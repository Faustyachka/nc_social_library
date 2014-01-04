/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sociallibrary.actions;

import com.sociallibrary.entity.*;
import com.sociallibrary.crud.*;
import com.sociallibrary.icrud.*;
import org.apache.log4j.*;
import com.sociallibrary.iactions.ILibraryActions;
import com.sociallibrary.connection.ConnectionProvider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Антон
 */
public class LibraryActions implements ILibraryActions
{
    private Connection connection;
    private Library library;
    private ILibraryCRUD u;
    private IAuthorCRUD u1;
    private IRatingCRUD u2;
    public static final Logger log = Logger.getLogger(LibraryActions.class);

    public LibraryActions()
    {
        connection = ConnectionProvider.getConnection();
        library = new Library();
        u = new LibraryCRUD();
        u1=new AuthorCRUD();
        u2 = new RatingCRUD();
    }

     public List<Library> getAllBooks()
     {
        List<Library> libraries = new ArrayList<Library>();

        try
        {
            Statement stmt = connection.createStatement ();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Library");

            while (rs.next())
            {
                library.setId(rs.getLong("ID"));
                library.setIsbn(rs.getString("ISBN"));
                library.setTitle(rs.getString("TITLE"));
                library.setCover(rs.getString("COVER"));
                library.setDescription(rs.getString("DESCRIPTION"));
                library.setPages(rs.getInt("PAGES"));
                //library.setUser(new UserCRUD().readUsers(rs.getInt("USERS")));
                //library.setWorkflow(new BookWorkflowCRUD().readBookWorkflow(rs.getInt("WORKFLOW")));
                libraries.add(library);
            }
            rs.close();
            stmt.close();
            connection.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return libraries;
    }

    

    public List<Library> searchBooksByParameter(String where, String what) {
        BasicConfigurator.configure();
        List<Library> lList = new ArrayList<Library>();
        String selectParametr = "select id  from library where "+where+" = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(selectParametr);
            stmt.setString(1, what);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                library = u.readLibrary(rs.getInt("id"));
                lList.add(library);
            }
            rs.close();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            while (e != null) {
                log.error("SQLException" + e);
            }
        }

        return lList;
    }
    
    public List<Library> searchBooksByStringMask(String where, String what) {
        BasicConfigurator.configure();
        List<Library> lList = new ArrayList<Library>();
        String selectParametr = "select *  from library where "+where+" like '"+what+"'";
        try {
             PreparedStatement stmt = connection.prepareStatement(selectParametr);
//            stmt.setString(1, where);
//            stmt.setString(2, what);
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                library = u.readLibrary(rs.getInt("id"));
                lList.add(library);
            }
            rs.close();
            stmt.close();
            connection.close();
        } 
        catch (SQLException e)
        {
            while (e != null)
            {
                log.error("SQLException" + e);
            }
        }
        return lList;
    }

    public List<Library> getBooksByIdInInterval(long from, long to)
    {
        BasicConfigurator.configure();
        List<Library> lList = new ArrayList<Library>();
        for(long i = from; i<to; i++)
        {
                lList.add(u.readLibrary((int) i));
        }

        return lList;
    }

    public List<Author> getAuthorsList(long bookId)
    {
        BasicConfigurator.configure();
        
        List<Author> lList = new ArrayList<Author>();
        String selectParametr = "select * from book_author where book = ?";
        try {

            PreparedStatement stmt = connection.prepareStatement(selectParametr);
            stmt.setLong(1, bookId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                lList.add(u1.readAuthor(rs.getInt("author")));
            }
            rs.close();
            stmt.close();
            connection.close();
        } 
        catch (SQLException e)
        {
            while (e != null)
            {
                log.error("SQLException" + e);
            }
        }
        return lList;
    }

    public List<Rating> getRatingsList(long bookId)
    {
        BasicConfigurator.configure();
        
        List<Rating> lList = new ArrayList<Rating>();
        String selectParametr = "select *  from rating where book= ?";
        try {

            PreparedStatement stmt = connection.prepareStatement(selectParametr);
            stmt.setLong(1, bookId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lList.add(u2.readRating(rs.getInt("id")));
            }
            rs.close();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            while (e != null) {
                log.error("SQLException" + e);
            }
        }

        return lList;
    }

    public int getAverageRate(long bookId)
    {
        int rate=0;
        List<Rating> ratings = getRatingsList(bookId);
        for(Rating rating : ratings)
            rate+=rating.getRate();

        if(ratings.size()>0)
            return rate/ratings.size();
        return 0;
    }

     
}
