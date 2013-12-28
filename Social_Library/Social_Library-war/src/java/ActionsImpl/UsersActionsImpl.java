/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ActionsImpl;

import ActionsInterfaces.UsersActions;
import OracleConnection.Oracle;
import TransferObject.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;
/**
 *
 * @author mazafaka
 */
public class UsersActionsImpl implements UsersActions{

    public static final Logger log=Logger.getLogger(UsersActionsImpl.class);
    private Oracle conn1;
    private static final String selectFirstName = "select firstName from users where firstName=?";
    private static final String selectLastName = "select lastName from users where lastName=?";

     public String SearchFirstName(String firstname)
     {
         Users users=new Users();
         Connection conn=conn1.getConnection();
         try
        {
        PreparedStatement stmt = conn.prepareStatement(selectFirstName);
        stmt.setString(1, firstname);
        ResultSet rs = stmt.executeQuery();
        while (rs.next ())
        {
            firstname=rs.getString(users.getFirstName());
        }
        rs.close ();
        stmt.close ();
        }
        catch (SQLException e)
        {
            while(e!=null)
            {
                log.error("SQLException"+e);
            }
        }

         return firstname;
     }
     public String SearchLastName(String lastname)
     {
          Users users=new Users();
         Connection conn=conn1.getConnection();
         try
        {
        PreparedStatement stmt = conn.prepareStatement(selectLastName);
        stmt.setString(1, lastname);
        ResultSet rs = stmt.executeQuery();
        while (rs.next ())
        {
            lastname=rs.getString(users.getFirstName());
        }
        rs.close ();
        stmt.close ();
        }
        catch (SQLException e)
        {
            while(e!=null)
            {
                log.error("SQLException"+e);
            }
        }
         return lastname;
     }
}
