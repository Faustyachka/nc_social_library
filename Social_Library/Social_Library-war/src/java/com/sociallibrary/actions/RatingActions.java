/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sociallibrary.actions;

import com.sociallibrary.entity.*;
import com.sociallibrary.crud.*;
import org.apache.log4j.*;
import com.sociallibrary.connection.ConnectionProvider;
import com.sociallibrary.iactions.IRatingActions;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Антон
 */
public class RatingActions implements IRatingActions
{
    private Connection connection;
    public static final Logger log = Logger.getLogger(RatingActions.class);

    public RatingActions()
    {
        connection = ConnectionProvider.getConnection();
    }
    
    public List<Rating> getRatingsByBookId(long id)
    {
        BasicConfigurator.configure();
        RatingCRUD u = new RatingCRUD();
        List<Rating> lList = new ArrayList<Rating>();
        String selectParametr = "select *  from rating where book= ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(selectParametr);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                lList.add(u.readRating(rs.getInt("id")));
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
        return lList;
    }

    public Rating getRatingsByBookAndUserIds(long userId, long bookId)
    {
        BasicConfigurator.configure();
        RatingCRUD u = new RatingCRUD();
        Rating rating = null;
        String selectParametr = "select * from rating where book = ? and users = ?";
        try 
        {
            PreparedStatement stmt = connection.prepareStatement(selectParametr);
            stmt.setLong(1, bookId);
            stmt.setLong(2, userId);
            ResultSet rs = stmt.executeQuery();
            //while(rs.next())
            rs.next();
            rating = u.readRating(rs.getInt("id"));
            rs.close();
            stmt.close();
            connection.close();
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
        return rating;
    }
}
