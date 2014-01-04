/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sociallibrary.crud;

/**
 *
 * @author Антон
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.sociallibrary.connection.ConnectionProvider;
import com.sociallibrary.entity.Gender;
import com.sociallibrary.entity.Role;
import com.sociallibrary.entity.User;
import com.sociallibrary.icrud.IUserCRUD;

public class UserCRUD implements IUserCRUD
{

    private Connection connection;

    public UserCRUD() {
        connection = ConnectionProvider.getConnection();
    }

    public void createUsers(User user) {
        try {
                String sqlRequest =
                        "INSERT INTO Users (ID,FIRST_NAME,LAST_NAME,EMAIL,LOGIN,PASSWORD," +
                        "GENDER,CONFIRMED,BANNED,REGISTRATION_DATE,NOTIFY,ROLE) " +
                        "values(?,'?','?','?','?','?',?, ?, ?, TO_DATE('?','yyyy-mm-dd'), ?)";
            PreparedStatement ps = connection.prepareStatement(sqlRequest);

            ps.setLong(1, user.getId());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getLogin());
            ps.setString(6, user.getPassword());
            ps.setInt(7, user.getGender().toInt());
            ps.setBoolean(8, user.isConfirmed());
            ps.setBoolean(9, user.isBanned());
            ps.setString(10, user.getRegistrationDate());
            ps.setBoolean(11, user.isNotify());
            ps.executeUpdate();

            for(Role r : user.getRoles()) new RoleCRUD().applyRoleToUser(r, user);

//            connection.commit();
            connection.close();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User readUsers(int id) {
        User user = new User();
        try {
                String sqlRequest =
                        "SELECT * FROM Users WHERE id=?";
            PreparedStatement ps = connection.prepareStatement(sqlRequest);

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user.setId(rs.getLong("id"));
                user.setFirstName(rs.getString("FIRST_NAME"));
                user.setLastName(rs.getString("LAST_NAME"));
                user.setEmail(rs.getString("EMAIL"));
                user.setLogin(rs.getString("LOGIN"));
                user.setPassword(rs.getString("PASSWORD"));
                user.setGender(Gender.getGender(rs.getInt("GENDER")));
                user.setConfirmed(rs.getInt("CONFIRMED")==1);
                user.setBanned(rs.getInt("BANNED")==1);
                //String[] regDate = rs.getString("REGISTRATION_DATE").split(".");
                //user.setRegistrationDate(Date.valueOf(regDate[2]+"-"+regDate[1]+"-"+regDate[0]));
                user.setRegistrationDate(rs.getString("REGISTRATION_DATE"));
                user.setNotify(rs.getInt("NOTIFY")==1);
                //user.setRoles(new RoleCRUD().readRole((int) rs.getInt("id")));
                //user.setRole(new Role(1, "Administrator"));
            }

            connection.close();
            ps.close();
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void updateUsers(User user) {
        try {
                String sqlRequest = "UPDATE Users SET FIRST_NAME='?', LAST_NAME='?', " +
                        "EMAIL='?', LOGIN='?', PASSWORD='?', GENDER=?, CONFIRMED=?, " +
                        "BANNED=?, REGISTRATION_DATE=TO_DATE('?','yyyy-mm-dd'), NOTIFY=? WHERE ID=?";
            PreparedStatement ps = connection.prepareStatement(sqlRequest);

            String[] userParams = new String[12];
            userParams = user.toStringList().toArray(userParams);
            for(int i=1; i < 11; i++)
                ps.setString(i, userParams[i]);

            new RoleCRUD().dropAllRolesOfUser(user);
            for(Role r : user.getRoles()) new RoleCRUD().applyRoleToUser(r, user);
            ps.setString(11, userParams[0]);

            ps.executeUpdate();
            connection.prepareStatement("commit").executeUpdate();
//            connection.commit();

            connection.close();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUsers(int id) {
        try {
                String sqlRequest = "DELETE FROM users WHERE id=?";
            PreparedStatement ps = connection.prepareStatement(sqlRequest);
            ps.setInt(1, id);
            ps.executeUpdate();
//            connection.commit();

            connection.close();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}