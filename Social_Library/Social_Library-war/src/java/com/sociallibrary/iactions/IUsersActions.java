/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sociallibrary.iactions;

import com.sociallibrary.entity.User;
import java.util.List;

/**
 *
 * @author Nastya Pavlova
 */
public interface IUsersActions {

    public List<User> getAllUsers();
    public User searchUserByLogin(String login);

}
