/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sociallibrary.icrud;

import com.sociallibrary.entity.*;

/**
 *
 * @author mazafaka
 */
public interface IUserCRUD {

     public void createUsers(User user);
     public User readUsers(long id);
     public void updateUsers(User user);
     public void deleteUsers(int id);

}
