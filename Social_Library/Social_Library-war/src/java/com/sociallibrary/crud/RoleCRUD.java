/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sociallibrary.crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.sociallibrary.connection.ConnectionProvider;
import com.sociallibrary.entities.Role;
import com.sociallibrary.entities.User;
import com.sociallibrary.crudInterfaces.IRoleCRUD;
/**
 *
 * @author Антон
 */
public class RoleCRUD implements IRoleCRUD
{

    private Connection connection;

    public RoleCRUD() {
        connection = ConnectionProvider.getConnection();
    }

    public Role readRole(int id) {
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

    public void createRole(Role role) {
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
        }
    }

    public void deleteRole(int id) {
        try {
                String sqlRequest = "DELETE FROM Role WHERE id=?";
            PreparedStatement ps = connection.prepareStatement(sqlRequest);
            ps.setInt(1, id);
            ps.executeUpdate();

            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateRole(Role role) {
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
        }
    }

}
