/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Enity_Interfaces;

import Entity.Genre;

/**
 *
 * @author mazafaka
 */
public interface GenreDAO {

     public void createGenre(Genre genre);
     public Genre readGenre(int id);
     public void updateGenre(Genre genreOld, Genre genreNew);
     public void deleteGenre(Genre genre);

}
