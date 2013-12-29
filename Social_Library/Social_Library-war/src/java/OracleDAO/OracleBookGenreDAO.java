package OracleDAO;

import OracleConnection.Oracle;
import TransferObject.BookGenre;
import TransferObjectInterface.BookGenreDAO;
import TransferObjectInterface.GenreDAO;
import TransferObjectInterface.LibraryDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class OracleBookGenreDAO implements BookGenreDAO {

    public static final Logger log = Logger.getLogger(OracleBookGenreDAO.class);
    private Oracle conn1 = new Oracle();
    private static final String selectQuery = "SELECT * FROM book_genre WHERE id=?";
    private static final String deleteQuery = "DELETE FROM book_genre WHERE id =?";
    private static final String insertBookGenreQuery = "INSERT INTO book_genre VALUES (book_genre_id.nextval, ?, ?)";
    private static final String updateBookGenreQuery = "UPDATE book_genre SET book=?, genre=? WHERE id=?";

    public void createBookGenre(BookGenre bookGenre) {
        BasicConfigurator.configure();
        Connection conn = conn1.getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement(insertBookGenreQuery);

            pstmt.setLong(1, bookGenre.getBook().getId());
            pstmt.setInt(2, bookGenre.getGenre().getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            while (e != null) {
                log.error("SQLException" + e);
            }
        }
    }

    public BookGenre readBookGenre(int id) {
        BasicConfigurator.configure();
        BookGenre bookGenre = new BookGenre();
        Connection conn = conn1.getConnection();
        LibraryDAO l = new OracleLibraryDAO();
        GenreDAO g = new OracleGenreDAO();
        try {
            PreparedStatement stmt = conn.prepareStatement(selectQuery);
            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                bookGenre.setId(rs.getLong(1));
                bookGenre.setBook(l.readLibrary(rs.getInt(2)));
                bookGenre.setGenre(g.readGenre(rs.getInt(3)));
            }
            rs.close();
        } catch (SQLException e) {
            while (e != null) {
                log.error("SQLException" + e);
            }
        }

        return bookGenre;
    }

    public void updateBookGenre(BookGenre bookGenreOld, BookGenre bookGenreNew) {
        BasicConfigurator.configure();
        Connection conn = conn1.getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement(updateBookGenreQuery);
            pstmt.setLong(1, bookGenreNew.getBook().getId());
            pstmt.setInt(2, bookGenreNew.getGenre().getId());
            pstmt.setLong(3, bookGenreOld.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            while (e != null) {
                log.error("SQLException" + e);
            }
        }
    }

    public void deleteBookGenre(BookGenre bookGenre) {
        BasicConfigurator.configure();
        Connection conn = conn1.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(deleteQuery);

            stmt.setLong(1, bookGenre.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            while (e != null) {
                log.error("SQLException" + e);
            }
        }
    }
}
