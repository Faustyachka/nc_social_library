/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package OracleDAO;

import OracleConnection.Oracle;
import TransferObject.Genre;
import TransferObjectInterface.GenreDAO;
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
    private static final String selectQuery="SELECT * FROM genre WHERE id=?";
    private static final String deleteQuery="DELETE FROM genre WHERE id =?";
    private static final String insertGenreQuery="INSERT INTO genre VALUES (?, ?)";
    private static final String updateGenreQuery="UPDATE genre SET genre=? WHERE id=?";
    private Genre genre;

    public void createGenre(Genre genre) {
        try
        {
        Connection conn=conn1.getConnection();
        try
        {
            PreparedStatement pstmt = conn.prepareStatement(insertGenreQuery);

            pstmt.setInt(1, genre.getId());
            pstmt.setString(2, genre.getGenre());

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
                e.printStackTrace();
                e=e.getNextException();
            }
        }
    }

    public Genre readGenre(int id) {
        genre = new Genre();
        try
        {
        Connection conn=conn1.getConnection();
        try
        {
            PreparedStatement stmt = conn.prepareStatement(selectQuery);

            stmt.setInt(1, genre.getId());

            ResultSet rs=stmt.executeQuery();

            while (rs.next())
            {
                genre.setId(rs.getInt(1));
                genre.setGenre(rs.getString(2));
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
                e.printStackTrace();
                e=e.getNextException();
            }
        }

        return genre;
    }

    public void updateGenre(Genre genreOld, Genre genreNew) {
        try
        {
        Connection conn=conn1.getConnection();
        try
        {
            PreparedStatement pstmt = conn.prepareStatement(updateGenreQuery);

            pstmt.setString(1, genreNew.getGenre());
            pstmt.setInt(2, genreOld.getId());

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
                e.printStackTrace();
                e=e.getNextException();
            }
        }
    }

    public void deleteGenre(Genre genre) {
        try
        {
        Connection conn=conn1.getConnection();
        try
        {
            PreparedStatement stmt = conn.prepareStatement(deleteQuery);

            stmt.setInt(1, genre.getId());

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
                e.printStackTrace();
                e=e.getNextException();
            }
        }
    }

}
