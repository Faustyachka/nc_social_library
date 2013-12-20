/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sociallibrary.test;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;

/**
 *
 * @author Felix
 */
public interface NewSessionBeanLocalHome extends EJBLocalHome {
    
    sociallibrary.test.NewSessionBeanLocal create() throws CreateException;
}
