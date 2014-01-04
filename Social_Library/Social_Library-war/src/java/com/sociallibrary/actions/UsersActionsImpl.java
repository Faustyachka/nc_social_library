/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sociallibrary.actions;

import com.sociallibrary.entities.Gender;
import com.sociallibrary.entities.User;
import com.sociallibrary.connection.ConnectionProvider;
import com.sociallibrary.crud.RoleCRUD;
import com.sociallibrary.crud.UserCRUD;
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
public class UsersActionsImpl {

    private Connection connection;

    public UsersActionsImpl() {
        connection = ConnectionProvider.getConnection();
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        try {
            String sqlRequest =
                    "SELECT * FROM Users";
            PreparedStatement ps = connection.prepareStatement(sqlRequest);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setFirstName(rs.getString("FIRST_NAME"));
                user.setLastName(rs.getString("LAST_NAME"));
                user.setEmail(rs.getString("EMAIL"));
                user.setLogin(rs.getString("LOGIN"));
                user.setPassword(rs.getString("PASSWORD"));
                user.setGender(Gender.getGender(rs.getInt("GENDER")));
                user.setConfirmed(rs.getInt("CONFIRMED") == 1);
                user.setBanned(rs.getInt("BANNED") == 1);
                user.setRegistrationDate(rs.getString("REGISTRATION_DATE"));
                user.setNotify(rs.getInt("NOTIFY") == 1);
                //user.setRoles(new RoleCRUD().getRolesByUserId(rs.getLong("id")));
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
    /*
    public static final Logger log = Logger.getLogger(UsersActionsImpl.class);
    private Users users = new Users();

    public UsersActionsImpl() {
    users = new Users();
    }
     */

    public User searchUserByLogin(String login) {
        UserCRUD uCRUD = new UserCRUD();
        User users = new User();
        String selectParametr = "select id  from users where login = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(selectParametr);
            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                uCRUD.readUsers(Integer.parseInt(rs.getString(1)));
            }
            rs.close();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }
}
