/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Enity_Interfaces;

import Entity.BookAuthor;

/**
 *
 * @author mazafaka
 */
public interface BookAuthorDAO {

     public void createBookAuthor(BookAuthor bookauthor);
     public BookAuthor readBookAuthor(int id);
     public void updateBookAuthor(BookAuthor bookauthorOld, BookAuthor bookauthorNew);
     public void deleteBookAuthor(BookAuthor bookauthor);

}
