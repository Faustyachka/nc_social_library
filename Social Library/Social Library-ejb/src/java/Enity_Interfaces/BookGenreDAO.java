/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Enity_Interfaces;

import Entity.BookGenre;

/**
 *
 * @author mazafaka
 */
public interface BookGenreDAO {

    public void createBookGenre(BookGenre bookGenre);
     public BookGenre readBookGenre(int id);
     public void updateBookGenre(BookGenre bookGenreOld, BookGenre bookGenreNew);
     public void deleteBookGenre(BookGenre bookGanre);

}
