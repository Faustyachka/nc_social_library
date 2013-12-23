/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package CRUD;

import Enity_Interfaces.RoleDAO;
import DAO.OracleDAOFactory;
import Entity.Role;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author mazafaka
 */
public class ImplRoleDAO implements RoleDAO{

    private Connection conn;
    private OracleDAOFactory conn1;
    private ResultSet rs;
    private static final String selectQuery="SELECT * FROM ? WHERE id=?";
    private static final String deleteQuery="DELETE FROM ? WHERE id =?";
    private static final String insertRoleQuery="INSERT INTO role VALUES(?, ?)";
    private static final String updateRoleQuery="UPDATE role SET name =? where id=?";
    private Role role;

    public ImplRoleDAO()
    {
        try
        {
        conn=conn1.getConnection();
        }
        catch(IOException e1)
        {
            System.out.println("IOExeption:"+e1);
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
           if (conn != null)
            {
                conn.close();
            }
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
            if (conn != null)
            {
                conn.close();
            }
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
            if (conn != null)
            {
                conn.close();
            }
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
            if (conn != null)
            {
                conn.close();
            }
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
