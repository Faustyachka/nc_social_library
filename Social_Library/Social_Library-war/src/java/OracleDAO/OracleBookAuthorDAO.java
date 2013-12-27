/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package OracleDAO;

import OracleConnection.Oracle;
import TransferObject.BookAuthor;
import TransferObjectInterface.BookAuthorDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;

/**
 *
 * @author mazafaka
 */
public class OracleBookAuthorDAO implements BookAuthorDAO {
    public static final Logger log=Logger.getLogger(OracleAuthorDAO.class);
    private Oracle conn1;
    private static final String selectQuery="SELECT * FROM book_author WHERE id=?";
    private static final String deleteQuery="DELETE FROM book_author WHERE id =?";
    private static final String insertBookAuthorQuery="INSERT INTO book_author VALUES (?, ?, ?)";
    private static final String updateBookAuthorQuery="UPDATE book_author SET book=?, author=? WHERE id=?";
    private BookAuthor bookauthor;

    public void createBookAuthor(BookAuthor bookauthor) {
        try
        {
        Connection conn=conn1.getConnection();
        try
         {
            PreparedStatement pstmt = conn.prepareStatement(insertBookAuthorQuery);

            pstmt.setLong(1, bookauthor.getId());

            pstmt.executeUpdate();
        }
        finally
        {
            conn.close();
        }
        }
        catch (SQLException e)
        {
            while(e!=null)
            {
                log.error("SQLException"+e);
            }
        }
    }

    public BookAuthor readBookAuthor(int id) {
        bookauthor=new BookAuthor();
        try
        {
        Connection conn=conn1.getConnection();
            try
            {
            PreparedStatement stmt = conn.prepareStatement(selectQuery);

            stmt.setLong(1, bookauthor.getId());

             ResultSet rs = stmt.executeQuery();
                while (rs.next())
                {
                    bookauthor.setId(rs.getLong(1));
                }
             rs.close();
        }
            finally
            {
                conn.close();
            }
        }
        catch (SQLException e)
        {
            while(e!=null)
            {
                log.error("SQLException"+e);
            }
        }

        return bookauthor;
    }

    public void updateBookAuthor(BookAuthor bookauthorOld, BookAuthor bookauthorNew) {
        try
        {
        Connection conn=conn1.getConnection();
        try
        {
            PreparedStatement pstmt = conn.prepareStatement(updateBookAuthorQuery);

            pstmt.setLong(3, bookauthorOld.getId());

            pstmt.executeUpdate();
        }
        finally
        {
             conn.close();
        }
        }
        catch (SQLException e)
        {
            while(e!=null)
            {
                log.error("SQLException"+e);
            }
        }
    }

    public void deleteBookAuthor(BookAuthor bookauthor) {
        try
        {
        Connection conn=conn1.getConnection();
         try
        {
            PreparedStatement stmt = conn.prepareStatement(deleteQuery);

            stmt.setLong(1, bookauthor.getId());

            stmt.executeUpdate();
        }
         finally
         {
             conn.close();
         }
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
