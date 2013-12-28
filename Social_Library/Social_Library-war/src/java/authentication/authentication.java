/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package authentication;

import OracleConnection.Oracle;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.log4j.Logger;

/**
 *
 * @author mazafaka
 */
public class authentication extends HttpServlet {
    public static final Logger log=Logger.getLogger(authentication.class);
    private Oracle conn1;
    private ServletConfig config;

    @Override
    public void init(ServletConfig config)
        throws ServletException{
            this.config=config;
 }

    @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,IOException{
  PrintWriter out = response.getWriter();
  Connection connection=null;
  ResultSet rs;
  String userName=new String("");
  String passwrd=new String("");
  response.setContentType("text/html");
  try {
  String sql = "select user,password from users";
  Statement stat = connection.createStatement();
  stat.executeQuery (sql);
  rs = stat.getResultSet();
  while (rs.next ()){
  userName=rs.getString("user");
  passwrd=rs.getString("password");
  }
  rs.close ();
  stat.close ();
  }catch(Exception e){
  System.out.println("Exception is ;"+e);
  }
  if(userName.equals(request.getParameter("user")) &&
  passwrd.equals(request.getParameter("pass"))){
  out.println("User Authenticated");
  }
  else{
  out.println("You are not an authentic person");
  }
  }

}
