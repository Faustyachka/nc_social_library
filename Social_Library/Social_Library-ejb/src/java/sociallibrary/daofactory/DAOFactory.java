/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sociallibrary.daofactory;

import javax.servlet.ServletException;
import sociallibrary.daoimpl.DAOImpl;
/**
 *
 * @author Cotil1ion
 */
public class DAOFactory {
    public static DAOImpl create() throws ServletException{
        return (new DAOImpl());
}    
}
