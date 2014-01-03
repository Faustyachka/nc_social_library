/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sociallibrary.ActionsImpl;

import com.sociallibrary.Entities.Role;
import com.sociallibrary.Entities.User;
import com.sociallibrary.connection.ConnectionProvider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mazafaka
 */
public class RolesActionsImpl {

    private Connection connection;

    public RolesActionsImpl() {
        connection = ConnectionProvider.getConnection();
    }
    public List<Role> getAllRoles() {
        List<Role> roles = new ArrayList<Role>();
        try {
                String sqlRequest =
                        "SELECT * FROM Role";
            PreparedStatement ps = connection.prepareStatement(sqlRequest);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Role role = new Role();
                role.setId(rs.getInt("id"));
                role.setName(rs.getString("name"));
                roles.add(role);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roles;
    }

    public boolean applyRoleToUser(Role role, User user) {
        try {
                String sqlRequest =
                        "INSERT INTO USERS_ROLES (ID,USERS,ROLE) values(0, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sqlRequest);

            ps.setLong(1, user.getId());
            ps.setInt(2, role.getId());
            ps.executeUpdate();

            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean dropAllRolesOfUser(User user) {
        try {
                String sqlRequest =
                        "DELETE FROM USERS_ROLES WHERE USERS=?";
            PreparedStatement ps = connection.prepareStatement(sqlRequest);

            ps.setLong(1, user.getId());

            ps.executeUpdate();

            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
