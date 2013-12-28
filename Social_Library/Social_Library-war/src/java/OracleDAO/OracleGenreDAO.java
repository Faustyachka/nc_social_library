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
import org.apache.log4j.Logger;
/**
 *
 * @author mazafaka
 */
public class OracleGenreDAO implements GenreDAO{
    public static final Logger log=Logger.getLogger(OracleAuthorDAO.class);
    private Oracle conn1;
    private static final String selectQuery="SELECT * FROM genre WHERE id=?";
    private static final String deleteQuery="DELETE FROM genre WHERE id =?";
    private static final String insertGenreQuery="INSERT INTO genre VALUES (?, ?)";
    private static final String updateGenreQuery="UPDATE genre SET genre=? WHERE id=?";

    public void createGenre(Genre genre) {
        Connection conn=conn1.getConnection();
        try
        {
            PreparedStatement pstmt = conn.prepareStatement(insertGenreQuery);

            pstmt.setInt(1, genre.getId());
            pstmt.setString(2, genre.getGenre());

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

    public Genre readGenre(int id) {
        Genre genre = new Genre();
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
        catch (SQLException e)
        {
            while(e!=null)
            {
                log.error("SQLException"+e);
            }
        }

        return genre;
    }

    public void updateGenre(Genre genreOld, Genre genreNew) {
        Connection conn=conn1.getConnection();
        try
        {
            PreparedStatement pstmt = conn.prepareStatement(updateGenreQuery);

            pstmt.setString(1, genreNew.getGenre());
            pstmt.setInt(2, genreOld.getId());

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

    public void deleteGenre(Genre genre) {
      
        Connection conn=conn1.getConnection();
        try
        {
            PreparedStatement stmt = conn.prepareStatement(deleteQuery);

            stmt.setInt(1, genre.getId());

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
