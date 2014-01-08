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

    public NoCommand() {
    }


    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page=null;
        Page pag =ConfigurationManager.getPageForRole(3);
        page=pag.get_NOCOMMAND_PAGE();

        return page;
    }

}
