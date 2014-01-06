/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sociallibrary.controller;
import com.sociallibrary.actions.UsersActions;
import com.sociallibrary.entity.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Антон
 */
public class SignIn implements Command {

     public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        try {
            User user = new UsersActions().getUser(login, password);
            request.getSession().setAttribute("user", user);
        } finally {

        }
        return ConfigurationManager.LOCAL_LIB;
    }

}
