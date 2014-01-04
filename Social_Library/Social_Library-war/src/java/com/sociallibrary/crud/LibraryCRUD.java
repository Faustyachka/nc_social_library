/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sociallibrary.crud;

import com.sociallibrary.actions.LibraryActions;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.sociallibrary.connection.ConnectionProvider;
import com.sociallibrary.entity.*;
import com.sociallibrary.icrud.ILibraryCRUD;
import java.sql.Statement;
import org.apache.log4j.*;
/**
 *
 * @author Антон
 */
public class LibraryCRUD implements ILibraryCRUD {

    private Connection connection;
    public static final Logger log = Logger.getLogger(LibraryCRUD.class);

    public LibraryCRUD() {
        connection = ConnectionProvider.getConnection();
    }
    
    public void createLibrary(Library library)
    {
        BasicConfigurator.configure();
        try 
        {
            String sqlRequest = "INSERT INTO LIBRARY (ID,ISBN,TITLE,COVER,DESCRIPTION,PAGES,USERs,WORKFLOW) " +
                        "values(?,'?','?','?','?', ?, ?, ?)";

            PreparedStatement ps = connection.prepareStatement(sqlRequest);

            ps.setLong(1, library.getId());
            ps.setString(2, library.getIsbn());
            ps.setString(3, library.getTitle());
            ps.setString(4, library.getCover());
            ps.setString(5, library.getDescription());
            ps.setInt(6, library.getPages());
            ps.setLong(7, library.getUser().getId());
           // ps.setInt(8, library.getWorkflow().getId());
            ps.executeUpdate();

            connection.close();
            ps.close();
        }
        catch (SQLException e)
        {
            log.error("SQLException" + e);
            e.printStackTrace();
        }
    }

    public Library readLibrary(int id)
    {
         Library library = new Library();
         try
         {
             //connection = ConnectionProvider.getConnection();

             PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Library where id= ?");
             stmt.setLong(1, id);

             ResultSet rs = stmt.executeQuery();

             if (rs.next())
             {
                library.setId(rs.getLong("ID"));
                library.setIsbn(rs.getString("ISBN"));
                library.setTitle(rs.getString("TITLE"));
                library.setCover(rs.getString("COVER"));
                library.setDescription(rs.getString("DESCRIPTION"));
                library.setPages(rs.getInt("PAGES"));
               // library.setUser(new UserCRUD().readUsers(rs.getInt("USERS")));
                //library.setWorkflow(new BookWorkflowCRUD().readBookWorkflow(rs.getInt("WORKFLOW")));
            }

           // connection.close();
           // stmt.close();
           // rs.close();

        } 
         catch (SQLException e)
         {
            e.printStackTrace();
         }
        return library;
    }

    public void updateLibrary(Library library)
    {
         try 
         {
             String sqlRequest = "UPDATE Library SET ISBN='?',TITLE='?',COVER='?'," +
                        "DESCRIPTION='?',PAGES=?,USERs=?,WORKFLOW=? WHERE ID=?";
             PreparedStatement ps = connection.prepareStatement(sqlRequest);

             String[] libraryParams = new String[8];
             libraryParams = library.toStringList().toArray(libraryParams);
             for(int i=1; i < 8; i++)
                ps.setString(i, libraryParams[i]);
            ps.setString(8, libraryParams[0]);

            ps.executeUpdate();
            connection.prepareStatement("commit").executeUpdate();

            connection.close();
            ps.close();

        } 
         catch (SQLException e)
         {
            e.printStackTrace();
         }
    }

    public void deleteLibrary(int id) 
    {
        try
        {
            String sqlRequest = "DELETE FROM Library WHERE id=?";
            PreparedStatement ps = connection.prepareStatement(sqlRequest);
            ps.setInt(1, id);
            ps.executeUpdate();

            connection.close();
            ps.close();

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        LibraryActions books=new LibraryActions();
        System.out.println(books.getAllBooks());
    }
}

