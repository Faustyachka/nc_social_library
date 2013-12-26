/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package OracleDAO;

import OracleConnection.Oracle;
import TransferObjectInterface.RoleDAO;
import TransferObject.Role;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author mazafaka
 */
public class OracleRoleDAO implements RoleDAO{

    private Oracle conn1;
    private ResultSet rs;
    private Connection conn;
    private static final String selectQuery="SELECT * FROM ? WHERE id=?";
    private static final String deleteQuery="DELETE FROM ? WHERE id =?";
    private static final String insertRoleQuery="INSERT INTO role VALUES(?, ?)";
    private static final String updateRoleQuery="UPDATE role SET name =? where id=?";
    private Role role;

    public OracleRoleDAO()
    {
        try
        {
        conn=conn1.getConnection();
        }
        catch(IOException e1)
        {
            System.out.println("IOExeption:"+e1);
        }
    }

    public Role readRole(int id)
    {
        role = new Role();
        try
        {
            PreparedStatement stmt = conn.prepareStatement(selectQuery);
            stmt.setString(1, "role");
            stmt.setInt(2, role.getId());

            rs = stmt.executeQuery();

            while (rs.next())
            {
                role.setId(rs.getShort(1));
                role.setName(rs.getString(2));
            }
                conn.close();
            
        }
        catch (SQLException e)
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
            PreparedStatement pstmt = conn.prepareStatement(insertRoleQuery);

            pstmt.setInt(1, role.getId());
            pstmt.setString(2, role.getName());

            pstmt.executeUpdate();
                conn.close();
            
        }
        catch (SQLException e)
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
            PreparedStatement stmt = conn.prepareStatement(deleteQuery);

            stmt.setString(1, "role");
            stmt.setInt(2, role.getId());

            stmt.executeUpdate();
                conn.close();
            
        }
        catch (SQLException e)
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
            PreparedStatement pstmt = conn.prepareStatement(updateRoleQuery);

            pstmt.setString(1, roleNew.getName());
            pstmt.setInt(2, roleOld.getId());

            pstmt.executeUpdate();
                conn.close();
            
        }
        catch (SQLException e)
        {
            while(e!=null)
            {
                e.printStackTrace();
                e=e.getNextException();
            }
        }
    }

}

