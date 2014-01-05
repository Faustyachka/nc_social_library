/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sociallibrary.iactions;

import com.sociallibrary.entity.Author;
import com.sociallibrary.entity.Library;
import com.sociallibrary.entity.Rating;
import java.util.List;

/**
 *
 * @author Nastya Pavlova
 */
public interface ILibraryActions {

    public List<Library> getAllBooks(int from, int to);
    public List<Library> searchBooksByParameter(String where, String what);
    public List<Library> searchBooksByStringMask(String where, String what);
    public List<Author> getAuthorsList(long bookId);
    public List<Rating> getRatingsList(long bookId);
    public int getAverageRate(long bookId);
}
