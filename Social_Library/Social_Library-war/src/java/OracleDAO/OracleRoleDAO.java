/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package OracleDAO;

import OracleConnection.Oracle;
import TransferObjectInterface.RoleDAO;
import TransferObject.Role;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author mazafaka
 */
public class OracleRoleDAO implements RoleDAO{
    public static final Logger log=Logger.getLogger(OracleAuthorDAO.class);
    private Oracle conn1 = new Oracle();
    private static final String selectQuery="SELECT * FROM role WHERE id=?";
    private static final String deleteQuery="DELETE FROM role WHERE id =?";
    private static final String insertRoleQuery="INSERT INTO role (ID, NAME) VALUES(ROLE_ID.nextval, ?)";
    private static final String updateRoleQuery="UPDATE role SET name =? where id=?";
    private Role role = new Role();

    public Role readRole(int id)
    {
        role = new Role();
        try
        {
        Connection conn=conn1.getConnection();
        try
        {
            PreparedStatement stmt = conn.prepareStatement(selectQuery);

            stmt.setInt(1, role.getId());

            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                role.setId(rs.getShort(1));
                role.setName(rs.getString(2));
            }
            rs.close();
        }
        finally
        {
            conn.close();
        }
        }
        catch (FileNotFoundException ex) {
            java.util.logging.Logger.getLogger(OracleRoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }        catch (SQLException e)
        {
            while(e!=null)
            {
                e.printStackTrace();
                e=e.getNextException();
            }
        }

        return role;
    }

    public void createRole(Role role) {
        try
        {
        Connection conn=conn1.getConnection();
        try
        {
            PreparedStatement pstmt = conn.prepareStatement(insertRoleQuery);
            pstmt.setString(1, role.getName());

            pstmt.executeUpdate();
        }
        finally
        {   
            conn.close();
        }
        }
        catch (FileNotFoundException ex) {
            java.util.logging.Logger.getLogger(OracleRoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }        catch (SQLException e)
        {
            while(e!=null)
            {
                e.printStackTrace();
                e=e.getNextException();
            }
        }
    }

    public void deleteRole(Role role) {
        try
        {
        Connection conn=conn1.getConnection();
        try
        {
            PreparedStatement stmt = conn.prepareStatement(deleteQuery);

            stmt.setInt(1, role.getId());

            stmt.executeUpdate();
        }
        finally
        {
            conn.close();
        }
        }
        catch (FileNotFoundException ex) {
            java.util.logging.Logger.getLogger(OracleRoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }        catch (SQLException e)
        {
            while(e!=null)
            {
                e.printStackTrace();
                e=e.getNextException();
            }
        }
    }

    public void updateRole(Role roleNew, Role roleOld) {
        try
        {
        Connection conn=conn1.getConnection();
        try
        {
            PreparedStatement pstmt = conn.prepareStatement(updateRoleQuery);

            pstmt.setString(1, roleNew.getName());
            pstmt.setInt(2, roleOld.getId());

            pstmt.executeUpdate();
        }
        finally
        {
            conn.close();
        }
        }
        catch (FileNotFoundException ex) {
            java.util.logging.Logger.getLogger(OracleRoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }        catch (SQLException e)
        {
            while(e!=null)
            {
                e.printStackTrace();
                e=e.getNextException();
            }
        }
    }

}

