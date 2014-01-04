/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sociallibraryl.registration;

import com.sociallibrary.actions.UsersActionsImpl;
import com.sociallibrary.actionsInterfaces.IUsersActions;
import com.sociallibrary.controller.Command;
import com.sociallibrary.controller.ConfigurationManager;
import com.sociallibrary.crud.RoleCRUD;
import com.sociallibrary.entities.Role;
import com.sociallibrary.entities.User;
import com.sociallibrary.crudInterfaces.IUserCRUD;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sociallibrary.crud.UserCRUD;
import com.sociallibrary.crudInterfaces.IRoleCRUD;
import java.util.List;
import com.sociallibrary.email.EmailSender;
import com.sociallibrary.entities.Gender;

/**
 *
 * @author П
 */
public class Registration implements Command {

    private User user;
    private Role role;
    private UserCRUD userCRUD = new UserCRUD();
    private RoleCRUD roleCRUD = new RoleCRUD();

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;

        response.setContentType("text/html;charset=UTF-8");
        user = new User();
        role = new Role();
        Short status0 = 0;
        Short status1 = 1;
        role = roleCRUD.readRole(3);
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        user.setEmail(request.getParameter("email"));
        user.setLogin(request.getParameter("login"));
        user.setPassword(request.getParameter("password"));
        user.setGender(Gender.getGender(status1));
        user.setConfirmed(status0==1);
        user.setBanned(status1==1);
        user.setRegistrationDate(new Date().toString());
        user.setNotify(status1==1);
//        user.setRole(role);
        userCRUD.createUsers(user);
        IUsersActions uA = new UsersActionsImpl();
        List<User> uList = uA.searchUsersByParameter("login", user.getLogin());
        String mailSub = "Registration on Social Library";
        String mailText = "Please copy and use link: 'http://localhost:8080/Social_Library-war/Servlet?users=" + uList.get(0).getId() + "&command=confirmUser'";
        String mail[] = new String[1];
        mail[0] = user.getEmail();
        try {
            EmailSender.sendEmail(mail, mailSub, mailText);
        } catch (MessagingException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }

        page = ConfigurationManager.INDEX_PAGE;
        return page;
    }
}
