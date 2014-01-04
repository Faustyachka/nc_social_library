/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sociallibrary.iactions;

import com.sociallibrary.entity.Rating;
import java.util.List;

/**
 *
 * @author Nastya Pavlova
 */
public interface IRatingActions {

    public List<Rating> getRatingsByBookId(long id);
    public Rating getRatingsByBookAndUserIds(long userId, long bookId);

}