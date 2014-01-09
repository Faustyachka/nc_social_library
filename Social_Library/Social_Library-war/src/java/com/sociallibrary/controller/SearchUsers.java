/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sociallibrary.controller;

import com.sociallibrary.model.AdminPage;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Антон
 */
public class SearchUsers implements Command {

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String search_request = request.getParameter("search_users");
        new AdminPage().searchUsers(search_request);

//        return ConfigurationManager.ADMIN_SEARCH_PAGE;
        return null;
    }

}
