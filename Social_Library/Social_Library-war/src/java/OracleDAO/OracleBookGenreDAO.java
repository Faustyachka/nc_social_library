/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package OracleDAO;


import OracleConnection.Oracle;
import TransferObject.BookGenre;
import TransferObjectInterface.BookGenreDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;
/**
 *
 * @author mazafaka
 */
public class OracleBookGenreDAO implements BookGenreDAO{
    public static final Logger log=Logger.getLogger(OracleAuthorDAO.class);
    private Oracle conn1;
    private static final String selectQuery="SELECT * FROM book_genre WHERE id=?";
    private static final String deleteQuery="DELETE FROM book_genre WHERE id =?";
     private static final String insertBookGenreQuery="INSERT INTO book_genre VALUES (?, ?, ?)";
    private static final String updateBookGenreQuery="UPDATE book_genre SET book=?, genre=? WHERE id=?";

    public void createBookGenre(BookGenre bookGenre) {
        Connection conn=conn1.getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement(insertBookGenreQuery);

            pstmt.setLong(1, bookGenre.getId());

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

    public BookGenre readBookGenre(int id) {
        BookGenre bookGenre = new BookGenre();
        Connection conn=conn1.getConnection();
        try
        {
            PreparedStatement stmt = conn.prepareStatement(selectQuery);

            stmt.setLong(1, bookGenre.getId());

            ResultSet rs=stmt.executeQuery();

            while (rs.next())
            {
                bookGenre.setId(rs.getLong(1));
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

        return bookGenre;
    }

    public void updateBookGenre(BookGenre bookGenreOld, BookGenre bookGenreNew) {
        Connection conn=conn1.getConnection();
        try
        {
            PreparedStatement pstmt = conn.prepareStatement(updateBookGenreQuery);

            pstmt.setLong(1, bookGenreOld.getId());

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

    public void deleteBookGenre(BookGenre bookGenre) {
        Connection conn=conn1.getConnection();
        try
        {
            PreparedStatement stmt = conn.prepareStatement(deleteQuery);

            stmt.setLong(1, bookGenre.getId());

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
