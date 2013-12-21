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
    
    public Users readUsers(int id) throws ServletException{
        Connection conn = null;
        Users users = new Users();
        try {
            conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE id="+id);
            while (rs.next()) {
                users.setId(rs.getInt(1));
                users.setFirstName(rs.getString(2));
                users.setLastName(rs.getString(3));
                users.setEmail(rs.getString(4));
                users.setLogin(rs.getString(5));
                users.setPassword(rs.getString(6));
                users.setGender(rs.getInt(7));
                users.setConfirmed(rs.getInt(8));
                users.setBanned(rs.getInt(9));
                users.setRegistrationDate(rs.getString(10));
                users.setNotify(rs.getInt(11));
                users.setRole(rs.getInt(12));
            }    
        } catch (SQLException ex) {
            throw new ServletException("Cannot obtain connection", ex);
        } finally {
            if (conn != null) {
                releaseConnection(conn);
            }
        }
        return users;
    }
    
    public void updateUsers(Users usersOld, Users usersNew) throws ServletException {
        Connection conn = null;
        try {
            conn = getConnection();
            
            PreparedStatement pstmt = conn.prepareStatement("UPDATE users SET first_name = ?," + 
                        "last_name = ?, email = ?, login = ?, password = ?," + 
                        "gender = ?, confirmed = ?, banned = ?, registration_date = '01.01.2013'," +
                        "notify = ?, role=? WHERE id = ?");
            pstmt.setString(1, usersNew.getFirstName());
            pstmt.setString(2, usersNew.getLastName());
            pstmt.setString(3, usersNew.getEmail());
            pstmt.setString(4, usersNew.getLogin());
            pstmt.setString(5, usersNew.getPassword());
            pstmt.setInt(6, usersNew.getGender());
            pstmt.setInt(7, usersNew.getConfirmed());
            pstmt.setInt(8, usersNew.getBanned());
            pstmt.setInt(9, usersNew.getNotify());
            pstmt.setInt(10, usersNew.getRole());
            pstmt.setInt(11, usersOld.getId());          
      
            pstmt.executeUpdate();
            
        } catch (SQLException ex) {
            throw new ServletException("Cannot obtain connection", ex);
        } finally {
            if (conn != null) {
                releaseConnection(conn);
            }
        }
    }
    
    public void deleteUsers(Users users) throws ServletException {
        Connection conn = null;
        try {
            conn = getConnection();
            
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM users WHERE id=?");
            pstmt.setInt(1, users.getId());
            
            pstmt.executeUpdate();
            
        } catch (SQLException ex) {
            throw new ServletException("Cannot obtain connection", ex);
        } finally {
            if (conn != null) {
                releaseConnection(conn);
            }
        }
    }
    
    /*
     * GRUD Library
     */
    public void createLibrary(Library library) throws ServletException {
        Connection conn = null;
        try {
            conn = getConnection();
            
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO library VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pstmt.setInt(1, library.getId());
            pstmt.setString(2, library.getIsbn());
            pstmt.setString(3, library.getTitle());
            pstmt.setString(4, library.getCover());
            pstmt.setString(5, library.getDescription());
            pstmt.setInt(6, library.getPages());
            pstmt.setInt(7, library.getAuthor());
            pstmt.setInt(8, library.getGanre());
            pstmt.setInt(9, library.getUsers());
            pstmt.executeUpdate();
            
        } catch (SQLException ex) {
            throw new ServletException("Cannot obtain connection", ex);
        } finally {
            if (conn != null) {
                releaseConnection(conn);
            }
        }
    }
    public Library readLibrary(int id) throws ServletException{
        Connection conn = null;
        Library library = new Library();
        try {
            conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM library WHERE id="+id);
            while (rs.next()) {
                library.setId(rs.getInt(1));
                library.setIsbn(rs.getString(2));
                library.setTitle(rs.getString(3));
                library.setCover(rs.getString(4));
                library.setDescription(rs.getString(5));
                library.setPages(rs.getInt(6));
                library.setAuthor(rs.getInt(7));
                library.setGanre(rs.getInt(8));
                library.setUsers(rs.getInt(9));     
            }    
        } catch (SQLException ex) {
            throw new ServletException("Cannot obtain connection", ex);
        } finally {
            if (conn != null) {
                releaseConnection(conn);
            }
        }
        return library;
    }
    
    public void updateLibrary(Library libraryOld, Library libraryNew) throws ServletException {
        Connection conn = null;
        try {
            conn = getConnection();
            
            PreparedStatement pstmt = conn.prepareStatement("UPDATE library SET isbn = ?, title = ?," +
                                            "cover = ?, description = ?, pages = ?," + 
                                            "author = ?, genre = ?, users = ? WHERE id=?");
            pstmt.setString(1, libraryNew.getIsbn());
            pstmt.setString(2, libraryNew.getTitle());
            pstmt.setString(3, libraryNew.getCover());
            pstmt.setString(4, libraryNew.getDescription());
            pstmt.setInt(5, libraryNew.getPages());
            pstmt.setInt(6, libraryNew.getAuthor());
            pstmt.setInt(7, libraryNew.getGanre());
            pstmt.setInt(8, libraryNew.getUsers());
            pstmt.setInt(9, libraryOld.getId());
            pstmt.executeUpdate();            
        } catch (SQLException ex) {
            throw new ServletException("Cannot obtain connection", ex);
        } finally {
            if (conn != null) {
                releaseConnection(conn);
            }
        }
    }
    
    public void deleteLibrary(Library library) throws ServletException {
        Connection conn = null;
        try {
            conn = getConnection();
            
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM library WHERE id=?");
            pstmt.setInt(1, library.getId());
            
            pstmt.executeUpdate();  
        } catch (SQLException ex) {
            throw new ServletException("Cannot obtain connection", ex);
        } finally {
            if (conn != null) {
                releaseConnection(conn);
            }
        }
    }
    
    /*
     * grud Catolog
     */
    public void createCatalog(Catalog catalog) throws ServletException {
        Connection conn = null;
        try {
            conn = getConnection();
            
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO catalog VALUES(?, ?, ?)");
            pstmt.setInt(1, catalog.getId());
            pstmt.setInt(2, catalog.getUsers());
            pstmt.setInt(3, catalog.getBook());        
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