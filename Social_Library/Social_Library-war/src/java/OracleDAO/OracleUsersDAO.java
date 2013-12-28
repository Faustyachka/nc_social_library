/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package OracleDAO;

import OracleConnection.Oracle;
import TransferObject.Users;
import TransferObjectInterface.UsersDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;

/**
 *
 * @author mazafaka
 */
public class OracleUsersDAO implements UsersDAO{
    public static final Logger log=Logger.getLogger(OracleAuthorDAO.class);
   private Oracle conn1;
    private static final String selectQuery="SELECT * FROM ? WHERE id=?";
    private static final String deleteQuery="DELETE FROM ? WHERE id =?";
    private static final String insertUsersQuery="INSERT INTO users VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, TO_DATE('11.02.2013','dd.mm.yyyy'), ?)";
    private static final String updateUsersQuery="UPDATE users SET first_name = ?," +
                        "last_name = ?, email = ?, login = ?, password = ?," +
                        "gender = ?, confirmed = ?, banned = ?, registration_date = ?," +
                        "notify = ? WHERE id = ?";

    public void createUsers(Users users) {
        
        Connection conn=conn1.getConnection();
        try
        {
            PreparedStatement pstmt = conn.prepareStatement(insertUsersQuery);

            pstmt.setLong(1, users.getId());
            pstmt.setString(2, users.getFirstName());
            pstmt.setString(3, users.getLastName());
            pstmt.setString(4, users.getEmail());
            pstmt.setString(5, users.getLogin());
            pstmt.setString(6, users.getPassword());
            pstmt.setInt(7, users.getGender());
            pstmt.setInt(8, users.getConfirmed());
            pstmt.setInt(9, users.getBanned());
            pstmt.setDate(10,(Date) users.getRegistrationDate());
            pstmt.setInt(11, users.getNotify());

            pstmt.executeUpdate();
        }
        
        catch (SQLException e)
        {
            while(e!=null)
            {
                log.error("SQLException"+e);
            }
        }

    }

    public Users readUsers(int id) {
        Users users = new Users();
        Connection conn=conn1.getConnection();
        try
        {
            PreparedStatement stmt = conn.prepareStatement(selectQuery);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

           while (rs.next())
            {
                users.setId(rs.getLong(1));
                users.setFirstName(rs.getString(2));
                users.setLastName(rs.getString(3));
                users.setEmail(rs.getString(4));
                users.setLogin(rs.getString(5));
                users.setPassword(rs.getString(6));
                users.setGender(rs.getShort(7));
                users.setConfirmed(rs.getShort(8));
                users.setBanned(rs.getShort(9));
                users.setRegistrationDate(rs.getDate(10));
                users.setNotify(rs.getShort(11));
            }
            rs.close();
        }
        
        catch (SQLException e)
        {
            while(e!=null)
            {
                log.error("SQLException"+e);
            }
        }

        return users;
    }

    public void updateUsers(Users usersOld, Users usersNew) {
        
        Connection conn=conn1.getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement(updateUsersQuery);

            pstmt.setString(1, usersNew.getFirstName());
            pstmt.setString(2, usersNew.getLastName());
            pstmt.setString(3, usersNew.getEmail());
            pstmt.setString(4, usersNew.getLogin());
            pstmt.setString(5, usersNew.getPassword());
            pstmt.setInt(6, usersNew.getGender());
            pstmt.setInt(7, usersNew.getConfirmed());
            pstmt.setInt(8, usersNew.getBanned());
            pstmt.setInt(9, usersNew.getNotify());
            pstmt.setLong(11, usersOld.getId());

            pstmt.executeUpdate();
        }
       
        catch (SQLException e)
        {
            while(e!=null)
            {
                log.error("SQLException"+e);
            }
        }
    }

    public void deleteUsers(Users users) {
       
        Connection conn=conn1.getConnection();
        try
        {
            PreparedStatement stmt = conn.prepareStatement(deleteQuery);

            stmt.setLong(1, users.getId());

            stmt.executeUpdate();
        }
        
        catch (SQLException e)
        {
            while(e!=null)
            {
               log.error("SQLException"+e);
            }
        }

    }

}
