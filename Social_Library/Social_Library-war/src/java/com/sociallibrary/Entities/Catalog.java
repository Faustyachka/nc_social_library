/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sociallibrary.entities;

import java.sql.Time;

/**
 *
 * @author Антон
 */
public class Catalog {
    private long id;
    private User user;
    private Library book;
    private Time eventTime;
    private BookStatus status;

    public Catalog() {
    }

    public Catalog(long id) {
        this.id = id;
    }

    public Library getBook() {
        return book;
    }

    public void setBook(Library book) {
        this.book = book;
    }

    public Time getEventTime() {
        return eventTime;
    }

    public void setEventTime(Time eventTime) {
        this.eventTime = eventTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    
}
