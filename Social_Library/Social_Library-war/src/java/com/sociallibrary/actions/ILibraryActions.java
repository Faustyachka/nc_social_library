/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sociallibrary.actions;

import com.sociallibrary.entity.Author;
import com.sociallibrary.entity.Library;
import com.sociallibrary.entity.Rating;
import java.util.List;

/**
 *
 * @author Nastya Pavlova
 */
public interface ILibraryActions {

    public List<Library> BooksList(int from, int to);
    public void AddToLocal(long book_id, long user_id);
}
