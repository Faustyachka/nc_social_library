package com.sociallibrary.authorization;

import com.sociallibrary.actions.UsersActions;
import com.sociallibrary.controller.Command;
import com.sociallibrary.controller.ConfigurationManager;
import com.sociallibrary.entity.User;
import com.sociallibrary.registration.SecurityHash;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author Костя
 */
public class SignIn_ implements Command {

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, UnsupportedEncodingException {
        String page = ConfigurationManager.INDEX_PAGE;
        response.setContentType("text/html;charset=UTF-8");
        UsersActions uAction = new UsersActions();
        User user = uAction.searchUserByLogin(request.getParameter("login"));
        if (user.getId()==0) {
            return page;
        } else {
            try {
                if (user.getPassword().equals(SecurityHash.getMd5(request.getParameter("password"))) && user.isConfirmed() && !user.isBanned()) {
                    HttpSession session = request.getSession(true);
                    session.setAttribute("id", user.getId());
                    session.setAttribute("role", user.getRoles());
                    page = (ConfigurationManager.INDEX_PAGE);
                } else {
                    page = ConfigurationManager.ERROR_PAGE;
                }
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(SignIn_.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return page;
    }
}
