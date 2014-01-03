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
public interface UserDAO {

     public void createUsers(User user);
     public User readUsers(int id);
     public void updateUsers(User user);
     public void deleteUsers(int id);

}
