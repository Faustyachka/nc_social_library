/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sociallibrary.authorization;

import com.sociallibrary.actions.UsersActions;
import com.sociallibrary.constants.Const;
import com.sociallibrary.controller.Command;
import com.sociallibrary.crud.RoleCRUD;
import com.sociallibrary.crud.UserCRUD;
import com.sociallibrary.entity.Gender;
import com.sociallibrary.entity.Role;
import com.sociallibrary.entity.User;
import com.sociallibrary.registration.SecurityHash;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
//import com.google.gson.JsonObject;
import org.json.JSONObject;

//import net.sf.json.JSONException;
//import org.json.JSONObject

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Костя
 */
public class facebookLogin extends HttpServlet {

    private String code;
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private String fb_id;

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NoSuchAlgorithmException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            code = request.getParameter("code");
            //token
            String token = null;
            try {
                String g = "https://graph.facebook.com/oauth/access_token?client_id=" + Const.APP_ID + "&redirect_uri=" + URLEncoder.encode(Const.HOST + "facebookLogin", "UTF-8") + "&client_secret=" + Const.APP_SECRET + "&code=" + code;
                URL u = new URL(g);
                URLConnection c = u.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(c.getInputStream()));
                String inputLine;
                StringBuffer b = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    b.append(inputLine + "\n");
                }
                in.close();
                token = b.toString();
                if (token.startsWith("{")) {
                    throw new Exception("error on requesting token: " + token + " with code: " + code);
                }
            } catch (Exception e) {
                // an error occurred, handle this
            }
            //Graph API
            String graph = null;
            try {
                String g = "https://graph.facebook.com/me?" + token;
                URL u = new URL(g);
                URLConnection c = u.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(c.getInputStream()));
                String inputLine;
                StringBuffer b = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    b.append(inputLine + "\n");
                }
                in.close();
                graph = b.toString();

            } catch (Exception e) {
                // an error occurred, handle this
            }
            //Take data from FB for user
            try {
                JSONObject j = new JSONObject(graph);
                fb_id = j.getString("id");
                firstName = j.getString("first_name");
                lastName = j.getString("last_name");
                email = j.getString("email");
                gender = j.getString("gender");
                if (email.equals("")) {
                    email = "noEmail";
                }
                if (gender.equals("")) {
                    gender = "male";
                }
            } catch (org.json.JSONException ex) {
                Logger.getLogger(facebookLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
            //create user
            User user = new User();
            user = new UsersActions().searchUserByLogin(fb_id);
            if (user == null) {
                user = new User();
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setEmail(email);
                if (gender.equals("male")) {
                    user.setGender(Gender.getGender(1));
                } else {
                    user.setGender(Gender.getGender(0));
                }
                user.setLogin(fb_id);
                user.setNotify(false);
                user.setBanned(false);
                String pass = SecurityHash.getPass(8, 12);
                user.setPassword(SecurityHash.getMd5(pass));
                user.setConfirmed(true);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                user.setRegistrationDate(dateFormat.format(new Date()).toString());
                List<Role> rList = new ArrayList<Role>();
                rList.add(new RoleCRUD().readRole(3));
                user.setRoles(rList);
                new UserCRUD().createUser(user);
            } else {
            }



        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(facebookLogin.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(facebookLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
