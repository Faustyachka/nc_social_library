/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package CRUD;

import Enity_Interfaces.GenreDAO;
import Entity.Genre;
import DAO.OracleDAOFactory;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author mazafaka
 */
public class ImplGenreDAO implements GenreDAO{

    private Connection conn;
    private OracleDAOFactory conn1;
    private ResultSet rs;
    private static final String selectQuery="SELECT * FROM ? WHERE id=?";
    private static final String deleteQuery="DELETE FROM ? WHERE id =?";
    private static final String insertGenreQuery="INSERT INTO genre VALUES (?, ?)";
    private static final String updateGenreQuery="UPDATE genre SET genre=? WHERE id=?";
    private Genre genre;

     public ImplGenreDAO()
    {
        try
        {
        conn=conn1.getConnection();
        }
        catch(IOException e1)
        {
            System.out.println("IOExeption:"+e1);
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

    public void createGenre(Genre genre) {
        try
        {
            PreparedStatement pstmt = conn.prepareStatement(insertGenreQuery);

            pstmt.setInt(1, genre.getId());
            pstmt.setString(2, genre.getGenre());

            pstmt.executeUpdate();
        if (conn != null)
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
            PreparedStatement stmt = conn.prepareStatement(selectQuery);
            stmt.setString(1, "genre");
            stmt.setInt(2, genre.getId());

            rs=stmt.executeQuery();

            while (rs.next())
            {
                genre.setId(rs.getInt(1));
                genre.setGenre(rs.getString(2));
            }
        if (conn != null)
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
            PreparedStatement pstmt = conn.prepareStatement(updateGenreQuery);

            pstmt.setString(1, genreNew.getGenre());
            pstmt.setInt(2, genreOld.getId());

            pstmt.executeUpdate();
        if (conn != null)
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
            PreparedStatement stmt = conn.prepareStatement(deleteQuery);

            stmt.setString(1, "genre");
            stmt.setInt(2, genre.getId());

            stmt.executeUpdate();
        if (conn != null)
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
