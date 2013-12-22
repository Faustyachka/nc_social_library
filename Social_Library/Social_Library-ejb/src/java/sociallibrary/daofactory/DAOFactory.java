/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sociallibrary.daofactory;

import sociallibrary.daoimpl.DAOImpl;
/**
 *
 * @author Cotil1ion
 */
public class DAOFactory {
    public static DAOImpl create() {
        return (new DAOImpl());
}    
}
