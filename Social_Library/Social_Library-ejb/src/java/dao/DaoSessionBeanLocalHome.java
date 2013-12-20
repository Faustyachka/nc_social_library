/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;

/**
 *
 * @author Cotil1ion
 */
public interface DaoSessionBeanLocalHome extends EJBLocalHome {
    
    dao.DaoSessionBeanLocal create() throws CreateException;
}
