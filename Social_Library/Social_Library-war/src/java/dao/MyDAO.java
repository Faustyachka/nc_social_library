package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.sql.*;
import javax.naming.*;
import javax.servlet.*;

/**
 *
 * @author Felix
 */
public class MyDAO {
    private DataSource dataSource;

    public MyDAO() throws ServletException {
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
    public Role readRole(int id) throws ServletException{
        Connection conn = null;
        Role role = new Role();
        try {
            conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id, name FROM role WHERE id="+id);
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
    public void createRole(Role role) throws ServletException {
        Connection conn = null;
        try {
            conn = getConnection();
            
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO role VALUES(?, ?)");
            pstmt.setInt(1, role.getId());
            pstmt.setString(2, role.getName());
            pstmt.executeUpdate();
            
        } catch (SQLException ex) {
            throw new ServletException("Cannot obtain connection", ex);
        } finally {
            if (conn != null) {
                releaseConnection(conn);
            }
        }
    }
    public void deleteRole(Role role) throws ServletException {
        Connection conn = null;
        try {
            conn = getConnection();
            
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM role WHERE id =?");
            pstmt.setInt(1, role.getId());
            
            pstmt.executeUpdate();
            
        } catch (SQLException ex) {
            throw new ServletException("Cannot obtain connection", ex);
        } finally {
            if (conn != null) {
                releaseConnection(conn);
            }
        }
    }
    public Role updateRole(Role role, String name) throws ServletException {
        Connection conn = null;
        try {
            conn = getConnection();
            
            PreparedStatement pstmt = conn.prepareStatement("UPDATE role SET name =? where id=?");
            pstmt.setString(1, name);
            pstmt.setInt(2, role.getId());
            
            pstmt.executeUpdate();
            
        } catch (SQLException ex) {
            throw new ServletException("Cannot obtain connection", ex);
        } finally {
            if (conn != null) {
                releaseConnection(conn);
            }
        }
        return role;
    }
    
    
}
