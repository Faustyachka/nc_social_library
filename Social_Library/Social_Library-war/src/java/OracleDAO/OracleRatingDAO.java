/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package OracleDAO;

import OracleConnection.Oracle;
import TransferObject.Rating;
import TransferObjectInterface.RatingDAO;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author mazafaka
 */
public class OracleRatingDAO implements RatingDAO{
    public static final Logger log=Logger.getLogger(OracleAuthorDAO.class);
    private Oracle conn1;
    private static final String selectQuery="SELECT * FROM rating WHERE id=?";
    private static final String deleteQuery="DELETE FROM rating WHERE id =?";
    private static final String insertRatingQuery="INSERT INTO rating VALUES (?, ?, ?, ?)";
    private static final String updateRatingQuery="UPDATE rating SET rate=?, users=?, book=? WHERE id=?";
    private Rating rating;

    public void createRating(Rating rating) {
        try
        {
        Connection conn=conn1.getConnection();
        try
        {
            PreparedStatement pstmt = conn.prepareStatement(insertRatingQuery);

            pstmt.setInt(1, rating.getId());
            pstmt.setInt(2, rating.getRate());

            pstmt.executeUpdate();
        }
        finally
        {
             conn.close();
        }
        }
        catch (FileNotFoundException ex) {
            java.util.logging.Logger.getLogger(OracleRatingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }        catch (SQLException e)
        {
            while(e!=null)
            {
                e.printStackTrace();
                e=e.getNextException();
            }
        }
    }

    public Rating readRating(int id) {
        rating = new Rating();
        try
        {
        Connection conn=conn1.getConnection();
        try
        {
            PreparedStatement stmt = conn.prepareStatement(selectQuery);
            
            stmt.setInt(1, rating.getId());

            ResultSet rs=stmt.executeQuery();

            while (rs.next())
            {
                rating.setId(rs.getInt(1));
            }
            rs.close();
        }
        finally
        {
            conn.close();
        }
        }
        catch (FileNotFoundException ex) {
            java.util.logging.Logger.getLogger(OracleRatingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }        catch (SQLException e)
        {
            while(e!=null)
            {
                e.printStackTrace();
                e=e.getNextException();
            }
        }

        return rating;
    }

    public void updateRating(Rating ratingOld, Rating ratingNew) {
        try
        {
        Connection conn=conn1.getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement(updateRatingQuery);

            pstmt.setInt(1, ratingNew.getRate());
            pstmt.setInt(2, ratingOld.getId());

            pstmt.executeUpdate();
        }
        finally
        {
            conn.close();
        }
        }
        catch (FileNotFoundException ex) {
            java.util.logging.Logger.getLogger(OracleRatingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }        catch (SQLException e)
        {
            while(e!=null)
            {
                e.printStackTrace();
                e=e.getNextException();
            }
        }
    }

    public void deleteRating(Rating rating) {
        try
        {
        Connection conn=conn1.getConnection();
        try
        {
            PreparedStatement stmt = conn.prepareStatement(deleteQuery);

            stmt.setInt(1, rating.getId());

            stmt.executeUpdate();
        }
        finally
        {
             conn.close();
        }
        }
        catch (FileNotFoundException ex) {
            java.util.logging.Logger.getLogger(OracleRatingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }        catch (SQLException e)
        {
            while(e!=null)
            {
                e.printStackTrace();
                e=e.getNextException();
            }
        }

    }
}
