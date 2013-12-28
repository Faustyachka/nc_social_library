/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package OracleDAO;

import OracleConnection.Oracle;
import TransferObject.Rating;
import TransferObjectInterface.RatingDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
   

    public void createRating(Rating rating) {
        
        Connection conn=conn1.getConnection();
        try
        {
            PreparedStatement pstmt = conn.prepareStatement(insertRatingQuery);

            pstmt.setInt(1, rating.getId());
            pstmt.setInt(2, rating.getRate());

            pstmt.executeUpdate();
        }
        catch (SQLException e)
        {
            while(e!=null)
            {
               log.error("SQLException"+e);
            }
        }
    }

    public Rating readRating(int id) {
        Rating rating = new Rating();
        
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
       
        catch (SQLException e)
        {
            while(e!=null)
            {
                log.error("SQLException"+e);
            }
        }

        return rating;
    }

    public void updateRating(Rating ratingOld, Rating ratingNew) {
       
        Connection conn=conn1.getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement(updateRatingQuery);

            pstmt.setInt(1, ratingNew.getRate());
            pstmt.setInt(2, ratingOld.getId());

            pstmt.executeUpdate();
        }
        catch (SQLException e)
        {
            while(e!=null)
            {
               log.error("SQLException"+e);
            }
        }
    }

    public void deleteRating(Rating rating) {
       
        Connection conn=conn1.getConnection();
        try
        {
            PreparedStatement stmt = conn.prepareStatement(deleteQuery);

            stmt.setInt(1, rating.getId());

            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            while(e!=null)
            {
                log.error("SQLException"+e);
            }
        }

    }
}
