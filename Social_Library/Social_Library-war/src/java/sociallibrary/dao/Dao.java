package sociallibrary.dao;

import com.sun.xml.rpc.processor.modeler.j2ee.xml.string;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.sql.*;
import javax.naming.*;
import javax.servlet.*;
import sociallibrary.entity.Role;
import sociallibrary.entity.Users;

/**
 *
 * @author Felix
 */
public class Dao {
private DataSource dataSource;

    public Dao() throws ServletException {
        if (dataSource != null) {
            return;
        }
        try {
	Context ic = new InitialContext();
	dataSource = (DataSource) ic.lookup("jdbc/test");
        } catch (NamingException ex) {
            throw new ServletException(
                    "Cannot retrieve java:comp/env/jdbc/HRDB", ex);
        }
    }
     public Connection getConnection() throws ServletException {
        try {
            Connection conn = dataSource.getConnection();
            conn.setAutoCommit(true);
            return conn;
        } catch (SQLException ex) {
            throw new ServletException(
                    "Cannot obtain connection", ex);
        }
    }

    public void releaseConnection(Connection conn) throws ServletException {
        try {
            conn.close();
        } catch (SQLException ex) {
            throw new ServletException(
                    "Cannot release connection", ex);
        }
    }
public List<Role> getRole() throws ServletException {
        List<Role> role = new ArrayList<Role>();
        Connection conn = null;
        try {
            conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM role");
            while (rs.next()) {
                role.add(new Role(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException ex) {
            throw new ServletException("Cannot obtain connection", ex);
        } finally {
            if (conn != null) {
                releaseConnection(conn);
            }
        }
        return role;
    }
public void createRole(Role role) throws ServletException {
    Connection conn = null;
        try {
            conn = getConnection();

            PreparedStatement pr = conn.prepareStatement("insert into role values(?, ?)");
            pr.setInt(1, role.getId());
            pr.setString(2, role.getName());
            pr.executeUpdate();

        } catch (SQLException ex) {
            throw new ServletException("Cannot obtain connection", ex);
        } finally {
            if (conn != null) {
                releaseConnection(conn);
            }
        }

}
public Role viewRole(int id) throws ServletException {
    Role role = new Role();
    Connection conn = null;
        try {
            conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM role where id="+id);
            while (rs.next()) {
                role.setId(rs.getInt(1));
                role.setName(rs.getString(2));
            }
        } catch (SQLException ex) {
            throw new ServletException("Cannot obtain connection", ex);
        } finally {
            if (conn != null) {
                releaseConnection(conn);
            }
        }
        return role;
}
public String getUserName(Users users) throws ServletException {
    Connection conn = null;
        try {
            conn = getConnection();

            PreparedStatement pr = conn.prepareStatement("SELECT firstName FROM USERS WHERE id=1");
            String a ="a";
            return a;
        } catch (SQLException ex) {
            throw new ServletException("Cannot obtain connection", ex);
        } finally {
            if (conn != null) {
                releaseConnection(conn);
            }
        }
}
public void createUsers(Users users) throws ServletException {
    Connection conn = null;
        try {
           conn = getConnection();
           //USERS_
            PreparedStatement pr = conn.prepareStatement("INSERT INTO Users (ID,FIRST_NAME,LAST_NAME,EMAIL,LOGIN,PASSWORD,GENDER,CONFIRMED,BANNED,REGISTRATION_DATE,NOTIFY,ROLE) values(11, 'Beginner', 'user', 'asjdhj@sjv.i', 'login', 'pass', 1, 1, 0, TO_DATE('2015/10/26', 'yyyy/mm/dd'), 1, 1)");
            pr.setInt(1, users.getId());
            pr.setString(2, users.getFirsName());
            pr.setString(3, users.getLastName());
            pr.setString(4, users.getEmail());
            pr.setString(5, users.getLogin());
            pr.setString(6, users.getPassword());
            pr.setInt(7, users.getGender());
            pr.setInt(8, users.getConfirmed());
            pr.setInt(9, users.getBanned());
            pr.setInt(10, users.getNotify());
            pr.setInt(11, users.getRole());

            pr.executeUpdate();

        } catch (SQLException ex) {
            throw new ServletException("Cannot obtain connection", ex);
        } finally {
            if (conn != null) {
                releaseConnection(conn);
            }
        }

}

}