/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package OracleDAO;


import OracleConnection.Oracle;
import TransferObject.BookGenre;
import TransferObjectInterface.BookGenreDAO;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author mazafaka
 */
public class OracleBookGenreDAO implements BookGenreDAO{

    private Oracle conn1;
    private ResultSet rs;
    private Connection conn;
    private static final String selectQuery="SELECT * FROM ? WHERE id=?";
    private static final String deleteQuery="DELETE FROM ? WHERE id =?";
     private static final String insertBookGenreQuery="INSERT INTO book_genre VALUES (?, ?, ?)";
    private static final String updateBookGenreQuery="UPDATE book_genre SET book=?, genre=? WHERE id=?";
    private BookGenre bookGenre;

     public OracleBookGenreDAO()
    {
        try
        {
        conn=conn1.getConnection();
        }
        catch(IOException e1)
        {
            System.out.println("IOExeption:"+e1);
        }
        
    }

    public void createBookGenre(BookGenre bookGenre) {
        try {
            PreparedStatement pstmt = conn.prepareStatement(insertBookGenreQuery);

            pstmt.setLong(1, bookGenre.getId());

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

    public BookGenre readBookGenre(int id) {
        bookGenre = new BookGenre();
        try
        {
            PreparedStatement stmt = conn.prepareStatement(selectQuery);
            stmt.setString(1, "bookGenre");
            stmt.setLong(2, bookGenre.getId());

            rs=stmt.executeQuery();

            while (rs.next())
            {
                bookGenre.setId(rs.getLong(1));
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

        return bookGenre;
    }

    public void updateBookGenre(BookGenre bookGenreOld, BookGenre bookGenreNew) {
        try
        {
            PreparedStatement pstmt = conn.prepareStatement(updateBookGenreQuery);

            pstmt.setLong(1, bookGenreOld.getId());

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

    public void deleteBookGenre(BookGenre bookGanre) {
        try
        {
            PreparedStatement stmt = conn.prepareStatement(deleteQuery);

            stmt.setString(1, "book_genre");
            stmt.setLong(2, bookGenre.getId());

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