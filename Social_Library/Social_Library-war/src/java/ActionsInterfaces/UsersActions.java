/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ActionsInterfaces;

import TransferObject.Users;
import java.util.List;

/**
 *
 * @author mazafaka
 */
public interface UsersActions {

    public Users searchUsersByParameter(String search, String param);
    public String SearchLastName(String lastname);
}
