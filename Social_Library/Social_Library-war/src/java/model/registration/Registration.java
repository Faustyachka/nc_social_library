/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.registration;

import Controller.Command;
import Controller.ConfigurationManager;
import TransferObject.Role;
import TransferObject.Users;
import TransferObjectInterface.UsersDAO;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import sociallibrary.dao.Dao;
import OracleDAO.OracleUsersDAO;
import model.email.EmailSender;

/**
 *
 * @author П
 */
public class Registration implements Command {

    private Users users;
    private Role role;
    private UsersDAO dao;


    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;

        response.setContentType("text/html;charset=UTF-8");
        users = new Users();
        role=new Role();
        Short status0=0;
        Short status1=1;

        role.setId(status1);
        role.setName("beginner");
        
        users.setId(status1.longValue());
        users.setFirstName(request.getParameter("firsName"));
        users.setLastName(request.getParameter("lastName"));
        users.setEmail(request.getParameter("email"));
        users.setLogin(request.getParameter("login"));
        users.setPassword(request.getParameter("password"));
        users.setGender(status1);
        users.setConfirmed(status0);
        users.setBanned(status1);
        users.setRegistrationDate(new Date());
        users.setNotify(status1);
        users.setRole(role);
        dao.createUsers(users);
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
