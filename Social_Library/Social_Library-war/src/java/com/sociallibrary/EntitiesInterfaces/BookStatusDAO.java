/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sociallibrary.EntitiesInterfaces;
import com.sociallibrary.Entities.*;

/**
 *
 * @author Назар
 */
public interface BookStatusDAO {

    public void createBookStatus(BookStatus BookStatus);
     public BookStatus readBookStatus(int id);
     public void updateBookStatus(BookStatus bookStatusOld, BookStatus bookStatusNew);
     public void deleteBookStatus(BookStatus bookStatus);

}