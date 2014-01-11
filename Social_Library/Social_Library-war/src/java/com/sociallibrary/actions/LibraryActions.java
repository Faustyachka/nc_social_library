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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nastya Pavlova
 */
public class LibraryActions implements ILibraryActions


{

      public static String workflow = "workflow";
    public static String workflowInprogres = "1";
    public static String workflowPublished = "4";
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

     public List<Author> getAuthorsOfBook(long book_id)
     {
         List<Author> authors = new ArrayList<Author>();
         BasicConfigurator.configure();
        String selectParametr = "select id from author where id in " +
                                    "(select author from book_author where book in " +
                                        "(select id from library where id=?))";
        try {
            PreparedStatement stmt = connection.prepareStatement(selectParametr);
            stmt.setLong(1, book_id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Author author = new AuthorCRUD().readAuthor(rs.getInt("id"));
                authors.add(author);
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

     public boolean addBookToLocal(long book_id, long user_id)
     {

        Library book = new LibraryCRUD().readLibrary(book_id);
        User user = new UserCRUD().readUser(user_id);
        if(book!=null)
            if(user!=null){
                Catalog catalog = new Catalog();
                catalog.setBook(book);
                catalog.setUser(user);
                catalog.setStatus(new BookStatusCRUD().readBookStatus(0));
                catalog.setEventTime(new Timestamp(new java.util.Date().getTime()));
                new CatalogCRUD().createCatalog(catalog);

                return true;
            }

        return false;
    }

     public boolean removeBookFromLocal(long book_id, long user_id)
     {
        boolean result = true;
        BasicConfigurator.configure();
        String selectParametr = "SELECT id FROM Catalog WHERE users=? AND book=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(selectParametr);
            stmt.setLong(1, user_id);
            stmt.setLong(2, book_id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Catalog catalog = new CatalogCRUD().readCatalog(rs.getInt("id"));
                new CatalogCRUD().deleteCatalog(catalog);
            }
            rs.close();
            stmt.close();
        }
       catch (SQLException e)
        {
                e.printStackTrace();
                log.error("SQLException:" + e);
                result = false;
        }
        finally
        {
            ConnectionProvider.close();
        }

        return result;
    }

     public boolean isBookInLocalLibraryOfUser(long book_id, long user_id)
     {
        boolean result = false;
        BasicConfigurator.configure();
        String selectParametr = "SELECT count(id) FROM Catalog WHERE users=? AND book=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(selectParametr);
            stmt.setLong(1, user_id);
            stmt.setLong(2, book_id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                result = rs.getInt(1) > 0;
            }
            rs.close();
            stmt.close();
        }
       catch (SQLException e)
        {
                e.printStackTrace();
                log.error("SQLException:" + e);
                result = false;
        }
        finally
        {
            ConnectionProvider.close();
        }

        return result;
    }

     public String getBookAuthors(long book_id)
     {
        BasicConfigurator.configure();
        List<Author> authors = new ArrayList<Author>();
        String selectParametr = "select id from author where id in " +
                                    "(select author from book_author where book in " +
                                        "(select id from library where id=?))";
        try {
            PreparedStatement stmt = connection.prepareStatement(selectParametr);
            stmt.setLong(1, book_id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Author author = new AuthorCRUD().readAuthor(rs.getInt("id"));
                authors.add(author);
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
        String result = "";
        for(Author a : authors) result+=a.getAuthor();
        return result;
    }

     public List<Library> getAllBooksByWorkflow(int workflow)
     {
        BasicConfigurator.configure();
        Library library = new Library();
        List<Library> libraries = new ArrayList<Library>();
        String selectParametr = "select id from library where workflow = ? order by id";
        try {
            PreparedStatement stmt = connection.prepareStatement(selectParametr);
            stmt.setInt(1, workflow);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                library = new LibraryCRUD().readLibrary(rs.getInt("id"));
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

     public List<Library> searchBooksByAuthor(String author_name)
     {
        BasicConfigurator.configure();
        Library library = new Library();
        List<Library> libraries = new ArrayList<Library>();
        String selectParametr = "select id from library where id in" +
                                    "(select book from book_author where book_author.author in" +
                                        "(select id from author where upper(author) like upper(?)))";
        try {
            PreparedStatement stmt = connection.prepareStatement(selectParametr);
            stmt.setString(1, "%"+author_name+"%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                library = new LibraryCRUD().readLibrary(rs.getLong("id"));
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

     public List<Library> searchBooksByGenre(String genre)
     {
        BasicConfigurator.configure();
        Library library = new Library();
        List<Library> libraries = new ArrayList<Library>();
        String selectParametr = "select id from library where id in " +
                                    "(select book from book_genre where book_genre.genre in " +
                                        "(select id from genre where upper(genre) like upper(?)));";
        try {
            PreparedStatement stmt = connection.prepareStatement(selectParametr);
            stmt.setString(1, "%"+genre+"%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                library = new LibraryCRUD().readLibrary(rs.getLong("id"));
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

     public List<Library> searchBooksByTitle(String title)
     {
        BasicConfigurator.configure();
        Library library = new Library();
        List<Library> libraries = new ArrayList<Library>();
        String selectParametr = "select id from library where upper(title) like upper(?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(selectParametr);
            stmt.setString(1, "%"+title+"%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                library = new LibraryCRUD().readLibrary(rs.getLong("id"));
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

     public List<Library> searchBooksByDescription(String description)
     {
        BasicConfigurator.configure();
        //Library library = new Library();
        List<Library> libraries = new ArrayList<Library>();
        String selectParametr = "select id from library where upper(description) like upper(?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(selectParametr);
            stmt.setString(1, "%"+description+"%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Library library = new LibraryCRUD().readLibrary(rs.getLong("id"));
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

     public List<Library> getAllLocalBooksByUser(long user_id)
     {
        BasicConfigurator.configure();
        Library library = new Library();
        List<Library> libraries = new ArrayList<Library>();
        String selectParametr = "SELECT Book FROM Catalog WHERE users=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(selectParametr);
            stmt.setLong(1, user_id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                //System.out.println("!@# "+rs.getLong("id"));
                library = new LibraryCRUD().readLibrary(rs.getInt("book"));
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

    public List<Library> searchBooksByParameter(String where, String what)
    {
        BasicConfigurator.configure();
        Library library = new Library();
        List<Library> libraries = new ArrayList<Library>();
        String selectParametr = "select id  from library where "+where+" = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(selectParametr);
            stmt.setString(1, what);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                 ILibraryCRUD ilibrary = new LibraryCRUD();
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

    public int countBooksByParameter(String where, String what)
    {
        BasicConfigurator.configure();
        int result = 0;
        String selectParametr = "select count(id) PARAM from library where "+where+" = "+what;
        //String selectParametr = "select count(id) \"param\" from library where ? = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(selectParametr);
//            stmt.setString(1, where);
//            stmt.setInt(2, what);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                result = rs.getInt("PARAM");
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
        return result;
    }
    
    public List<Library> searchBooksByStringMask(String where, String what)
    {
        BasicConfigurator.configure();
        Library library = new Library();
        List<Library> libraries = new ArrayList<Library>();
        String selectParametr = "select *  from library where "+where+" like '"+what+"'";
        try {
             PreparedStatement stmt = connection.prepareStatement(selectParametr);
//            stmt.setString(1, where);
//            stmt.setString(2, what);
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                library = new LibraryCRUD().readLibrary(rs.getInt("id"));
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
        List<Author> authors = new ArrayList<Author>();
        String selectParametr = "select * from book_author where book = ?";
        try {

            PreparedStatement stmt = connection.prepareStatement(selectParametr);
            stmt.setLong(1, bookId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                authors.add(new AuthorCRUD().readAuthor(rs.getInt("author")));
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
        List<Rating> rating = new ArrayList<Rating>();
        String selectParametr = "select *  from rating where book= ?";
        try {

            PreparedStatement stmt = connection.prepareStatement(selectParametr);
            stmt.setLong(1, bookId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                rating.add(new RatingCRUD().readRating(rs.getInt("id")));
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
