/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sociallibrary.daofactory;

import javax.servlet.ServletException;
import sociallibrary.dao.RoleDAO;
import sociallibrary.daoimpl.RoleDAOImpl;

/**
 *
 * @author Cotil1ion
 */
public class RoleDAOFactory {
    public static RoleDAOImpl create() throws ServletException{
        return (new RoleDAOImpl());
}    
}
