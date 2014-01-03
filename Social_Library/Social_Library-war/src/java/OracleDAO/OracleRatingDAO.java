package OracleDAO;

import OracleConnection.Oracle;
import TransferObject.Rating;
import com.sociallibrary.EntitiesInterfaces.LibraryDAO;
import com.sociallibrary.EntitiesInterfaces.RatingDAO;
import com.sociallibrary.EntitiesInterfaces.UsersDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class OracleRatingDAO implements RatingDAO {

    public static final Logger log = Logger.getLogger(OracleAuthorDAO.class);
    private Oracle conn1 = new Oracle();
    private static final String selectQuery = "SELECT * FROM rating WHERE id=?";
    private static final String deleteQuery = "DELETE FROM rating WHERE id =?";
    private static final String insertRatingQuery = "INSERT INTO rating (ID, Rate, Users, Book) VALUES (rating_id.nextval, ?, ?, ?)";
    private static final String updateRatingQuery = "UPDATE rating SET rate=?, users=?, book=? WHERE id=?";

    public void createRating(Rating rating) {
        BasicConfigurator.configure();
        Connection conn = conn1.getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement(insertRatingQuery);

            pstmt.setInt(1, rating.getRate());
            pstmt.setLong(2, rating.getUsers().getId());
            pstmt.setLong(3, rating.getBook().getId());

            pstmt.executeUpdate();

            pstmt.close();
                conn.close();
        } catch (SQLException e) {
            while (e != null) {
                log.error("SQLException" + e);
            }
        }
    }

    public void createRating(long bookId, long userId, short rate) {
        BasicConfigurator.configure();
        Connection conn = conn1.getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement(insertRatingQuery);

            pstmt.setShort(1, rate);
            pstmt.setLong(2, userId);
            pstmt.setLong(3, bookId);

            pstmt.executeUpdate();

            pstmt.close();
                conn.close();
        } catch (SQLException e) {
            while (e != null) {
                log.error("SQLException" + e);
            }
        }
    }

    public Rating readRating(int id) {
        Rating rating = new Rating();
        int resulSetSize=0;
        BasicConfigurator.configure();
        Connection conn = conn1.getConnection();
        UsersDAO u = new OracleUsersDAO();
        LibraryDAO l = new OracleLibraryDAO();
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
                rating.setUsers(u.readUsers(rs.getInt(3)));
                rating.setBook(l.readLibrary(rs.getInt(4)));
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            while (e != null) {
                log.error("SQLException" + e);
            }
        }
        return rating;
    }

    public void updateRating(Rating ratingOld, Rating ratingNew) {
        BasicConfigurator.configure();
        Connection conn = conn1.getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement(updateRatingQuery);

            pstmt.setInt(1, ratingNew.getRate());
            pstmt.setLong(3, ratingNew.getUsers().getId());
            pstmt.setLong(2, ratingNew.getBook().getId());
            pstmt.setLong(4, ratingOld.getId());

            pstmt.executeUpdate();

            pstmt.close();
                conn.close();
        } catch (SQLException e) {
            while (e != null) {
                log.error("SQLException" + e);
            }
        }
    }

    public void deleteRating(Rating rating) {
        BasicConfigurator.configure();
        Connection conn = conn1.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(deleteQuery);

            stmt.setLong(1, rating.getId());

            stmt.executeUpdate();

            stmt.close();
                conn.close();
        } catch (SQLException e) {
            while (e != null) {
                log.error("SQLException" + e);
            }
        }

    }
}
