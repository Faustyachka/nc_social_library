package sociallibrary.daoimpl;

import sociallibrary.dao.DAO;
import sociallibrary.dto.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.sql.*;
import javax.naming.*;
import javax.servlet.*;
import sociallibrary.entyti.*;

public class DAOImpl implements DAO{
    
    private DataSource dataSource;

    public DAOImpl() throws ServletException {
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
    
    /*
     * GRUD Users
     */
    public void createUsers(Users users) throws ServletException {
        Connection conn = null;
        try {
            conn = getConnection();
            
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO users VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, '11.02.2013', ?, ?)");
            pstmt.setInt(1, users.getId());
            pstmt.setString(2, users.getFirstName());
            pstmt.setString(3, users.getLastName());
            pstmt.setString(4, users.getEmail());
            pstmt.setString(5, users.getLogin());
            pstmt.setString(6, users.getPassword());
            pstmt.setInt(7, users.getGender());
            pstmt.setInt(8, users.getConfirmed());
            pstmt.setInt(9, users.getBanned());
            pstmt.setInt(10, users.getNotify());
            pstmt.setInt(11, users.getRole());
            
            pstmt.executeUpdate();
            
        } catch (SQLException ex) {
            throw new ServletException("Cannot obtain connection", ex);
        } finally {
            if (conn != null) {
                releaseConnection(conn);
            }
        }
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
