/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Enity_Interfaces.AuthorDAO;
import Enity_Interfaces.BookAuthorDAO;
import Enity_Interfaces.BookGenreDAO;
import Enity_Interfaces.CatalogDAO;
import Enity_Interfaces.GenreDAO;
import Enity_Interfaces.LibraryDAO;
import Enity_Interfaces.RatingDAO;
import Enity_Interfaces.RoleDAO;
import Enity_Interfaces.UsersDAO;

/**
 *
 * @author mazafaka
 */
public abstract class DAOFactory
{
    public static final int ORACLE =1;

     public abstract AuthorDAO getAuthorDAO ();
     public abstract BookAuthorDAO getBookAuthorDAO();
     public abstract BookGenreDAO getBookGenreDAO();
     public abstract CatalogDAO getCatalogDAO();
     public abstract GenreDAO getGenreDAO();
     public abstract LibraryDAO getLibraryDAO();
     public abstract RatingDAO getRatingDAO();
     public abstract RoleDAO getRoleDAO();
     public abstract UsersDAO getUsersDAO();

    public static DAOFactory getDAOFactory(int whichFactory)
    {

        switch (whichFactory)
        {
            case ORACLE:return new OracleDAOFactory();
            default: return null;
        }
    }
}
