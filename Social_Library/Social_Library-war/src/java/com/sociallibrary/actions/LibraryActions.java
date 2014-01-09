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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nastya Pavlova
 */
public class LibraryActions implements ILibraryActions
{
    private static Connection connection;
    public static final Logger log = Logger.getLogger(LibraryActions.class);

    public LibraryActions()
    {
        connection = ConnectionProvider.getConnection();
    }

     public List<Library> getAllBooks(int from, int to)
     {
         List<Library> libraries = new ArrayList<Library>();
         for(int i = from; i<to; i++)
         {
            LibraryCRUD library=new LibraryCRUD();
            libraries.add(library.readLibrary(i));
            library=null;
         }
        return libraries;
     }


    public List<Library> searchBooksByParameter(String where, String what)
    {
        BasicConfigurator.configure();
        Library library = new Library();
        ILibraryCRUD ilibrary = new LibraryCRUD();
        List<Library> libraries = new ArrayList<Library>();
        String selectParametr = "select id  from library where "+where+" = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(selectParametr);
            stmt.setString(1, what);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                library = ilibrary.readLibrary(rs.getInt("id"));
                libraries.add(library);
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
        return libraries;
    }
    
    public List<Library> searchBooksByStringMask(String where, String what)
    {
        BasicConfigurator.configure();
        Library library = new Library();
        ILibraryCRUD ilibrary = new LibraryCRUD();
        List<Library> libraries = new ArrayList<Library>();
        String selectParametr = "select *  from library where "+where+" like '"+what+"'";
        try {
             PreparedStatement stmt = connection.prepareStatement(selectParametr);
//            stmt.setString(1, where);
//            stmt.setString(2, what);
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                library = ilibrary.readLibrary(rs.getInt("id"));
                libraries.add(library);
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
        return libraries;
    }

    public List<Author> getAuthorsList(long bookId)
    {
        BasicConfigurator.configure();
        IAuthorCRUD iauthor=new AuthorCRUD();
        List<Author> authors = new ArrayList<Author>();
        String selectParametr = "select * from book_author where book = ?";
        try {

            PreparedStatement stmt = connection.prepareStatement(selectParametr);
            stmt.setLong(1, bookId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                authors.add(iauthor.readAuthor(rs.getInt("author")));
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
        return authors;
    }

    public List<Rating> getRatingsList(long bookId)
    {
        BasicConfigurator.configure();
        IRatingCRUD irating = new RatingCRUD();
        List<Rating> rating = new ArrayList<Rating>();
        String selectParametr = "select *  from rating where book= ?";
        try {

            PreparedStatement stmt = connection.prepareStatement(selectParametr);
            stmt.setLong(1, bookId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                rating.add(irating.readRating(rs.getInt("id")));
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
        return rating;
    }

    public int getAverageRate(long bookId)
    {
        int rate=0;
        List<Rating> ratings = getRatingsList(bookId);
        for(Rating rating1 : ratings)
            rate+=rating1.getRate();

        if(ratings.size()>0)
            return rate/ratings.size();
        return 0;
    }

     
}
