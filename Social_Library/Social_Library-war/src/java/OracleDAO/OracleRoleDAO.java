/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package OracleDAO;

import OracleConnection.Oracle;
import TransferObjectInterface.RoleDAO;
import TransferObject.Role;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 *
 * @author mazafaka
 */
public class OracleRoleDAO implements RoleDAO{
    public static final Logger log=Logger.getLogger(OracleRoleDAO.class);
    private Oracle conn1;
    private static final String selectQuery="SELECT * FROM role WHERE id=?";
    private static final String deleteQuery="DELETE FROM role WHERE id =?";
    private static final String insertRoleQuery="INSERT INTO role VALUES(?, ?)";
    private static final String updateRoleQuery="UPDATE role SET name =? where id=?";

    public Role readRole(int id)
    {
            BasicConfigurator.configure();
        Role role = new Role();
        
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
       
        catch (SQLException e)
        {
            while(e!=null)
            {
                log.error("SQLException"+e);
            }
        }

        return role;
    }

    public void createRole(Role role) {
            BasicConfigurator.configure();
        Connection conn=conn1.getConnection();
        try
        {
            PreparedStatement pstmt = conn.prepareStatement(insertRoleQuery);

            pstmt.setInt(1, role.getId());
            pstmt.setString(2, role.getName());

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

    public void deleteRole(Role role) {
            BasicConfigurator.configure();
        Connection conn=conn1.getConnection();
        try
        {
            PreparedStatement stmt = conn.prepareStatement(deleteQuery);

            stmt.setInt(1, role.getId());

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

    public void updateRole(Role roleNew, Role roleOld) {
            BasicConfigurator.configure();
        Connection conn=conn1.getConnection();
        try
        {
            PreparedStatement pstmt = conn.prepareStatement(updateRoleQuery);

            pstmt.setString(1, roleNew.getName());
            pstmt.setInt(2, roleOld.getId());

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

}

