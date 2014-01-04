/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sociallibrary.actions;

import com.sociallibrary.entity.Gender;
import com.sociallibrary.entity.User;
import com.sociallibrary.connection.ConnectionProvider;
//import com.sociallibrary.crud.RoleCRUD;
import com.sociallibrary.crud.UserCRUD;
import com.sociallibrary.iactions.IUsersActions;
import com.sociallibrary.icrud.IUserCRUD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.BasicConfigurator;

/**
 *
 * @author Nastya Pavlova
 */
public class UsersActions implements IUsersActions
{
    private Connection connection;

    public UsersActions()
    {
        connection = ConnectionProvider.getConnection();
    }

     public List<User> getAllUsers()
     {
        List<User> users = new ArrayList<User>();
        try 
        {
            String sqlRequest ="SELECT * FROM Users";
            PreparedStatement ps = connection.prepareStatement(sqlRequest);
            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setFirstName(rs.getString("FIRST_NAME"));
                user.setLastName(rs.getString("LAST_NAME"));
                user.setEmail(rs.getString("EMAIL"));
                user.setLogin(rs.getString("LOGIN"));
                user.setPassword(rs.getString("PASSWORD"));
                user.setGender(Gender.getGender(rs.getInt("GENDER")));
                user.setConfirmed(rs.getInt("CONFIRMED")==1);
                user.setBanned(rs.getInt("BANNED")==1);
                user.setRegistrationDate(rs.getString("REGISTRATION_DATE"));
                user.setNotify(rs.getInt("NOTIFY")==1);
                //user.setRoles(new RoleCRUD().getRolesByUserId(rs.getLong("id")));
                users.add(user);
            }
        } 
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return users;
    }

    public List<User> searchUsersByParameter(String where, String what)
    {
        BasicConfigurator.configure();
        IUserCRUD u = new UserCRUD();
        List<User> uList = new ArrayList<User>();
        String selectParametr = "select id  from users where "+where+" = ?";
        try
        {
            PreparedStatement stmt = connection.prepareStatement(selectParametr);
            stmt.setString(1, what);
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                User user = new User();
                user = u.readUsers(rs.getInt("id"));
                uList.add(user);
            }
            rs.close();
            stmt.close();
            connection.close();
        } 
        catch (SQLException e)
        {
            while (e != null)
            {
                //log.error("SQLException" + e);
            }
        }
        return uList;
    }
}