/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sociallibrary.controller;

import com.sociallibrary.actions.RatingActions;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

class ScoreCommand implements Command {

    public ScoreCommand() {
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;

         // TO DO
        long user_id = Long.parseLong(request.getParameter("user_id"));
        long book_id = Long.parseLong(request.getParameter("book_id"));
        int rate = Integer.parseInt(request.getParameter("rate"));
        new RatingActions().addRating(book_id, user_id, rate);
        return page;
    }
}
