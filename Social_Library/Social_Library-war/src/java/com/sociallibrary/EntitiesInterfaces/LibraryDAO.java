/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sociallibrary.EntitiesInterfaces;

import com.sociallibrary.Entities.*;

/**
 *
 * @author mazafaka
 */
public interface LibraryDAO {

     public void createLibrary(Library library);
     public Library readLibrary(int id);
     public void updateLibrary(Library library);
     public void deleteLibrary(int id);

}
