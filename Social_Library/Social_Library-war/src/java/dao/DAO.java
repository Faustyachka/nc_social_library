package dao;


import dao.Rating;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.sql.*;
import javax.naming.*;
import javax.servlet.*;

/**
 *
 * @author Felix
 */
public class DAO {
private DataSource dataSource;

    public DAO() throws ServletException {
        if (dataSource != null) {
            return;
        }
        try {
	Context ic = new InitialContext();
	dataSource = (DataSource) ic.lookup("jdbc/test1");
        } catch (NamingException ex) {
            throw new ServletException(
                    "Cannot retrieve java:comp/env/jdbc/HRDB", ex);
        }
    }
     public Connection getConnection() throws ServletException {
        try {
            Connection conn = dataSource.getConnection();
            conn.setAutoCommit(true);
            return conn;
        } catch (SQLException ex) {
            throw new ServletException(
                    "Cannot obtain connection", ex);
        }
    }

    public void releaseConnection(Connection conn) throws ServletException {
        try {
            conn.close();
        } catch (SQLException ex) {
            throw new ServletException(
                    "Cannot release connection", ex);
        }
    }



public void createRating(Rating rating) throws ServletException {
    Connection conn = null;
        try {
            conn = getConnection();

            PreparedStatement pr = conn.prepareStatement("insert into rating(id,rate, user, book) values(rating_id.nextval, ?, ?,?)");
            pr.setInt(1, rating.getRating());
           pr.setInt(2, rating.getUser_id());
            pr.setInt(3, rating.getBook_id());


            pr.executeUpdate();

        } catch (SQLException ex) {
            throw new ServletException("Cannot obtain connection", ex);
        } finally {
            if (conn != null) {
                releaseConnection(conn);
            }
        }

}

public void updateRating(Rating rating) throws ServletException {
    Connection conn = null;
        try {
            conn = getConnection();

            PreparedStatement pr = conn.prepareStatement("update into rating(rate, user, book) values(?, ?,?)");
            pr.setInt(1, rating.getRating());
           pr.setInt(2, rating.getUser_id());
            pr.setInt(3, rating.getBook_id());


            pr.executeUpdate();

        } catch (SQLException ex) {
            throw new ServletException("Cannot obtain connection", ex);
        } finally {
            if (conn != null) {
                releaseConnection(conn);
            }
        }

}


}