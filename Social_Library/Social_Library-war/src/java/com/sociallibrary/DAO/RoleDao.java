/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sociallibrary.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.sociallibrary.connection.ConnectionProvider;
import com.sociallibrary.Entities.Role;
import com.sociallibrary.Entities.User;
/**
 *
 * @author Антон
 */
public class RoleDao {

    private Connection connection;

    public RoleDao() {
        connection = ConnectionProvider.getConnection();
    }

    public boolean createRole(Role role) {
        try {
                String sqlRequest =
                        "INSERT INTO ROLE (ID, Name)  values(?, '?')";
            PreparedStatement ps = connection.prepareStatement(sqlRequest);

            ps.setInt(1, role.getId());
            ps.setString(2, role.getName());
            ps.executeUpdate();

            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
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

    public Role getRoleById(int id) {
        Role role = new Role();
        try {
                String sqlRequest =
                        "SELECT * FROM Role WHERE id=?";
            PreparedStatement ps = connection.prepareStatement(sqlRequest);

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                role.setId(rs.getInt("id"));
                role.setName(rs.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
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

    public List<Role> getRolesByUserId(long id) {
        List<Role> roles = new ArrayList<Role>();
        try {
                String sqlRequest =
                        "SELECT users_roles.role " +
                        "FROM users " +
                        "INNER JOIN users_roles " +
                        "ON users.id=users_roles.users " +
                        "WHERE users.id=?";
            PreparedStatement ps = connection.prepareStatement(sqlRequest);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Role role = getRoleById(rs.getInt("role"));
                roles.add(role);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roles;
    }

    public boolean removeRole(int id) {
        try {
                String sqlRequest = "DELETE FROM Role WHERE id=?";
            PreparedStatement ps = connection.prepareStatement(sqlRequest);
            ps.setInt(1, id);
            ps.executeUpdate();

            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
      }

    public boolean  editRole(Role role) {
        try {
                String sqlRequest = "UPDATE Role SET NAME='?' WHERE ID=?";
            PreparedStatement ps = connection.prepareStatement(sqlRequest);

            String[] roleParams = new String[2];
            roleParams = role.toStringList().toArray(roleParams);
            ps.setString(1, roleParams[1]);
            ps.setString(2, roleParams[0]);

            ps.executeUpdate();

            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
