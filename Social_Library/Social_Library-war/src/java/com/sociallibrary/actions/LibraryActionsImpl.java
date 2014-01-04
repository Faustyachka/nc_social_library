/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sociallibrary.actions;

import com.sociallibrary.entities.Library;
import com.sociallibrary.connection.ConnectionProvider;
import com.sociallibrary.crud.BookWorkflowCRUD;
import com.sociallibrary.crud.UserCRUD;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
//import org.apache.log4j.BasicConfigurator;
//import org.apache.log4j.Logger;

/**
 *
 * @author Антон
 */
public class LibraryActionsImpl //implements LibraryActions
{
     private Connection connection;

    public LibraryActionsImpl() {
        connection = ConnectionProvider.getConnection();
    }

     public List<Library> getAllBooks()
            throws SQLException, NamingException
    {
        List<Library> libraries = new ArrayList<Library>();

        try {
            connection=ConnectionProvider.getConnection();


            Statement stmt = connection.createStatement ();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Library");

            while (rs.next()) {
                Library library = new Library();
                library.setId(rs.getLong("ID"));
                library.setIsbn(rs.getString("ISBN"));
                library.setTitle(rs.getString("TITLE"));
                library.setCover(rs.getString("COVER"));
                library.setDescription(rs.getString("DESCRIPTION"));
                library.setPages(rs.getInt("PAGES"));
                library.setUser(new UserCRUD().readUsers(rs.getInt("USERS")));
                library.setWorkflow(new BookWorkflowCRUD().readBookWorkflow(rs.getInt("WORKFLOW")));
                libraries.add(library);
            }


            rs.close();
            stmt.close();
    }



        catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException("Error occurred!", e);
        }
        return libraries;

    }
    /*
    public static final Logger log = Logger.getLogger(UsersActionsImpl.class);
    private Library library = new Library();

    public LibraryActionsImpl() {
        library = new Library();
    }

    public List<Library> searchBooksByParameter(String where, String what) {
        BasicConfigurator.configure();
        LibraryDAO u = new LibraryCRUD();
        List<Library> lList = new ArrayList<Library>();
        String selectParametr = "select id  from library where "+where+" = ?";
        try {

            Oracle conn1 = new Oracle();
            Connection conn = conn1.getConnection();
            PreparedStatement stmt = conn.prepareStatement(selectParametr);
            stmt.setString(1, what);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                library = u.readLibrary(rs.getInt("id"));
                lList.add(library);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            while (e != null) {
                log.error("SQLException" + e);
            }
        }

        return lList;
    }
    
    public List<Library> searchBooksByStringMask(String where, String what) {
        BasicConfigurator.configure();
        LibraryDAO u = new OracleLibraryDAO();
        List<Library> lList = new ArrayList<Library>();
        String selectParametr = "select *  from library where "+where+" like '"+what+"'";
        try {

            Oracle conn1 = new Oracle();
            Connection conn = conn1.getConnection();
            PreparedStatement stmt = conn.prepareStatement(selectParametr);
//            stmt.setString(1, where);
//            stmt.setString(2, what);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                library = u.readLibrary(rs.getInt("id"));
                lList.add(library);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            while (e != null) {
                log.error("SQLException" + e);
            }
        }

        return lList;
    }


    public List<Library> getBooksByIdInInterval(long from, long to){
        BasicConfigurator.configure();
        LibraryDAO u = new OracleLibraryDAO();
        Library lib = new Library();
        List<Library> lList = new ArrayList<Library>();
        for(long i = from; i<to; i++){
                lList.add(u.readLibrary(i));        }

        return lList;
    }

    public List<Author> getAuthorsList(long bookId)
    {
        BasicConfigurator.configure();
        AuthorCRUD u = new AuthorCRUD();
        List<Author> lList = new ArrayList<Author>();
        String selectParametr = "select * from book_author where book = ?";
        try {

            Oracle conn1 = new Oracle();
            Connection conn = conn1.getConnection();
            PreparedStatement stmt = conn.prepareStatement(selectParametr);
            stmt.setLong(1, bookId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lList.add(u.readAuthor(rs.getInt("author")));
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            while (e != null) {
                log.error("SQLException" + e);
            }
        }

        return lList;
    }

    public List<Rating> getRatingsList(long bookId){
        BasicConfigurator.configure();
        RatingCRUD u = new RatingCRUD();
        List<Rating> lList = new ArrayList<Rating>();
        String selectParametr = "select *  from rating where book= ?";
        try {

            Oracle conn1 = new Oracle();
            Connection conn = conn1.getConnection();
            PreparedStatement stmt = conn.prepareStatement(selectParametr);
            stmt.setLong(1, bookId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lList.add(u.readRating(rs.getInt("id")));
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            while (e != null) {
                log.error("SQLException" + e);
            }
        }

        return lList;
    }

    public int getAverageRate(long bookId){
        int rate=0;
        List<Rating> ratings = getRatingsList(bookId);
        for(Rating rating : ratings)
            rate+=rating.getRate();

        if(ratings.size()>0)
            return rate/ratings.size();
        return 0;
    }
*/
}