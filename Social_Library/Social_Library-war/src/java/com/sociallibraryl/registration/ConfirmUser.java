/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sociallibraryl.registration;

import com.sociallibrary.controller.Command;
import com.sociallibrary.controller.ConfigurationManager;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sociallibrary.crud.UserCRUD;
import com.sociallibrary.entities.User;
import com.sociallibrary.crudInterfaces.IUserCRUD;

/**
 *
 * @author Костя
 */
public class ConfirmUser implements Command {

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        response.setContentType("text/html;charset=UTF-8");
        UserCRUD userCRUD = new UserCRUD();
        User user= userCRUD.readUsers(Integer.parseInt(request.getParameter("users")));
        user.setConfirmed(true);
        userCRUD.updateUsers(user);
        page = ConfigurationManager.INDEX_PAGE;
        return page;
    }
}