/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Enity_Interfaces;

import Entity.Users;

/**
 *
 * @author mazafaka
 */
public interface UsersDAO {

     public void createUsers(Users users);
     public Users readUsers(int id);
     public void updateUsers(Users usersOld, Users usersNew);
     public void deleteUsers(Users users);

}
