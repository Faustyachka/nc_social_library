/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.test
 */
package sociallibrary.dao;

import java.util.List;
import javax.servlet.ServletException;
import sociallibrary.dto.Role;
import sociallibrary.entity.*;
/**
 *
 * @author Cotil1ion
 */
public interface DAO {
   
     public List<Role> getAllRole() throws ServletException;
     public Role readRole(int id) throws ServletException;
     public void createRole(Role role) throws ServletException;
     public void deleteRole(Role role) throws ServletException;
     public Role updateRole(Role role, String name) throws ServletException;
     public void createUsers(Users users) throws ServletException;
     public Users readUsers(int id) throws ServletException;
     public void updateUsers(Users usersOld, Users usersNew) throws ServletException;
     public void deleteUsers(Users users) throws ServletException;
     public void createLibrary(Library library) throws ServletException;
     public Library readLibrary(int id) throws ServletException;
     public void updateLibrary(Library libraryOld, Library libraryNew) throws ServletException;
     public void deleteLibrary(Library library) throws ServletException;
     public void createCatalog(Catalog catalog) throws ServletException;
     public Catalog readCatalog(int id) throws ServletException;
     public void updateCatalog(Catalog catalogOld, Catalog catalogNew) throws ServletException;
     public void deleteCatalog(Catalog catalog) throws ServletException;
     public void createRating(Rating rating) throws ServletException;
     public Rating readRating(int id) throws ServletException;
     public void updateRating(Rating ratingOld, Rating ratingNew) throws ServletException;
     public void deleteRating(Rating rating) throws ServletException;
     public void createBookAuthor(BookAuthor bookauthor) throws ServletException;
     public BookAuthor readBookAuthor(int id) throws ServletException;
     public void updateBookAuthor(BookAuthor bookauthorOld, BookAuthor bookauthorNew) throws ServletException;
     public void deleteBookAuthor(BookAuthor bookauthor) throws ServletException;
     public void createAuthor(Author author) throws ServletException;
     public Author readAuthor(int id) throws ServletException;
      public void updateAuthor(Author authorOld, Author authorNew) throws ServletException;
      public void deleteAuthor(Author author) throws ServletException;
      public void createGenre(Genre genre) throws ServletException;
      public Genre readGenre(int id) throws ServletException;
      public void updateGenre(Genre genreOld, Genre genreNew) throws ServletException ;
      public void deleteGenre(Genre genre) throws ServletException;
      public void createBookGenre(BookGenre bookGanre) throws ServletException;
      public BookGenre readBookGanre(int id) throws ServletException;
      public void updateBookGenre(BookGenre bookGanreOld, BookGenre bookGanreNew) throws ServletException;
      public void deleteBookGenre(BookGenre bookGanre) throws ServletException;
}
