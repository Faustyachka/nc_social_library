package OracleDAO;

import OracleConnection.Oracle;
import TransferObject.Author;
import TransferObjectInterface.AuthorDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class OracleAuthorDAO implements AuthorDAO {

    public static final Logger log = Logger.getLogger(OracleAuthorDAO.class);
    private Oracle conn1;
    private static final String selectQuery = "SELECT * FROM author WHERE id=?";
    private static final String deleteQuery = "DELETE FROM author WHERE id =?";
    private static final String insertAuthorQuery = "INSERT INTO author VALUES ( author_id.nextval , ?)";
    private static final String updateAuthorQuery = "UPDATE author SET author=? WHERE id=?";

    public void createAuthor(Author author) {
        BasicConfigurator.configure();
        try {

            Connection conn = conn1.getConnection();
            try {
                PreparedStatement pstmt = conn.prepareStatement(insertAuthorQuery);
                pstmt.setString(1, author.getAuthor());
                pstmt.executeUpdate();
            } finally {
                conn.close();
            }

        } catch (SQLException e) {
            while (e != null) {
                log.error("SQLException" + e);
            }
        }

    }

    public Author readAuthor(int id) {
        BasicConfigurator.configure();
        Author author = new Author();
        try {
            Connection conn = conn1.getConnection();
            try {
                PreparedStatement stmt = conn.prepareStatement(selectQuery);

                stmt.setLong(1, id);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    author.setId(rs.getLong(1));
                    author.setAuthor(rs.getString(2));
                }
                rs.close();
            } finally {
                conn.close();
            }

        } catch (SQLException e) {
            while (e != null) {
                log.error("SQLException" + e);
            }
        }

        return author;
    }

    public void updateAuthor(Author authorOld, Author authorNew) {
        BasicConfigurator.configure();
        try {
            Connection conn = conn1.getConnection();
            try {
                PreparedStatement pstmt = conn.prepareStatement(updateAuthorQuery);

                pstmt.setString(1, authorNew.getAuthor());
                pstmt.setLong(2, authorOld.getId());

                pstmt.executeUpdate();
            } finally {
                conn.close();
            }
        } catch (SQLException e) {
            while (e != null) {
                log.error("SQLException" + e);
            }
        }
    }

    public void deleteAuthor(Author author) {
        BasicConfigurator.configure();
        try {
            Connection conn = conn1.getConnection();
            try {
                PreparedStatement stmt = conn.prepareStatement(deleteQuery);

                stmt.setLong(1, author.getId());

                stmt.executeUpdate();
            } finally {
                conn.close();
            }
        } catch (SQLException e) {
            while (e != null) {
                log.error("SQLException" + e);
            }
        }
    }
}
