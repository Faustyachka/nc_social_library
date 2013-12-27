/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.registration;

import Controller.Command;
import Controller.ConfigurationManager;
import TransferObject.Role;
import TransferObject.Users;
import java.io.IOException;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sociallibrary.dao.Dao;
import OracleDAO.OracleUsersDAO;
import model.email.EmailSender;

/**
 *
 * @author П
 */
public class Registration implements Command {

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;

        response.setContentType("text/html;charset=UTF-8");
        Users users = new Users();
        users.setFirstName(request.getParameter("firsName"));
        users.setLastName(request.getParameter("lastName"));
        users.setEmail(request.getParameter("email"));
        users.setLogin(request.getParameter("login"));
        users.setPassword(request.getParameter("password"));
        users.setGender((short)1);
        users.setConfirmed((short)0);
        users.setBanned((short)0);
        users.setRegistrationDate(new Date());
        users.setNotify((short)0);
        Role role = new Role();
        role.setId((short)3);
        users.setRole(role);
        OracleUsersDAO daoUser = new OracleUsersDAO();
        daoUser.createUsers(users);
        String mailSub = "Registration on Social Library";
        String mailText = "Please copy and use link: 'http://localhost:8080/Social_Library-war/Servlet?users=" + users.getLogin() + "&command=confirmUser'";
        String mail[] = new String[1];
        mail[0] = users.getEmail();
        try {
            EmailSender.sendEmail(mail, mailSub, mailText);
        } catch (MessagingException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }

        page = ConfigurationManager.INDEX_PAGE;
        return page;
    }
}
