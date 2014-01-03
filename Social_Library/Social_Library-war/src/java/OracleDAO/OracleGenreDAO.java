package OracleDAO;

import OracleConnection.Oracle;
import TransferObject.Genre;
import com.sociallibrary.EntitiesInterfaces.GenreDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class OracleGenreDAO implements GenreDAO {

    public static final Logger log = Logger.getLogger(OracleAuthorDAO.class);
    private Oracle conn1 = new Oracle();
    private static final String selectQuery = "SELECT * FROM genre WHERE id=?";
    private static final String deleteQuery = "DELETE FROM genre WHERE id =?";
    private static final String insertGenreQuery = "INSERT INTO genre VALUES (genre_id.nextval, ?)";
    private static final String updateGenreQuery = "UPDATE genre SET genre=? WHERE id=?";

    public void createGenre(Genre genre) {
        BasicConfigurator.configure();
        Connection conn = conn1.getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement(insertGenreQuery);

            pstmt.setString(1, genre.getGenre());

            pstmt.executeUpdate();

            pstmt.close();
                conn.close();
        } catch (SQLException e) {
            while (e != null) {
                log.error("SQLException" + e);
            }
        }
    }

    public Genre readGenre(int id) {
        BasicConfigurator.configure();
        Genre genre = new Genre();
        Connection conn = conn1.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(selectQuery);

            stmt.setInt(1, genre.getId());

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                genre.setId(rs.getInt(1));
                genre.setGenre(rs.getString(2));
            }
            rs.close();
            stmt.close();
                conn.close();
        } catch (SQLException e) {
            while (e != null) {
                log.error("SQLException" + e);
            }
        }

        return genre;
    }

    public void updateGenre(Genre genreOld, Genre genreNew) {
        BasicConfigurator.configure();
        Connection conn = conn1.getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement(updateGenreQuery);

            pstmt.setString(1, genreNew.getGenre());
            pstmt.setInt(2, genreOld.getId());

            pstmt.executeUpdate();

            pstmt.close();
                conn.close();
        } catch (SQLException e) {
            while (e != null) {
                log.error("SQLException" + e);
            }
        }
    }

    public void deleteGenre(Genre genre) {
        BasicConfigurator.configure();
        Connection conn = conn1.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(deleteQuery);

            stmt.setInt(1, genre.getId());

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
