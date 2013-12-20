package sociallibrary.daoimpl;

import sociallibrary.dao.RoleDAO;
import sociallibrary.dto.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.sql.*;
import javax.naming.*;
import javax.servlet.*;

public class RoleDAOImpl implements RoleDAO{
    
    private DataSource dataSource;

    public RoleDAOImpl() throws ServletException {
        Locale.setDefault(Locale.ENGLISH);
        if (dataSource != null) {
            return;
        }
        try {
	Context ic = new InitialContext();
        // You connection name
	dataSource = (DataSource) ic.lookup("jdbc/test");
        } catch (NamingException ex) {
            throw new ServletException(
                    "Cannot retrieve jdbc/HRDB", ex);
        }
    }
 public Connection getConnection() throws ServletException {
     Locale.setDefault(Locale.ENGLISH);
        try {
            Connection conn = dataSource.getConnection();
            conn.setAutoCommit(false);
            return conn;
        } catch (SQLException ex) {
            throw new ServletException(
                    "Cannot obtain connection", ex);
        }
    }

    public void releaseConnection(Connection conn) throws ServletException {
        Locale.setDefault(Locale.ENGLISH);
        try {
            conn.close();
        } catch (SQLException ex) {
            throw new ServletException(
                    "Cannot release connection", ex);
        }
    }
    
    public List<Role> getAllRole() throws ServletException {
        List<Role> allRole = new ArrayList<Role>();
        Connection conn = null;
        try {
            conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id, name FROM role");
            while (rs.next()) {
                allRole.add(new Role(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException ex) {
            throw new ServletException("Cannot obtain connection", ex);
        } finally {
            if (conn != null) {
                releaseConnection(conn);
            }
        }
        return allRole;
    }
    
    public void insert(Role user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void update(Role user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void delete(int roleId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
