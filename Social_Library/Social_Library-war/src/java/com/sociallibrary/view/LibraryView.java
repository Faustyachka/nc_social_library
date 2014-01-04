/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sociallibrary.view;

import com.sociallibrary.controller.ConfigurationManager;
import com.sociallibrary.crud.*;
import com.sociallibrary.entities.BookWorkflow;
import com.sociallibrary.entities.Library;
import com.sociallibrary.crudInterfaces.ILibraryCRUD;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mazafaka
 */
public class LibraryView extends HttpServlet {
   /*
   private Users users;
    private Role role;
    private UsersDAO dao = new OracleUsersDAO();
    private RoleDAO rDao = new OracleRoleDAO();*/
    private Library library;
    private BookWorkflow bookworkflow;
    private ILibraryCRUD dao=new LibraryCRUD();

    public String execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException
    {
        String page = null;

        response.setContentType("text/html;charset=UTF-8");
        library=new Library();
        bookworkflow=new BookWorkflow();
        
        /*  передача параметра воркфлов=publihed в метод поиска по книгам
         
         List<Library> uList = uAction.searchLibraryByParameter(library.setWorkflow(bookworkflow.setWorkflow("published")));
        */
           //page = ConfigurationManager.LIBRARY_PAGE;

           page = ConfigurationManager.LOCAL_LIB;
      
        return page;
         
         
         
        
    }
}
