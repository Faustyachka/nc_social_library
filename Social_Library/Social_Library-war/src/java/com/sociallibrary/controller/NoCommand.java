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

    private Integer role = null;
    private String page = null;

    public NoCommand() {
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        HttpSession session = request.getSession();



        if (session.getAttribute("role") != null) {
            role = (Integer) session.getAttribute("role");
        } else {
            role = 4;
        }
        Page pag = ConfigurationManager.getPageForRole(role);
        page = pag.get_NOCOMMAND_PAGE();

        return page;
    }
}
