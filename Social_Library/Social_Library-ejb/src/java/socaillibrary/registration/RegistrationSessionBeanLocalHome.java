/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socaillibrary.registration;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;

/**
 *
 * @author Felix
 */
public interface RegistrationSessionBeanLocalHome extends EJBLocalHome {
    
    socaillibrary.registration.RegistrationSessionBeanLocal create() throws CreateException;
}
