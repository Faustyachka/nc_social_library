/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import CRUD.ImplAuthorDAO;
import CRUD.ImplBookAuthorDAO;
import CRUD.ImplBookGenreDAO;
import CRUD.ImplCatalogDAO;
import CRUD.ImplGenreDAO;
import CRUD.ImplLibraryDAO;
import CRUD.ImplRatingDAO;
import CRUD.ImplRoleDAO;
import CRUD.ImplUsersDAO;
import Enity_Interfaces.AuthorDAO;
import Enity_Interfaces.BookAuthorDAO;
import Enity_Interfaces.BookGenreDAO;
import Enity_Interfaces.CatalogDAO;
import Enity_Interfaces.GenreDAO;
import Enity_Interfaces.LibraryDAO;
import Enity_Interfaces.RatingDAO;
import Enity_Interfaces.RoleDAO;
import Enity_Interfaces.UsersDAO;
import java.sql.Connection;
import java.util.Locale;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author mazafaka
 */
public class OracleDAOFactory extends DAOFactory
{
    public static Connection conn;
    public static DataSource dataSource;

    public static Connection getConnection ()
            throws java.io.IOException,java.sql.SQLException
    {
        try
        {
            Locale.setDefault(Locale.ENGLISH);
            Context ic = new InitialContext();
            dataSource = (DataSource) ic.lookup("jdbc/test");
            conn = dataSource.getConnection();
            conn.setAutoCommit(true);
        }
        catch (NamingException e)
        {
            System.out.println("Cannot retrieve jdbc/test"+e.getMessage());
        }

        return conn;
    }

    public AuthorDAO getAuthorDAO ()
    {
        return new ImplAuthorDAO();
    }
    public BookAuthorDAO getBookAuthorDAO()
    {
         return new ImplBookAuthorDAO();
    }
     public BookGenreDAO getBookGenreDAO()
     {
        return new ImplBookGenreDAO();
     }
     public CatalogDAO getCatalogDAO()
     {
        return new ImplCatalogDAO();
     }
     public GenreDAO getGenreDAO()
     {
        return new ImplGenreDAO();
     }
     public LibraryDAO getLibraryDAO()
     {
        return new ImplLibraryDAO();
     }
     public RatingDAO getRatingDAO()
     {
        return new ImplRatingDAO();
     }
     public RoleDAO getRoleDAO()
     {
        return new ImplRoleDAO();
     }
     public UsersDAO getUsersDAO()
     {
         return new ImplUsersDAO();
     }
}
