package com.sociallibrary.actions;

import com.sociallibrary.entity.*;
import com.sociallibrary.crud.*;
import org.apache.log4j.*;
import com.sociallibrary.connection.ConnectionProvider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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

     public List<Library> BooksList(int from, int to,int workflow)
     {
        BasicConfigurator.configure();
        List<Library> libraries = new ArrayList<Library>();
        String selectParametr = "select id from library where workflow = ? order by id";
        try {
            for(int i = from; i<to; i++)
            {
            PreparedStatement stmt = connection.prepareStatement(selectParametr);
            stmt.setInt(1, workflow);
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                libraries.add(new LibraryCRUD().readLibrary(i));
            }
            rs.close();
            stmt.close();
            }
        }
       catch (SQLException e)
        {
                e.printStackTrace();
                log.error("SQLException:" + e);
        }

        return libraries;
    }

     public void AddToLocal(long book_id, long user_id)
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
            }
    }

     public void RemoveFromLocal(long book_id, long user_id)
     {
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
        }
        
    }

     public boolean CheckLocal(long book_id, long user_id)
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
        
        return result;
    }

     public List<Library> searchBooksByAuthor(String author_name)
     {
        BasicConfigurator.configure();
        Library library = new Library();
        List<Library> libraries = new ArrayList<Library>();
        String selectParametr = "select library.id from library " +
                "inner join book_author on library.id=book_author.author" +
                "inner join author on book_author.author=author.id";
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
       
        return libraries;
    }

     public List<Library> searchBooksByGenre(String genre)
     {
        BasicConfigurator.configure();
        Library library = new Library();
        List<Library> libraries = new ArrayList<Library>();
        String selectParametr="select library.id from library" +
                "inner join book_genre on library.id=book_genre.genre" +
                "inner join genre on book_genre.genre=genre.id";
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
