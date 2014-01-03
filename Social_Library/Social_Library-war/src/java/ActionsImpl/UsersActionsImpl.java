/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ActionsImpl;

import ActionsInterfaces.UsersActions;
import com.sociallibrary.Entities.Gender;
import com.sociallibrary.Entities.User;
import com.sociallibrary.connection.ConnectionProvider;
import com.sociallibrary.crud.RoleCRUD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mazafaka
 */
public class UsersActionsImpl implements UsersActions {

     private Connection connection;

    public UsersActionsImpl() {
        connection = ConnectionProvider.getConnection();
    }

     public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        try {
                String sqlRequest =
                        "SELECT * FROM Users";
            PreparedStatement ps = connection.prepareStatement(sqlRequest);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setFirstName(rs.getString("FIRST_NAME"));
                user.setLastName(rs.getString("LAST_NAME"));
                user.setEmail(rs.getString("EMAIL"));
                user.setLogin(rs.getString("LOGIN"));
                user.setPassword(rs.getString("PASSWORD"));
                user.setGender(Gender.getGender(rs.getInt("GENDER")));
                user.setConfirmed(rs.getInt("CONFIRMED")==1);
                user.setBanned(rs.getInt("BANNED")==1);
                user.setRegistrationDate(rs.getString("REGISTRATION_DATE"));
                user.setNotify(rs.getInt("NOTIFY")==1);
                //user.setRoles(new RoleCRUD().getRolesByUserId(rs.getLong("id")));
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
    /*
    public static final Logger log = Logger.getLogger(UsersActionsImpl.class);
    private Users users = new Users();

    public UsersActionsImpl() {
        users = new Users();
    }

    public List<Users> searchUsersByParameter(String where, String what) {
        BasicConfigurator.configure();
        UserDAO u = new OracleUsersDAO();
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
*/
  
}
