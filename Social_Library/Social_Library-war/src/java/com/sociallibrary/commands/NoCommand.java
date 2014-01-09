/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sociallibrary.commands;

import com.sociallibrary.controller.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Pavel
 */
public class NoCommand implements Command {

    public NoCommand() {
    }


    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page=null;
        page=ConfigurationManager.NOCOMMAND_PAGE;

        return page;
    }

}
