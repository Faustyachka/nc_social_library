/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package OracleDAO;

import OracleConnection.Oracle;
import TransferObject.Rating;
import TransferObjectInterface.RatingDAO;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author mazafaka
 */
public class OracleRatingDAO implements RatingDAO{

    private Oracle conn1;
    private ResultSet rs;
    private Connection conn;
    private static final String selectQuery="SELECT * FROM ? WHERE id=?";
    private static final String deleteQuery="DELETE FROM ? WHERE id =?";
    private static final String insertRatingQuery="INSERT INTO rating VALUES (?, ?, ?, ?)";
    private static final String updateRatingQuery="UPDATE rating SET rate=?, users=?, book=? WHERE id=?";
    private Rating rating;

     public OracleRatingDAO()
    {
        try
        {
        conn=conn1.getConnection();
        }
        catch(IOException e1)
        {
            System.out.println("IOExeption:"+e1);
        }
        
    }

    public void createRating(Rating rating) {
        try
        {
            PreparedStatement pstmt = conn.prepareStatement(insertRatingQuery);

            pstmt.setInt(1, rating.getId());
            pstmt.setInt(2, rating.getRate());

            pstmt.executeUpdate();
                conn.close();
            
        }
        catch (SQLException e)
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
            PreparedStatement stmt = conn.prepareStatement(selectQuery);
            stmt.setString(1, "rating");
            stmt.setInt(2, rating.getId());

            rs=stmt.executeQuery();

            while (rs.next())
            {
                rating.setId(rs.getInt(1));
            }
                conn.close();
            
        }
        catch (SQLException e)
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
        try {
            PreparedStatement pstmt = conn.prepareStatement(updateRatingQuery);

            pstmt.setInt(1, ratingNew.getRate());
            pstmt.setInt(2, ratingOld.getId());

            pstmt.executeUpdate();
                conn.close();
            
        }
        catch (SQLException e)
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
            PreparedStatement stmt = conn.prepareStatement(deleteQuery);

            stmt.setString(1, "rating");
            stmt.setInt(2, rating.getId());

            stmt.executeUpdate();
                conn.close();
            
        }
        catch (SQLException e)
        {
            while(e!=null)
            {
                e.printStackTrace();
                e=e.getNextException();
            }
        }

    }

}
