/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package OracleDAO;

import OracleConnection.Oracle;
import TransferObject.BookAuthor;
import TransferObjectInterface.BookAuthorDAO;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author mazafaka
 */
public class OracleBookAuthorDAO implements BookAuthorDAO {

    private Oracle conn1;
    private ResultSet rs;
    private Connection conn;
    private static final String selectQuery="SELECT * FROM ? WHERE id=?";
    private static final String deleteQuery="DELETE FROM ? WHERE id =?";
    private static final String insertBookAuthorQuery="INSERT INTO book_author VALUES (?, ?, ?)";
    private static final String updateBookAuthorQuery="UPDATE book_author SET book=?, author=? WHERE id=?";
    private BookAuthor bookauthor;

     public OracleBookAuthorDAO()
    {
        try
        {
        conn=conn1.getConnection();
        }
        catch(IOException e)
        {
            System.out.println("IOExeption:"+e);
        }
        
    }

    public void createBookAuthor(BookAuthor bookauthor) {
        try
         {
            PreparedStatement pstmt = conn.prepareStatement(insertBookAuthorQuery);

            pstmt.setLong(1, bookauthor.getId());

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

    public BookAuthor readBookAuthor(int id) {
        bookauthor=new BookAuthor();
            try
        {
            PreparedStatement stmt = conn.prepareStatement(selectQuery);
            stmt.setString(1, "book_author");
            stmt.setLong(2, bookauthor.getId());

            rs = stmt.executeQuery();
                while (rs.next())
                {
                    bookauthor.setId(rs.getLong(1));
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

        return bookauthor;
    }

    public void updateBookAuthor(BookAuthor bookauthorOld, BookAuthor bookauthorNew) {
        try
        {
            PreparedStatement pstmt = conn.prepareStatement(updateBookAuthorQuery);

            pstmt.setLong(3, bookauthorOld.getId());

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

    public void deleteBookAuthor(BookAuthor bookauthor) {
         try
        {
            PreparedStatement stmt = conn.prepareStatement(deleteQuery);

            stmt.setString(1, "book_author");
            stmt.setLong(2, bookauthor.getId());

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
