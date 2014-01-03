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
public interface RatingDAO {

     public void createRating(Rating rating);
     public Rating readRating(int id);
     public void updateRating(Rating ratingOld, Rating ratingNew);
     public void deleteRating(Rating rating);

}
