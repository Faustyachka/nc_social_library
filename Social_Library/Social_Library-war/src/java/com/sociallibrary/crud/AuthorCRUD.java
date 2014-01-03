 package com.sociallibrary.crud;

import com.sociallibrary.connection.ConnectionProvider;
import com.sociallibrary.Entities.*;
import com.sociallibrary.EntitiesInterfaces.AuthorDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class AuthorCRUD implements AuthorDAO {

    public static final Logger log = Logger.getLogger(AuthorCRUD.class);
    private Oracle conn1 = new Oracle();
    private static final String selectQuery = "SELECT * FROM author WHERE id=?";
    private static final String deleteQuery = "DELETE FROM author WHERE id =?";
    private static final String insertAuthorQuery = "INSERT INTO author VALUES ( author_id.nextval , ?)";
    private static final String updateAuthorQuery = "UPDATE author SET author=? WHERE id=?";

    public void createAuthor(Author author) {
        BasicConfigurator.configure();
            Connection conn = conn1.getConnection();
            try {
                PreparedStatement pstmt = conn.prepareStatement(insertAuthorQuery);
                pstmt.setString(1, author.getAuthor());
                pstmt.executeUpdate();

                conn.close();
                pstmt.close();
            }

         catch (SQLException e) {
            while (e != null) {
                log.error("SQLException" + e);
            }
        }

    }

    public Author readAuthor(int id) {
        BasicConfigurator.configure();
        Author author = new Author();
            Connection conn = conn1.getConnection();
            try {
                PreparedStatement stmt = conn.prepareStatement(selectQuery);

                stmt.setLong(1, id);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    author.setId(rs.getLong(1));
                    author.setAuthor(rs.getString(2));
                }
                rs.close();
                stmt.close();
                conn.close();
            }

         catch (SQLException e) {
            while (e != null) {
                log.error("SQLException" + e);
            }
        }

        return author;
    }

    public void updateAuthor(Author authorOld, Author authorNew) {
        BasicConfigurator.configure();
            Connection conn = conn1.getConnection();
            try {
                PreparedStatement pstmt = conn.prepareStatement(updateAuthorQuery);

                pstmt.setString(1, authorNew.getAuthor());
                pstmt.setLong(2, authorOld.getId());

                pstmt.executeUpdate();

                conn.close();
                pstmt.close();
            }
         catch (SQLException e) {
            while (e != null) {
                log.error("SQLException" + e);
            }
        }
    }

    public void deleteAuthor(Author author) {
        BasicConfigurator.configure();
            Connection conn = conn1.getConnection();
            try {
                PreparedStatement stmt = conn.prepareStatement(deleteQuery);

                stmt.setLong(1, author.getId());

                stmt.executeUpdate();

                stmt.close();
                conn.close();
            }
         catch (SQLException e) {
            while (e != null) {
                log.error("SQLException" + e);
            }
        }
    }
}
