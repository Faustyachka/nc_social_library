/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Enity_Interfaces;

import Entity.Role;

/**
 *
 * @author mazafaka
 */
public interface RoleDAO {

     public Role readRole(int id);
     public void createRole(Role role);
     public void deleteRole(Role role);
     public void updateRole(Role roleNew, Role roleOld);

}
