/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sociallibrary.crudInterfaces;

import com.sociallibrary.entities.*;

/**
 *
 * @author mazafaka
 */
public interface IRatingCRUD {

     public void createRating(Rating rating);
     public Rating readRating(int id);
     public void updateRating(Rating ratingOld, Rating ratingNew);
     public void deleteRating(Rating rating);

}