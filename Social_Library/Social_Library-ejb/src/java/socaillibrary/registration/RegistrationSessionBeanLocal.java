/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socaillibrary.registration;

import javax.ejb.EJBLocalObject;
import sociallibrary.entity.Users;

/**
 *
 * @author Felix
 */
public interface RegistrationSessionBeanLocal extends EJBLocalObject {

    void setRegistration(Users users);

    int add(int a, int b);
    
}
