/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sociallibrary.EntitiesInterfaces;

import com.sociallibrary.Entities.*;

/**
 *
 * @author mazafaka
 */
public interface UsersDAO {

     public void createUsers(User users);
     public User readUsers(int id);
     public void updateUsers(User usersOld, User usersNew);
     public void deleteUsers(User users);

}
