/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model.authorization;
import ActionsImpl.UsersActionsImpl;
import Controller.Command;
import Controller.ConfigurationManager;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.log4j.Logger;
/**
 *
 * @author Костя
 */
public class SignIn implements Command {

     public static final Logger log=Logger.getLogger(SignIn.class);
     private UsersActionsImpl users;

     public String execute(HttpServletRequest request, HttpServletResponse response) 
             throws ServletException, IOException
     {
         
        String page = ConfigurationManager.INDEX_PAGE;
        response.setContentType("text/html;charset=UTF-8");
        //String firstName=request.getParameter("password");
        //String lastName=request.getParameter("login");
        //users.SearchFirstName(firstName);
       // users.SearchLastName(lastName);

        if(users.SearchFirstName(request.getParameter("password"))!=null //&& users.SearchFirstName( request.getParameter("login") )==null
                )
        {
            System.out.println("You are in system"+users.SearchFirstName(request.getParameter("password")));
            page = ConfigurationManager.INDEX_PAGE;
        }
        else
        {
            System.out.println("Invalid login && password.Please try again.");
             page = ConfigurationManager.REGISTR_PAGE;
        }
       return null;
    }

     public static void main(String args[])
     {
        //response.setContentType("text/html;charset=UTF-8");
        String firstName="password";
        String lastName="login";
        UsersActionsImpl users=new UsersActionsImpl();

        if(users.SearchFirstName(firstName)!=null && users.SearchLastName(lastName) !=null)
        {
            System.out.println("You are in system"+users.SearchFirstName(firstName)+' '+users.SearchLastName(lastName));
            //page = ConfigurationManager.INDEX_PAGE;
        }
        else
        {
            System.out.println("Invalid login && password.Please try again.");
             //page = ConfigurationManager.REGISTR_PAGE;
        }
     }
}
