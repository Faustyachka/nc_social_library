package com.sociallibrary.authorization;

import com.sociallibrary.actions.UsersActions;
import com.sociallibrary.controller.Command;
import com.sociallibrary.controller.ConfigurationManager;
import com.sociallibrary.entity.User;
import com.sociallibrary.registration.Security;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author Костя
 */
/*public class SignIn implements Command {

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, UnsupportedEncodingException {
        String page = ConfigurationManager.INDEX_PAGE;
        response.setContentType("text/html;charset=UTF-8");
        UsersActionsImpl uAction = new UsersActionsImpl();
        User uList = uAction.searchUserByLogin(request.getParameter("login"));
        try {
            if (uList.getPassword().equals(Security.getMd5(request.getParameter("password"))) && uList.isConfirmed() && !uList.isBanned()) {
                HttpSession session = request.getSession(true);
                session.setAttribute("id", uList.getId());
                page = (ConfigurationManager.LOCAL_LIB);
            } else {
                page = ConfigurationManager.INDEX_PAGE;
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(SignIn.class.getName()).log(Level.SEVERE, null, ex);
        }
        return page;
    }
}*/
