/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sociallibrary.authorization;

import com.sociallibrary.constants.Const;
import java.util.logging.Level;
import java.util.logging.Logger;
//import com.google.gson.JsonObject;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Костя
 */
public class facebookLogin extends HttpServlet {

    private static final String App_ID = "580908538651637";
    private String code;
    private String firstName;

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            code = request.getParameter("code");
            String token = null;
            try {
                String g = "https://graph.facebook.com/oauth/access_token?client_id="+Const.APP_ID+"&redirect_uri=" + URLEncoder.encode(Const.HOST+"facebookLogin", "UTF-8") + "&client_secret="+Const.APP_SECRET+"&code=" + code;
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
            out.print(code);
            out.print("<br>"+graph);
            try {
                JSONObject j = new JSONObject(graph);
                firstName = j.getString("id");
            } catch (org.json.JSONException ex) {
                Logger.getLogger(facebookLogin.class.getName()).log(Level.SEVERE, null, ex);
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
            processRequest(request, response);

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
            processRequest(request, response);
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
