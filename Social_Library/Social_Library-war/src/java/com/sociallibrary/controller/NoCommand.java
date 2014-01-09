/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sociallibrary.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Pavel
 */
class NoCommand implements Command {
    int role=2;

    public NoCommand() {
    }


    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page=null;
//       HttpSession session= request.getSession();
//       role=(Integer)session.getAttribute("role");
        Page pag =ConfigurationManager.getPageForRole(role);
        page=pag.get_NOCOMMAND_PAGE();

        return page;
    }

}
