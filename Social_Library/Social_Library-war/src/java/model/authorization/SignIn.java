/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model.authorization;
import Controller.Command;
import Controller.ConfigurationManager;
import TransferObject.Users;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author Костя
 */
public class SignIn implements Command {

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = ConfigurationManager.INDEX_PAGE;
        response.setContentType("text/html;charset=UTF-8");
        Users checkingUser = new Users();
        checkingUser.setPassword(request.getParameter("password"));
        checkingUser.setLogin(request.getParameter("login"));
        //here was acces to DAO and find user by Login
        Users findedUser = new Users();
        //findedUser = iDao.findUsersByLogin(checkingUser.getLogin());
        if(findedUser != null){
        if(findedUser.getPassword().equals(checkingUser.getPassword())){
          //Something to do
                    page = ConfigurationManager.INDEX_PAGE;
        } else {
                    page = ConfigurationManager.REGISTR_PAGE;
        }
        } else {
            page = ConfigurationManager.REGISTR_PAGE;
        }

        //////
        return null;
    }
}
