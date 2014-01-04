/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sociallibrary.actions;

import com.sociallibrary.entity.Role;
import com.sociallibrary.entity.User;
import com.sociallibrary.connection.ConnectionProvider;
import com.sociallibrary.iactions.IRolesActions;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nastya Pavlova
 */
public class RolesActions implements IRolesActions
{
    private Connection connection;

    public RolesActions()
    {
        connection = ConnectionProvider.getConnection();
    }

    public List<Role> getAllRoles()
    {
        List<Role> roles = new ArrayList<Role>();
        try {

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
        }
        return roles;
    }

    public void applyRoleToUser(Role role, User user)
    {
        try 
        {

            String sqlRequest ="INSERT INTO USERS_ROLES (ID,USERS,ROLE) values(0, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sqlRequest);

            ps.setLong(1, user.getId());
            ps.setInt(2, role.getId());
            ps.executeUpdate();

            connection.commit();

        } 
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void dropAllRolesOfUser(User user)
    {
        try {

            String sqlRequest ="DELETE FROM USERS_ROLES WHERE USERS=?";
            PreparedStatement ps = connection.prepareStatement(sqlRequest);

            ps.setLong(1, user.getId());
            ps.executeUpdate();

            connection.commit();

        } 
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
