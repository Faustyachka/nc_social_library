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
import java.util.Locale;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.log4j.Logger;

/**
 *
 * @author mazafaka
 */
public class UsersActionsImpl implements UsersActions {

    public static final Logger log = Logger.getLogger(UsersActionsImpl.class);
    private static final String selectParametr = "select id  from users where ? = ?";
    private static final String selectLastName = "select * from users where lastName=?";
    private Users users = new Users();

    public UsersActionsImpl() {
        users = new Users();
    }

//    public List<Users> searchUsersByParameter(String where, String what)

    public Users searchUsersByParameter(String where, String what) {
        UsersDAO u = new OracleUsersDAO();
        List<Users> uList = new ArrayList<Users>();
        try {
            Oracle conn1 = new Oracle();
            Connection conn = conn1.getConnection();
            PreparedStatement stmt = conn.prepareStatement(selectParametr);
            stmt.setString(1, where);
            stmt.setString(2, what);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                users = u.readUsers(rs.getInt(1));
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

        return users;
    }

    public String SearchLastName(String lastname) {
        String new2 = new String("");
        try {
            Locale.setDefault(Locale.ENGLISH);
            Context ic = new InitialContext();
            DataSource dataSource = (DataSource) ic.lookup("jdbc/test");
            Connection conn = dataSource.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement stmt = conn.prepareStatement(selectLastName);
            stmt.setString(1, lastname);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lastname = rs.getString(users.getFirstName());
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (NamingException e) {
            log.error("Cannot retrieve jdbc/test" + e.getMessage());
        } catch (SQLException e) {
            while (e != null) {
                log.error("SQLException" + e);
            }
        }
        return new2;
    }
}
