/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package OracleDAO;

import OracleConnection.Oracle;
import TransferObject.Genre;
import TransferObjectInterface.GenreDAO;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author mazafaka
 */
public class OracleGenreDAO implements GenreDAO{

    private Oracle conn1;
    private ResultSet rs;
    private Connection conn;
    private static final String selectQuery="SELECT * FROM ? WHERE id=?";
    private static final String deleteQuery="DELETE FROM ? WHERE id =?";
    private static final String insertGenreQuery="INSERT INTO genre VALUES (?, ?)";
    private static final String updateGenreQuery="UPDATE genre SET genre=? WHERE id=?";
    private Genre genre;

     public OracleGenreDAO()
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

    public void createGenre(Genre genre) {
        try
        {
            PreparedStatement pstmt = conn.prepareStatement(insertGenreQuery);

            pstmt.setInt(1, genre.getId());
            pstmt.setString(2, genre.getGenre());

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

    public Genre readGenre(int id) {
        genre = new Genre();
        try
        {
            PreparedStatement stmt = conn.prepareStatement(selectQuery);
            stmt.setString(1, "genre");
            stmt.setInt(2, genre.getId());

            rs=stmt.executeQuery();

            while (rs.next())
            {
                genre.setId(rs.getInt(1));
                genre.setGenre(rs.getString(2));
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

        return genre;
    }

    public void updateGenre(Genre genreOld, Genre genreNew) {
        try
        {
            PreparedStatement pstmt = conn.prepareStatement(updateGenreQuery);

            pstmt.setString(1, genreNew.getGenre());
            pstmt.setInt(2, genreOld.getId());

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

    public void deleteGenre(Genre genre) {
        try
        {
            PreparedStatement stmt = conn.prepareStatement(deleteQuery);

            stmt.setString(1, "genre");
            stmt.setInt(2, genre.getId());

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
