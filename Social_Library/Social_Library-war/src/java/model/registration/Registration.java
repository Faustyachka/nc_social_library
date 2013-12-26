/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model.registration;

import Controller.Command;
import Controller.ConfigurationManager;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sociallibrary.dao.Dao;
import sociallibrary.entity.Users;
import model.email.EmailSender;

/**
 *
 * @author П
 */
public class Registration implements Command {

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         String page= null;

        response.setContentType("text/html;charset=UTF-8");
        Users users = new Users();
        //полная лажа, пока нету бд с секвинсами
        Random generator = new Random();
        int rand = generator.nextInt(1000000)+50;
        users.setId(rand);
        users.setFirsName(request.getParameter("firsName"));
        users.setLastName(request.getParameter("lastName"));
        users.setEmail(request.getParameter("email"));
        users.setLogin(request.getParameter("login"));
        users.setPassword(request.getParameter("password"));
        users.setGender(1);
        users.setConfirmed(0);
        users.setBanned(1);
        users.setRegistrationData("2013/12/25");
        users.setNotify(1);
        users.setRole(1);
        Dao dao = new Dao();
        dao.createUsers(users);
        String mailSub = "Registration on Social Library";
        String mailText = "Please copy and use link: 'http://localhost:8080/Social_Library-war/ConfirmedRegistration?users="+users.getLogin()+"'";
        String mail[] = new String[1];
        mail[0]=users.getEmail();
        try {
            EmailSender.sendEmail(mail, mailText, mailSub);
        } catch (MessagingException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }

        page= ConfigurationManager.INDEX_PAGE;
        return page;
    }
}