/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sociallibrary.actions;

import com.sociallibrary.entity.*;
import com.sociallibrary.connection.ConnectionProvider;
import com.sociallibrary.iactions.IRolesActions;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 *
 * @author Nastya Pavlova
 */
public class RolesActions implements IRolesActions
{
    private Connection connection;
    public static final Logger log = Logger.getLogger(RolesActions.class);

    public RolesActions()
    {
        connection = ConnectionProvider.getConnection();
    }

    public List<Role> getAllRoles()
    {
        BasicConfigurator.configure();
        List<Role> roles = new ArrayList<Role>();
        try
        {
            String sqlRequest ="SELECT * FROM Role";
            PreparedStatement ps = connection.prepareStatement(sqlRequest);
            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                Role role = new Role();
                role.setId(rs.getInt("id"));
                role.setName(rs.getString("name"));
                roles.add(role);
            }
        } 
         catch (SQLException e)
        {
                e.printStackTrace();
                log.error("SQLException:" + e);
        }
        finally
        {
            ConnectionProvider.close();
        }
        return roles;
    }

    public void applyRoleToUser(Role role, User user)
    {
        BasicConfigurator.configure();
        try 
        {
            String sqlRequest ="INSERT INTO USERS_ROLES (ID,USERS,ROLE) values(0, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sqlRequest);

            ps.setLong(1, user.getId());
            ps.setInt(2, role.getId());
            ps.executeUpdate();

        } 
         catch (SQLException e)
        {
                e.printStackTrace();
                log.error("SQLException:" + e);
        }
        finally
        {
            ConnectionProvider.close();
        }
    }

    public void dropAllRolesOfUser(User user)
    {
        BasicConfigurator.configure();
        try
        {
            String sqlRequest ="DELETE FROM USERS_ROLES WHERE USERS=?";
            PreparedStatement ps = connection.prepareStatement(sqlRequest);

            ps.setLong(1, user.getId());
            ps.executeUpdate();

        } 
         catch (SQLException e)
        {
                e.printStackTrace();
                log.error("SQLException:" + e);
        }
        finally
        {
            ConnectionProvider.close();
        }
    }
}
