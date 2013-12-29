/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.registration;

import Controller.Command;
import Controller.ConfigurationManager;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import OracleDAO.OracleUsersDAO;
import TransferObject.Users;
import TransferObjectInterface.UsersDAO;

/**
 *
 * @author Костя
 */
public class ConfirmUser implements Command {

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
//        response.setContentType("text/html;charset=UTF-8");
//        UsersDAO users = new OracleUsersDAO();
//        Users usersOld= users.readUsers(Integer.parseInt(request.getParameter("users")));
//        Users usersNew = new Users(usersOld);
//        usersNew.setConfirmed((short)1);
//        users.updateUsers(usersOld, usersNew);
        page = ConfigurationManager.INDEX_PAGE;
        return page;
    }
}