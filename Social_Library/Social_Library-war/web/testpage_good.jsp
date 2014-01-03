<%-- 
    Document   : testpage_good
    Created on : 1/1/2014, 4:24:47
    Author     : Антон
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.*"%>
<%@page import="com.sociallibrary.connection.*"%>
<%@page import="com.sociallibrary.Entities.*"%>
<%@page import="com.sociallibrary.DAO.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
/**/         //   UserDao userDao = new UserDao();

           // for(int i = 1; i < 11; i++){
          //      User user = userDao.getUserById(i);
           //     out.println(user.toString()+"<br/>");
           // }

/*            java.util.Random r = new java.util.Random();
            r.nextBoolean();
            List<User> users = new UserDao().getAllUsers();
            for(User u : users){
                 if(u.getRole().getId()==4){
                    u.getRole().setId(r.nextBoolean()?2:3);
                    userDao.editUser(u);
                }
               out.println(u.toString()+"<br/>");
            }
  */          
            //List<Library> books = LibraryDao().getAllBooks();
           // List<TransferObject.Library> books = new ActionsImpl.LibraryActionsImpl().getBooksByIdInInterval(0, 1550);
           // for(TransferObject.Library b : books){

            List<Library> books = new LibraryDao().getAllBooks();
            for(Library b : books){
 /*                if(u.getRole().getId()==4){
                    u.getRole().setId(r.nextBoolean()?2:3);
                    userDao.editUser(u);
                }
 */              out.println(b.toString()+"<br/>");
            }
            books = new LibraryDao().getAllBooks();
            for(Library b : books){
              out.println(b.toString()+"<br/>");
            }
            books = new LibraryDao().getAllBooks();
            for(Library b : books){
              out.println(b.toString()+"<br/>");
            }


/*
            users = new UserDao().getAllUsers();
            for(User u : users){
             out.println(u.toString()+"<br/>");
            }    
            users = new UserDao().getAllUsers();
            for(User u : users){
             out.println(u.toString()+"<br/>");
            }
            users = new UserDao().getAllUsers();
            for(User u : users){
             out.println(u.toString()+"<br/>");
            }
            users = new UserDao().getAllUsers();
            for(User u : users){
             out.println(u.toString()+"<br/>");
            }
            out.println(userDao.getUserById(5).toString()+"<br/>");
            out.println(userDao.getUserById(5).toString()+"<br/>");
            out.println(userDao.getUserById(5).toString()+"<br/>");
            out.println(userDao.getUserById(5).toString()+"<br/>");
            out.println(userDao.getUserById(5).toString()+"<br/>");
  */
        %><br/><br/><br/>
        <%/*
            User user = userDao.getUserById(20);
            String[] userParams = new String[12];
            userParams = user.toStringList().toArray(userParams);
            for(int i=0; i < 12; i++)
                out.print(userParams[i]+"<br/>");
*/
        %>
    </body>
</html>
