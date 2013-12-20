/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sociallibrary.daofactory;

import sociallibrary.dao.RoleDAO;
import sociallibrary.daoimpl.RoleDAOImpl;

/**
 *
 * @author Cotil1ion
 */
public class RoleDAOFactory {
    public static RoleDAO create() {
return (new RoleDAOImpl());
}    
}
