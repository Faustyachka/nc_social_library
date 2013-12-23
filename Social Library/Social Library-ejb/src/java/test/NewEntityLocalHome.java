/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;

/**
 *
 * @author Felix
 */
public interface NewEntityLocalHome extends EJBLocalHome {

    test.NewEntityLocal findByPrimaryKey(java.lang.Long key)  throws FinderException;
    
    test.NewEntityLocal create(java.lang.Long key)  throws CreateException;

}
