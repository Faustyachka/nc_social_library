package com.sociallibrary.crud;

import com.sociallibrary.entity.Rating;
import com.sociallibrary.icrud.ILibraryCRUD;
import com.sociallibrary.icrud.IRatingCRUD;
import com.sociallibrary.icrud.IUserCRUD;
import com.sociallibrary.connection.ConnectionProvider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class RatingCRUD implements IRatingCRUD {

    public static final Logger log = Logger.getLogger(RatingCRUD.class);
    private static final String selectQuery = "SELECT * FROM rating WHERE id=?";
    private static final String deleteQuery = "DELETE FROM rating WHERE id =?";
    private static final String insertRatingQuery = "INSERT INTO rating (ID, Rate, Users, Book) VALUES (rating_id.nextval, ?, ?, ?)";
    private static final String updateRatingQuery = "UPDATE rating SET rate=?, users=?, book=? WHERE id=?";

    private Connection conn;

    public RatingCRUD() {
        conn = ConnectionProvider.getConnection();
    }

    public void createRating(Rating rating) {
        BasicConfigurator.configure();
        try {
            PreparedStatement pstmt = conn.prepareStatement(insertRatingQuery);

            pstmt.setInt(1, rating.getRate());
            pstmt.setLong(2, rating.getUser().getId());
            pstmt.setLong(3, rating.getBook().getId());

            pstmt.executeUpdate();

            pstmt.close();
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
    }

    public void createRating(long bookId, long userId, short rate) {
        BasicConfigurator.configure();
        try {
            PreparedStatement pstmt = conn.prepareStatement(insertRatingQuery);

            pstmt.setShort(1, rate);
            pstmt.setLong(2, userId);
            pstmt.setLong(3, bookId);

            pstmt.executeUpdate();

            pstmt.close();
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
    }

    public Rating readRating(int id) {
        Rating rating = new Rating();
        int resulSetSize=0;
        BasicConfigurator.configure();
        IUserCRUD u = new UserCRUD();
        ILibraryCRUD l = new LibraryCRUD();
        try {
            PreparedStatement stmt = conn.prepareStatement(selectQuery);

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();


//            if(!rs.first())
//            {
//                rs.close();
//                stmt.close();
//                conn.close();
//                return null;
//            }
            //rs.beforeFirst();

            while (rs.next()) {
                rating.setId(rs.getLong(1));
                rating.setRate(rs.getShort(2));
                rating.setUser(u.readUsers(rs.getInt(3)));
                rating.setBook(l.readLibrary(rs.getInt(4)));
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
        return rating;
    }

    public void updateRating(Rating ratingOld, Rating ratingNew) {
        BasicConfigurator.configure();
        try {
            PreparedStatement pstmt = conn.prepareStatement(updateRatingQuery);

            pstmt.setInt(1, ratingNew.getRate());
            pstmt.setLong(3, ratingNew.getUser().getId());
            pstmt.setLong(2, ratingNew.getBook().getId());
            pstmt.setLong(4, ratingOld.getId());

            pstmt.executeUpdate();

            pstmt.close();
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
    }

    public void deleteRating(Rating rating) {
        BasicConfigurator.configure();
        try {
            PreparedStatement stmt = conn.prepareStatement(deleteQuery);

            stmt.setLong(1, rating.getId());

            stmt.executeUpdate();

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
    }
}
