/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ActionsImpl;

import ActionsInterfaces.UsersActions;
import OracleConnection.Oracle;
import OracleDAO.OracleUsersDAO;
import TransferObject.Users;
import TransferObjectInterface.UsersDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 *
 * @author mazafaka
 */
public class UsersActionsImpl implements UsersActions {

    public static final Logger log = Logger.getLogger(UsersActionsImpl.class);
    private static final String selectLastName = "select * from users where lastName=?;";
    private Users users = new Users();

    public UsersActionsImpl() {
        users = new Users();
    }

    public List<Users> searchUsersByParameter(String where, String what) {
// Search by parametr "what" in column = "where"
        BasicConfigurator.configure();
        UsersDAO u = new OracleUsersDAO();
        List<Users> uList = new ArrayList<Users>();
        String selectParametr = "select id  from users where "+where+" = ?";
        try {

            Oracle conn1 = new Oracle();
            Connection conn = conn1.getConnection();
            PreparedStatement stmt = conn.prepareStatement(selectParametr);
            stmt.setString(1, what);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                users = u.readUsers(rs.getInt("id"));
                uList.add(users);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            while (e != null) {
                log.error("SQLException" + e);
            }
        }

        return uList;
    }

}
