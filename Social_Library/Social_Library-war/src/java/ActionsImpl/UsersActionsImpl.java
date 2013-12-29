/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ActionsImpl;

import ActionsInterfaces.UsersActions;
import TransferObject.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.log4j.Logger;

/**
 *
 * @author mazafaka
 */
public class UsersActionsImpl implements UsersActions{

    public static final Logger log=Logger.getLogger(UsersActionsImpl.class);
    private static final String selectFirstName = "select firstName from users where firstName=?";
    private static final String selectLastName = "select lastName from users where lastName=?";
    private Users users;

    public UsersActionsImpl()
    {
        users=new Users();
    }

     public String SearchFirstName(String firstname)
     {
          String new1=new String("");
         try
        {
             Locale.setDefault(Locale.ENGLISH);
             Context ic = new InitialContext();
             DataSource dataSource = (DataSource) ic.lookup("jdbc/test");
             Connection conn = dataSource.getConnection();
             conn.setAutoCommit(false);
             PreparedStatement stmt = conn.prepareStatement(selectFirstName);
             stmt.setString(1, firstname);
             ResultSet rs = stmt.executeQuery();
             while (rs.next ())
             {
                new1=rs.getString(users.getFirstName());
             }
             rs.close ();
             stmt.close ();
             conn.close();
        }
         catch(NamingException e)
         {
             log.error("Cannot retrieve jdbc/test"+e.getMessage());
         }
        catch (SQLException e)
        {
            while(e!=null)
            {
                log.error("SQLException"+e);
            }
        }

         return new1;
     }
     public String SearchLastName(String lastname)
     {
         String new2=new String("");
         try
        {
             Locale.setDefault(Locale.ENGLISH);
             Context ic = new InitialContext();
             DataSource dataSource = (DataSource) ic.lookup("jdbc/test");
             Connection conn = dataSource.getConnection();
             conn.setAutoCommit(false);
             PreparedStatement stmt = conn.prepareStatement(selectLastName);
             stmt.setString(1, lastname);
             ResultSet rs = stmt.executeQuery();
             while (rs.next ())
             {
                lastname=rs.getString(users.getFirstName());
             }
             rs.close ();
             stmt.close ();
             conn.close();
        }
         catch(NamingException e)
         {
             log.error("Cannot retrieve jdbc/test"+e.getMessage());
         }
        catch (SQLException e)
        {
            while(e!=null)
            {
                log.error("SQLException"+e);
            }
        }
         return new2;
     }
}