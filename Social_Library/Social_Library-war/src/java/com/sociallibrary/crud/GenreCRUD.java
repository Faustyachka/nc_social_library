package com.sociallibrary.crud;

import com.sociallibrary.Entities.Genre;
import com.sociallibrary.crud.GenderCRUD;
import com.sociallibrary.EntitiesInterfaces.GenreDAO;
import com.sociallibrary.EntitiesInterfaces.LibraryDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class GenreCRUD implements GenreDAO {

    public static final Logger log = Logger.getLogger(GenreCRUD.class);
    private static final String selectQuery = "SELECT * FROM book_genre WHERE id=?";
    private static final String deleteQuery = "DELETE FROM book_genre WHERE id =?";
    private static final String insertBookGenreQuery = "INSERT INTO book_genre VALUES (book_genre_id.nextval, ?, ?)";
    private static final String updateBookGenreQuery = "UPDATE book_genre SET book=?, genre=? WHERE id=?";
/*
    public void createBookGenre(BookGenre bookGenre) {
        BasicConfigurator.configure();
        Connection conn = conn1.getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement(insertBookGenreQuery);

            pstmt.setLong(1, bookGenre.getBook().getId());
            pstmt.setInt(2, bookGenre.getGenre().getId());

            pstmt.executeUpdate();

            pstmt.close();
                conn.close();
        } catch (SQLException e) {
            while (e != null) {
                log.error("SQLException" + e);
            }
        }
    }

    public BookGenre readBookGenre(int id) {
        BasicConfigurator.configure();
        BookGenre bookGenre = new BookGenre();
        Connection conn = conn1.getConnection();
        LibraryDAO l = new OracleLibraryDAO();
        GenreCRUD g = new GenderCRUD();
        try {
            PreparedStatement stmt = conn.prepareStatement(selectQuery);
            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                bookGenre.setId(rs.getLong(1));
                bookGenre.setBook(l.readLibrary(rs.getInt(2)));
                bookGenre.setGenre(g.readGenre(rs.getInt(3)));
            }
            rs.close();
            stmt.close();
                conn.close();
        } catch (SQLException e) {
            while (e != null) {
                log.error("SQLException" + e);
            }
        }

        return bookGenre;
    }

    public void updateBookGenre(BookGenre bookGenreOld, BookGenre bookGenreNew) {
        BasicConfigurator.configure();
        Connection conn = conn1.getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement(updateBookGenreQuery);
            pstmt.setLong(1, bookGenreNew.getBook().getId());
            pstmt.setInt(2, bookGenreNew.getGenre().getId());
            pstmt.setLong(3, bookGenreOld.getId());

            pstmt.executeUpdate();

            pstmt.close();
                conn.close();
        } catch (SQLException e) {
            while (e != null) {
                log.error("SQLException" + e);
            }
        }
    }

    public void deleteBookGenre(BookGenre bookGenre) {
        BasicConfigurator.configure();
        Connection conn = conn1.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(deleteQuery);

            stmt.setLong(1, bookGenre.getId());

            stmt.executeUpdate();

            stmt.close();
                conn.close();
        } catch (SQLException e) {
            while (e != null) {
                log.error("SQLException" + e);
            }
        }
    }
    */

    public void createGenre(Genre genre) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Genre readGenre(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateGenre(Genre genreOld, Genre genreNew) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void deleteGenre(Genre genre) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
