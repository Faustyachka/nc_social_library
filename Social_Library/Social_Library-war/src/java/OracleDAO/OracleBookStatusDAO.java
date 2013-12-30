package OracleDAO;

import OracleConnection.Oracle;
import TransferObject.BookStatus;
import TransferObjectInterface.BookStatusDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class OracleBookStatusDAO implements BookStatusDAO {

    public static final Logger log = Logger.getLogger(OracleBookStatusDAO.class);
    private Oracle conn1 = new Oracle();
    private static final String selectQuery = "SELECT * FROM Book_Status WHERE id=?";
    private static final String deleteQuery = "DELETE FROM Book_Status WHERE id =?";
    private static final String insertBookStatusQuery = "INSERT INTO Book_Status VALUES(BookStatus_id.nextval, ?)";
    private static final String updateBookStatusQuery = "UPDATE Book_Status SET Status =? where id=?";

    public BookStatus readBookStatus(int id) {
        BasicConfigurator.configure();
        BookStatus BookStatus = new BookStatus();

        Connection conn = conn1.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(selectQuery);

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                BookStatus.setId(rs.getShort(1));
                BookStatus.setStatus(rs.getString(2));
            }
            rs.close();
            stmt.close();
                conn.close();
        } catch (SQLException e) {
            while (e != null) {
                log.error("SQLException" + e);
            }
        }

        return BookStatus;
    }

    public void createBookStatus(BookStatus BookStatus) {
        BasicConfigurator.configure();
        Connection conn = conn1.getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement(insertBookStatusQuery);

            pstmt.setString(1, BookStatus.getStatus());

            pstmt.executeUpdate();

            pstmt.close();
                conn.close();
        } catch (SQLException e) {
            while (e != null) {
                log.error("SQLException" + e);
            }
        }
    }

    public void deleteBookStatus(BookStatus BookStatus) {
        BasicConfigurator.configure();
        Connection conn = conn1.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(deleteQuery);

            stmt.setInt(1, BookStatus.getId());

            stmt.executeUpdate();

            stmt.close();
                conn.close();
        } catch (SQLException e) {
            while (e != null) {
                log.error("SQLException" + e);
            }
        }
    }

    public void updateBookStatus(BookStatus BookStatusNew, BookStatus BookStatusOld) {
        BasicConfigurator.configure();
        Connection conn = conn1.getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement(updateBookStatusQuery);

            pstmt.setString(1, BookStatusNew.getStatus());
            pstmt.setInt(2, BookStatusOld.getId());

            pstmt.executeUpdate();

            pstmt.close();
                conn.close();
        } catch (SQLException e) {
            while (e != null) {
                log.error("SQLException" + e);
            }
        }
    }
}
