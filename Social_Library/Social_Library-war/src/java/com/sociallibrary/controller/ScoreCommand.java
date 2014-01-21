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

/**
 *
 * @author П
 */
class ScoreCommand implements Command {

    public ScoreCommand() {
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;

//                HttpSession session=request.getSession(true);
//                session.setAttribute("name", "Pavel!!!");
//         page= ConfigurationManager.SCORE_PAGE;
//        return page;

//        page= ConfigurationManager.LAST_PAGE;
        HttpSession session = request.getSession(true);
        long user_id = Long.parseLong(request.getParameter("user"));
        long book_id = Long.parseLong(request.getParameter("book"));
        int rate = Integer.parseInt(request.getParameter("rate"));
        new RatingActions().addRating(book_id, user_id, rate);
        Integer role = (Integer) session.getAttribute("role");
        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.MAIN_PAGE + role);
    }
}
