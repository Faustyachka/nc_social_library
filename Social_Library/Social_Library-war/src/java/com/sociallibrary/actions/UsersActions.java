/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sociallibrary.actions;

import com.sociallibrary.entity.*;
import com.sociallibrary.crud.*;
import com.sociallibrary.connection.ConnectionProvider;
import com.sociallibrary.iactions.IUsersActions;
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
public class UsersActions implements IUsersActions
{
    private Connection connection;
    public static final Logger log = Logger.getLogger(UsersActions.class);

    public UsersActions()
    {
        connection = ConnectionProvider.getConnection();
    }

     public List<User> getAllUsers()
     {
         BasicConfigurator.configure();
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

            rs.close();
            ps.close();
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
        return users;
    }

//    public UsersActions(boolean debug)
//    {
//        connection = ConnectionProvider.getDebugConnection();
//    }
//
//    public static void main(String[] args){
//        System.out.println(000);
//        User user = new UsersActions(true).getUser("Quaecte", "Ie5Eequaemai");
//        System.out.println("000");
//        System.out.println(user.getEmail());
//        System.out.println(7);
//    }

     public User getUser(String login, String password)
     {
        BasicConfigurator.configure();
        User user = null;
        try
        {
            String sqlRequest ="SELECT id FROM Users WHERE login='?' AND password='?'";
            PreparedStatement ps = connection.prepareStatement(sqlRequest);
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next())
            {
                user = new UserCRUD().readUsers(rs.getLong("id"));
            }

            rs.close();
            ps.close();
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
        return user;
    }

    public User searchUserByLogin(String login) 
    {
        BasicConfigurator.configure();
        User user = new User();
        String selectParametr = "select id  from users where login = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(selectParametr);
            stmt.setString(1, login);
            UserCRUD uCRUD = new UserCRUD();
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                user = uCRUD.readUsers(Integer.parseInt(rs.getString(1)));
            }
            rs.close();
            stmt.close();
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
        return user;
    }
   public User searchUserByEmail(String email)
    {
        BasicConfigurator.configure();
        User user = new User();
        String selectParametr = "select id  from users where email = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(selectParametr);
            stmt.setString(1, email);
            UserCRUD uCRUD = new UserCRUD();
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                user = uCRUD.readUsers(Integer.parseInt(rs.getString(1)));
            }
            rs.close();
            stmt.close();
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
        return user;
    }
}
