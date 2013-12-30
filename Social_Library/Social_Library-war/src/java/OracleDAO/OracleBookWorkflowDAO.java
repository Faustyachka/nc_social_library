/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package OracleDAO;
import OracleConnection.Oracle;
import TransferObject.BookStatus;
import TransferObject.BookWorkflow;
import TransferObjectInterface.BookStatusDAO;
import TransferObjectInterface.BookWorkflowDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
/**
 *
 * @author Назар
 */
public class OracleBookWorkflowDAO implements BookWorkflowDAO{
    public static final Logger log = Logger.getLogger(OracleBookWorkflowDAO.class);
    private Oracle conn1 = new Oracle();
    private static final String selectQuery = "SELECT * FROM Book_Workflow WHERE id=?";
    private static final String deleteQuery = "DELETE FROM Book_Workflow WHERE id =?";
    private static final String insertBookWorkflowQuery = "INSERT INTO Book_Workflow VALUES(BookWorkflow_id.nextval, ?)";
    private static final String updateBookWorkflowQuery = "UPDATE Book_Workflow SET Workflow =? where id=?";

    public BookWorkflow readBookWorkflow (int id) {
        BasicConfigurator.configure();
        BookWorkflow BookWorkflow = new BookWorkflow();

        Connection conn = conn1.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(selectQuery);

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                BookWorkflow.setId(rs.getInt(1));
                BookWorkflow.setWorkflow(rs.getString(2));
            }
            rs.close();
            stmt.close();
                conn.close();
        } catch (SQLException e) {
            while (e != null) {
                log.error("SQLException" + e);
            }
        }

        return BookWorkflow;
    }

    public void createBookWorkflow(BookWorkflow BookWorkflow) {
        BasicConfigurator.configure();
        Connection conn = conn1.getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement(insertBookWorkflowQuery);

            pstmt.setString(1, BookWorkflow.getWorkflow());

            pstmt.executeUpdate();

            pstmt.close();
                conn.close();
        } catch (SQLException e) {
            while (e != null) {
                log.error("SQLException" + e);
            }
        }
    }

    public void deleteBookWorkflow(BookWorkflow BookWorkflow) {
        BasicConfigurator.configure();
        Connection conn = conn1.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(deleteQuery);

            stmt.setInt(1, BookWorkflow.getId());

            stmt.executeUpdate();
            stmt.close();
                conn.close();
        } catch (SQLException e) {
            while (e != null) {
                log.error("SQLException" + e);
            }
        }
    }

    public void updateBookWorkflow(BookWorkflow BookWorkflowNew, BookWorkflow BookWorkflowOld) {
        BasicConfigurator.configure();
        Connection conn = conn1.getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement(updateBookWorkflowQuery);

            pstmt.setString(1, BookWorkflowNew.getWorkflow());
            pstmt.setInt(2, BookWorkflowOld.getId());

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
