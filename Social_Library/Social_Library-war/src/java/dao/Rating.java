/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

/**
 *
 * @author П
 */
public class Rating {

    private int rating ;
    private int user_id;
    private int book_id;

    public Rating(int rating, int user_id, int book_id) {
        this.rating = rating;
        this.user_id = user_id;
        this.book_id = book_id;
    }



    public int getBook_id() {
        return book_id;
    }

    public int getRating() {
        return rating;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }







}
