/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sociallibrary.controller;

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



    public ScoreCommand () {
    }



    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page=null;
       
                HttpSession session=request.getSession(true);
                session.setAttribute("name", "Pavel!!!");
         page= ConfigurationManager.SCORE_PAGE;
        return page;
    }

}
