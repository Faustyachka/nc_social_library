/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.test
 */
package sociallibrary.dao;

import sociallibrary.dto.Role;
import sociallibrary.entity.*;
/**
 *
 * @author Cotil1ion
 */
public interface DAO 
{
     //public Role getAllRole() throws ServletException;
    
    
    /*
     crud for table role in database
     */
     public Role readRole(int id);
     public void createRole(Role role);
     public void deleteRole(Role role);
     public void updateRole(Role roleNew, Role roleOld);
     
     /*
      crud for table users in database
      */
     public void createUsers(Users users);
     public Users readUsers(int id);
     public void updateUsers(Users usersOld, Users usersNew);
     public void deleteUsers(Users users);
     
     /*
      crud for table library in daatabase
      */
     public void createLibrary(Library library);
     public Library readLibrary(int id);
     public void updateLibrary(Library libraryOld, Library libraryNew);
     public void deleteLibrary(Library library);
     
     /*
      crud for table catalog in database
      */
     public void createCatalog(Catalog catalog);
     public Catalog readCatalog(int id);
     public void updateCatalog(Catalog catalogOld, Catalog catalogNew);
     public void deleteCatalog(Catalog catalog);
     
     /*
      crud for table rating in database
      */
     public void createRating(Rating rating);
     public Rating readRating(int id);
     public void updateRating(Rating ratingOld, Rating ratingNew);
     public void deleteRating(Rating rating);
     
     /*
      crud for table author
      */
     public void createAuthor(Author author);
     public Author readAuthor(int id);
     public void updateAuthor(Author authorOld, Author authorNew);
     public void deleteAuthor(Author author);
     
     /*
      crud for table genre
      */
     public void createGenre(Genre genre);
     public Genre readGenre(int id);
     public void updateGenre(Genre genreOld, Genre genreNew);
     public void deleteGenre(Genre genre);
     
     /*
      crud for table book_genre
      */
     public void createBookGenre(BookGenre bookGenre);
     public BookGenre readBookGenre(int id);
     public void updateBookGenre(BookGenre bookGenreOld, BookGenre bookGenreNew);
     public void deleteBookGenre(BookGenre bookGanre);
     
     /*
      crud for table book_author
      */
     public void createBookAuthor(BookAuthor bookauthor);
     public BookAuthor readBookAuthor(int id);
     public void updateBookAuthor(BookAuthor bookauthorOld, BookAuthor bookauthorNew);
     public void deleteBookAuthor(BookAuthor bookauthor);


}
