/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sociallibrary.actions;

import com.sociallibrary.entities.Library;
import com.sociallibrary.entities.Rating;
import com.sociallibrary.connection.ConnectionProvider;
import com.sociallibrary.crud.RatingCRUD;
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
 * @author Антон
 */
public class RatingActionsImpl{

    private Connection connection;
    private Library library;

    public RatingActionsImpl() {
        connection = ConnectionProvider.getConnection();
        library = new Library();
    }
    
     public static final Logger log = Logger.getLogger(UsersActionsImpl.class);
    
    public List<Rating> getRatingsByBookId(long id){
        BasicConfigurator.configure();
        RatingCRUD u = new RatingCRUD();
        List<Rating> lList = new ArrayList<Rating>();
        String selectParametr = "select *  from rating where book= ?";
        try {

            connection=ConnectionProvider.getConnection();
            PreparedStatement stmt = connection.prepareStatement(selectParametr);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lList.add(u.readRating(rs.getInt("id")));
            }
            rs.close();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            while (e != null) {
                log.error("SQLException" + e);
            }
        }

        return lList;
    }

    public Rating getRatingsByBookAndUserIds(long userId, long bookId){
        BasicConfigurator.configure();
        RatingCRUD u = new RatingCRUD();
        Rating rating = null;
        String selectParametr = "select * from rating where book = ? and users = ?";
        try {

            connection=ConnectionProvider.getConnection();
            
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
        } catch (SQLException e) {
            while (e != null) {
                log.error("SQLException" + e);
            }
        }

        return rating;
    }

}
