/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sociallibrary.dto;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;

/**
 *
 * @author Felix
 */
public interface RoleGrudLocalHome extends EJBLocalHome {
    
    sociallibrary.dto.RoleGrudLocal create() throws CreateException;
}
