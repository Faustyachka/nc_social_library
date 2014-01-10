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
 * @author Pavel
 */
class NoCommand implements Command {

    public NoCommand() {
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



        Integer role = null;
        String page = null;

        HttpSession session = request.getSession();
        role = (Integer) session.getAttribute("role");
        page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.NOCOMMAND_PAGE + role);
        return page;
    }
}
