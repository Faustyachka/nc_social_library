package OracleDAO;

import OracleConnection.Oracle;
import TransferObject.BookAuthor;
import TransferObjectInterface.AuthorDAO;
import TransferObjectInterface.BookAuthorDAO;
import TransferObjectInterface.LibraryDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class OracleBookAuthorDAO implements BookAuthorDAO {

    public static final Logger log = Logger.getLogger(OracleBookAuthorDAO.class);
    private Oracle conn1 = new Oracle();
    private static final String selectQuery = "SELECT * FROM book_author WHERE id=?";
    private static final String deleteQuery = "DELETE FROM book_author WHERE id =?";
    private static final String insertBookAuthorQuery = "INSERT INTO book_author VALUES (book_author.nextval, ?, ?)";
    private static final String updateBookAuthorQuery = "UPDATE book_author SET book=?, author=? WHERE id=?";

    public void createBookAuthor(BookAuthor bookauthor) {
        BasicConfigurator.configure();
        Connection conn = conn1.getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement(insertBookAuthorQuery);

            pstmt.setLong(1, bookauthor.getBook().getId());
            pstmt.setLong(2, bookauthor.getAuthor().getId());

            pstmt.executeUpdate();

            pstmt.close();
            conn.close();

        } catch (SQLException e) {
            while (e != null) {
                log.error("SQLException" + e);
            }
        }
    }

    public BookAuthor readBookAuthor(int id) {
        BasicConfigurator.configure();
        BookAuthor bookauthor = new BookAuthor();
        Connection conn = conn1.getConnection();
        AuthorDAO a = new OracleAuthorDAO();
        LibraryDAO l = new OracleLibraryDAO();
        try {
            PreparedStatement stmt = conn.prepareStatement(selectQuery);

            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                bookauthor.setId(rs.getLong(1));
                bookauthor.setBook(l.readLibrary(rs.getInt(2)));
                bookauthor.setAuthor(a.readAuthor(rs.getInt(3)));
            }
            rs.close();
            stmt.close();
                conn.close();
        } catch (SQLException e) {
            while (e != null) {
                log.error("SQLException" + e);
            }
        }

        return bookauthor;
    }

    public void updateBookAuthor(BookAuthor bookauthorOld, BookAuthor bookauthorNew) {
        BasicConfigurator.configure();
        Connection conn = conn1.getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement(updateBookAuthorQuery);
            pstmt.setLong(1, bookauthorNew.getBook().getId());
            pstmt.setLong(2, bookauthorNew.getAuthor().getId());
            pstmt.setLong(3, bookauthorOld.getId());

            pstmt.executeUpdate();

            pstmt.close();
                conn.close();
        } catch (SQLException e) {
            while (e != null) {
                log.error("SQLException" + e);
            }
        }
    }

    public void deleteBookAuthor(BookAuthor bookauthor) {
        BasicConfigurator.configure();
        Connection conn = conn1.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(deleteQuery);

            stmt.setLong(1, bookauthor.getId());

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
