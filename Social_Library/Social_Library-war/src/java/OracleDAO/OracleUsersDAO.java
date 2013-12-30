/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package OracleDAO;

import OracleConnection.Oracle;
import TransferObject.Role;
import TransferObject.Users;
import TransferObjectInterface.RoleDAO;
import TransferObjectInterface.UsersDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 *
 * @author mazafaka
 */
public class OracleUsersDAO implements UsersDAO{

    public static final Logger log=Logger.getLogger(OracleUsersDAO.class);
    private Oracle conn1 = new Oracle();
    private static final String selectQuery="SELECT * FROM users WHERE id=?";
    private static final String deleteQuery="DELETE FROM users WHERE id =?";
    private static final String insertUsersQuery="INSERT INTO users VALUES (users_id.nextval, ?, ?, ?, ?, ?, ?, ?, ?, TO_DATE('11.02.2013','dd.mm.yyyy'), ?,?)";
    private static final String updateUsersQuery="UPDATE USERS SET FIRST_NAME = ?, LAST_NAME = ?, EMAIL = ?, LOGIN = ?, PASSWORD = ?, GENDER = ?, " +
            "CONFIRMED = ?, BANNED = ?, REGISTRATION_DATE = TO_DATE(?, 'YYYY-MM-DD'), NOTIFY = ?, ROLE = ? WHERE ID=?";

    public void createUsers(Users users) {
    BasicConfigurator.configure();
        Connection conn=conn1.getConnection();
        try
        {
            PreparedStatement pstmt = conn.prepareStatement(insertUsersQuery);

            pstmt.setString(1, users.getFirstName());
            pstmt.setString(2, users.getLastName());
            pstmt.setString(3, users.getEmail());
            pstmt.setString(4, users.getLogin());
            pstmt.setString(5, users.getPassword());
            pstmt.setInt(6, users.getGender());
            pstmt.setInt(7, users.getConfirmed());
            pstmt.setInt(8, users.getBanned());
//          pstmt.setDate(9,(Date) users.getRegistrationDate());
            pstmt.setInt(9, users.getNotify());
            pstmt.setInt(10, users.getRole().getId());
            pstmt.executeUpdate();

            pstmt.close();
                conn.close();

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
            BasicConfigurator.configure();
        Users users = new Users();
        Connection conn=conn1.getConnection();
        try
        {
            PreparedStatement stmt = conn.prepareStatement(selectQuery);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            RoleDAO r = new OracleRoleDAO();

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
                users.setRole(r.readRole(rs.getInt(12)));
            }
            rs.close();
            stmt.close();
                conn.close();
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
            BasicConfigurator.configure();
        Connection conn=conn1.getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement(updateUsersQuery);

            pstmt.setString(1, usersNew.getFirstName());
            pstmt.setString(2, usersNew.getLastName());
            pstmt.setString(3, usersNew.getEmail());
            pstmt.setString(4, usersNew.getLogin());
            pstmt.setString(5, usersNew.getPassword());
            pstmt.setShort(6, usersNew.getGender());
            pstmt.setInt(7, usersNew.getConfirmed());
            pstmt.setInt(8, usersNew.getBanned());
            pstmt.setDate(9,(Date) usersNew.getRegistrationDate());
            pstmt.setInt(10, usersNew.getNotify());
            pstmt.setInt(11, usersNew.getRole().getId());
            pstmt.setLong(12, usersOld.getId());

            pstmt.executeUpdate();

            pstmt.close();
                conn.close();
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
           BasicConfigurator.configure();
        Connection conn=conn1.getConnection();
        try
        {
            PreparedStatement stmt = conn.prepareStatement(deleteQuery);

            stmt.setLong(1, users.getId());

            stmt.executeUpdate();

            stmt.close();
                conn.close();
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
