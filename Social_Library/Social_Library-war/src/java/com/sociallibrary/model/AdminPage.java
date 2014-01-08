/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sociallibrary.model;

import com.sociallibrary.actions.UsersActions;
import com.sociallibrary.crud.RoleCRUD;
import com.sociallibrary.crud.UserCRUD;
import com.sociallibrary.entity.Role;
import com.sociallibrary.entity.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Антон
 */
public class AdminPage {

    private static List<User> last_search_users_result = null;

    public void assignRolesToUser(long user_id, List<Integer> role_ids){
        User user = new UserCRUD().readUsers(user_id);
        user.freeRoles();
        for(int role_id : role_ids){
            Role role = new RoleCRUD().readRole(role_id);
            if(!user.getRoles().contains(role)) user.getRoles().add(role);
        }
        new UserCRUD().updateUsers(user);
    }

    public void searchUsers(String search_request){
        last_search_users_result = new ArrayList<User>();
        last_search_users_result.addAll(new UsersActions().searchUsersByParam(UsersActions.firstName_param, search_request));
        last_search_users_result.addAll(new UsersActions().searchUsersByParam(UsersActions.lastName_param, search_request));
        last_search_users_result.addAll(new UsersActions().searchUsersByParam(UsersActions.login_param, search_request));
    }

    public static List<User> getSearch_users_result() {
        return last_search_users_result;
    }

    public List<User> getAllUsers(){
        return new UsersActions().getAllUsers();
    }

    public boolean isAdmin(User user){
        for(Role role : user.getRoles())
            if(role.getId()==0) return true;

        return false;
    }

    public boolean isModerator(User user){
        for(Role role : user.getRoles())
            if(role.getId()==1) return true;

        return false;
    }

    public boolean isAdvancedUser(User user){
        for(Role role : user.getRoles())
            if(role.getId()==2) return true;

        return false;
    }

    public boolean isBeginnerUser(User user){
        for(Role role : user.getRoles())
            if(role.getId()==3) return true;

        return false;
    }

}
