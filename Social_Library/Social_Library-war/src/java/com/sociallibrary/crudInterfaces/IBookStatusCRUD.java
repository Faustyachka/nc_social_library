/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sociallibrary.crudInterfaces;
import com.sociallibrary.entities.*;

/**
 *
 * @author Назар
 */
public interface IBookStatusCRUD {

    public void createBookStatus(BookStatus BookStatus);
     public BookStatus readBookStatus(int id);
     public void updateBookStatus(BookStatus bookStatusOld, BookStatus bookStatusNew);
     public void deleteBookStatus(BookStatus bookStatus);

}