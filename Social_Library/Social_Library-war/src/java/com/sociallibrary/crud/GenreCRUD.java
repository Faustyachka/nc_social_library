package com.sociallibrary.crud;

import com.sociallibrary.entity.*;
import com.sociallibrary.icrud.*;
import org.apache.log4j.*;
import com.sociallibrary.connection.ConnectionProvider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GenreCRUD implements IGenreCRUD {

    private Connection connection;
    public static final Logger log = Logger.getLogger(GenreCRUD.class);
    private static final String selectQuery = "SELECT * FROM book_genre WHERE id=?";
    private static final String deleteQuery = "DELETE FROM book_genre WHERE id =?";
    private static final String insertBookGenreQuery = "INSERT INTO book_genre VALUES (book_genre_id.nextval, ?, ?)";
    private static final String updateBookGenreQuery = "UPDATE book_genre SET book=?, genre=? WHERE id=?";
    private Genre bookGenre;

     public  GenreCRUD()
    {
        connection = ConnectionProvider.getConnection();
    }

     public void createGenre(Genre genre) {
         BasicConfigurator.configure();
        try {
            PreparedStatement pstmt = connection.prepareStatement(insertBookGenreQuery);

           // pstmt.setLong(1, bookGenre.getBook().getId());
            //pstmt.setInt(2, bookGenre.getGenre().getId());

            pstmt.executeUpdate();

            pstmt.close();
        }
        catch (SQLException e)
        {
                e.printStackTrace();
                log.error("SQLException:" + e);
        }
        finally
        {
            ConnectionProvider.close();
        }
    }

    public Genre readGenre(int id)
    {
        BasicConfigurator.configure();
        bookGenre = new Genre();
        ILibraryCRUD l = new LibraryCRUD();
        //IGenreCRUD g = new GenderCRUD();
        try {
            PreparedStatement stmt = connection.prepareStatement(selectQuery);
            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

               // bookGenre.setId(rs.getLong(1));
               // bookGenre.setBook(l.readLibrary(rs.getInt(2)));
               // bookGenre.setGenre(g.readGenre(rs.getInt(3)));
            }
            rs.close();
            stmt.close();
        }
        catch (SQLException e)
        {
                e.printStackTrace();
                log.error("SQLException:" + e);
        }
        finally
        {
            ConnectionProvider.close();
        }
        return bookGenre;
    }

    public void updateGenre(Genre genreOld, Genre genreNew) {
         BasicConfigurator.configure();
        try {
            PreparedStatement pstmt = connection.prepareStatement(updateBookGenreQuery);
            //pstmt.setLong(1, genreNew.getBook().getId());
            //pstmt.setInt(2, genreNew.getGenre().getId());
            pstmt.setLong(3, genreOld.getId());

            pstmt.executeUpdate();

            pstmt.close();
        }
        catch (SQLException e)
        {
                e.printStackTrace();
                log.error("SQLException:" + e);
        }
        finally
        {
            ConnectionProvider.close();
        }
    }

    public void deleteGenre(Genre genre) {
        BasicConfigurator.configure();
        try {
            PreparedStatement stmt = connection.prepareStatement(deleteQuery);

            stmt.setLong(1, bookGenre.getId());

            stmt.executeUpdate();

            stmt.close();
        }
        catch (SQLException e)
        {
                e.printStackTrace();
                log.error("SQLException:" + e);
        }
        finally
        {
            ConnectionProvider.close();
        }
    }
}
