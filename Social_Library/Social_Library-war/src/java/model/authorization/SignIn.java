/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model.authorization;
import ActionsImpl.UsersActionsImpl;
import ActionsInterfaces.UsersActions;
import Controller.Command;
import Controller.ConfigurationManager;
import TransferObject.Users;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import OracleConnection.Oracle;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.log4j.Logger;
/**
 *
 * @author Костя
 */
public class SignIn implements Command {

     public static final Logger log=Logger.getLogger(SignIn.class);
     private UsersActions users;

     public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page = ConfigurationManager.INDEX_PAGE;
        response.setContentType("text/html;charset=UTF-8");
        String firstName=request.getParameter("password");
        String lastName=request.getParameter("login");
        users.SearchFirstName(firstName);
        users.SearchLastName(lastName);

        if(firstName!=null && lastName !=null)
        {
            System.out.println("You are in system");
            page = ConfigurationManager.INDEX_PAGE;
        }
        else
        {
            System.out.println("Invalid login && password.Please try again.");
             page = ConfigurationManager.REGISTR_PAGE;
        }
       return null;
    }
}
