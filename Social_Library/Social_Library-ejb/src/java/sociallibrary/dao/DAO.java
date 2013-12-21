/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.test
 */
package sociallibrary.dao;

import sociallibrary.dto.Role;
/**
 *
 * @author Cotil1ion
 */
public interface DAO {
     public void insert(Role user);
     public void update(Role user);
     public void delete(int roleId);    
}
