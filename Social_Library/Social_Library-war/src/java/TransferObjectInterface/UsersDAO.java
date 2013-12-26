/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package TransferObjectInterface;

import TransferObject.Users;

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
