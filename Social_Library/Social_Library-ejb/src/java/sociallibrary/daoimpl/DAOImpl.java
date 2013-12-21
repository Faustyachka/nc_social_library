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
    
  
    @Override
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

   
    @Override
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
    
    @Override
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
    
    @Override
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
    
    @Override
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
     * CRUD Users
     */
    
    @Override
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
    
    @Override
    public Users readUsers(int id) throws ServletException
    {
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
    
    @Override
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
    
    @Override
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
     * CRUD Library
     */
    
    @Override
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
    
    @Override
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
    
    @Override
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
    
    @Override
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
<<<<<<< HEAD
     * Crud Catolog
=======
     * crud Catolog
>>>>>>> 07ed8152fbc8ada83440d9a2a47245eab25261e3
     */
    
   
    public void createCatalog(Catalog catalog) throws ServletException {
        Connection conn = null;
        try {
            conn = getConnection();
            
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO catalog VALUES(?, ?, ?)");
            pstmt.setInt(1, catalog.getId());
            pstmt.setInt(2, catalog.getUsers());
            pstmt.setInt(3, catalog.getBook());        
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            throw new ServletException("Cannot obtain connection", ex);
        } finally {
            if (conn != null) {
                releaseConnection(conn);
            }
        }
    }
    public Catalog readCatalog(int id) throws ServletException{
        Connection conn = null;
        Catalog catalog = new Catalog();
        try {
            conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM catalog WHERE id="+id);
            while (rs.next()) {
                catalog.setId(rs.getInt(1));
                catalog.setUsers(rs.getInt(2));
                catalog.setBook(rs.getInt(3));
                     
            }    
        } catch (SQLException ex) {
            throw new ServletException("Cannot obtain connection", ex);
        } finally {
            if (conn != null) {
                releaseConnection(conn);
            }
        }
        return catalog;
    }
    
    public void updateCatalog(Catalog catalogOld, Catalog catalogNew) throws ServletException {
        Connection conn = null;
        try {
            conn = getConnection();
            
            PreparedStatement pstmt = conn.prepareStatement("UPDATE catalog SET users=?, book=? WHERE id=?");
            pstmt.setInt(1, catalogNew.getUsers());
            pstmt.setInt(2, catalogNew.getBook());
            pstmt.setInt(3, catalogOld.getId());
            
            pstmt.executeUpdate();            
        } catch (SQLException ex) {
            throw new ServletException("Cannot obtain connection", ex);
        } finally {
            if (conn != null) {
                releaseConnection(conn);
            }
        }
    }
    
    public void deleteCatalog(Catalog catalog) throws ServletException {
        Connection conn = null;
        try {
            conn = getConnection();
            
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM catalog WHERE id=?");
            pstmt.setInt(1, catalog.getId());
            
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
     * crud Rating
     */
    
    public void createRating(Rating rating) throws ServletException {
        Connection conn = null;
        try {
            conn = getConnection();
            
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO rating VALUES (?, ?, ?, ?)");
            pstmt.setInt(1, rating.getId());
            pstmt.setInt(2, rating.getRate());
            pstmt.setInt(3, rating.getUsers());
            pstmt.setInt(4, rating.getBook());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            throw new ServletException("Cannot obtain connection", ex);
        } finally {
            if (conn != null) {
                releaseConnection(conn);
            }
        }
    }
  
    public Rating readRating(int id) throws ServletException{
        Connection conn = null;
        Rating rating = new Rating();
        try {
            conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM rating WHERE id="+id);
            while (rs.next()) {
                rating.setId(rs.getInt(1));
                rating.setRate(rs.getInt(2));
                rating.setUsers(rs.getInt(3));
                rating.setBook(rs.getInt(4));
            }    
        } catch (SQLException ex) {
            throw new ServletException("Cannot obtain connection", ex);
        } finally {
            if (conn != null) {
                releaseConnection(conn);
            }
        }
        return rating;
    }
    
  
    public void updateRating(Rating ratingOld, Rating ratingNew) throws ServletException {
        Connection conn = null;
        try {
            conn = getConnection();
            
            PreparedStatement pstmt = conn.prepareStatement("UPDATE rating SET rate=?, users=?, book=? WHERE id=?");
            pstmt.setInt(1, ratingNew.getRate());
            pstmt.setInt(2, ratingNew.getUsers());
            pstmt.setInt(3, ratingNew.getBook());
            pstmt.setInt(4, ratingOld.getId());
            
            pstmt.executeUpdate();            
        } catch (SQLException ex) {
            throw new ServletException("Cannot obtain connection", ex);
        } finally {
            if (conn != null) {
                releaseConnection(conn);
            }
        }
    }
    
   
    public void deleteRating(Rating rating) throws ServletException {
        Connection conn = null;
        try {
            conn = getConnection();
            
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM rating WHERE id=?");
            pstmt.setInt(1, rating.getId());
            
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
     * crud author
     */
    public void createAuthor(Author author) throws ServletException {
        Connection conn = null;
        try {
            conn = getConnection();
            
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO author VALUES (?, ?)");
            pstmt.setInt(1, author.getId());
            pstmt.setString(2, author.getAuthor());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            throw new ServletException("Cannot obtain connection", ex);
        } finally {
            if (conn != null) {
                releaseConnection(conn);
            }
        }
    }
    public Author readAuthor(int id) throws ServletException{
        Connection conn = null;
        Author author = new Author();
        try {
            conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM author WHERE id="+id);
            while (rs.next()) {
                author.setId(rs.getInt(1));
                author.setAuthor(rs.getString(2));               
            }    
        } catch (SQLException ex) {
            throw new ServletException("Cannot obtain connection", ex);
        } finally {
            if (conn != null) {
                releaseConnection(conn);
            }
        }
        return author;
    }
    public void updateAuthor(Author authorOld, Author authorNew) throws ServletException {
        Connection conn = null;
        try {
            conn = getConnection();
            
            PreparedStatement pstmt = conn.prepareStatement("UPDATE author SET author=? WHERE id=?");
            pstmt.setString(1, authorNew.getAuthor());
            pstmt.setInt(2, authorOld.getId());
            pstmt.executeUpdate();            
        } catch (SQLException ex) {
            throw new ServletException("Cannot obtain connection", ex);
        } finally {
            if (conn != null) {
                releaseConnection(conn);
            }
        }
    }
    public void deleteAuthor(Author author) throws ServletException {
        Connection conn = null;
        try {
            conn = getConnection();
            
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM author WHERE id=?");
            pstmt.setInt(1, author.getId());
            
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
     * crud genre
     */
    public void createGenre(Genre genre) throws ServletException {
        Connection conn = null;
        try {
            conn = getConnection();
            
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO genre VALUES (?, ?)");
            pstmt.setInt(1, genre.getId());
            pstmt.setString(2, genre.getGenre());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            throw new ServletException("Cannot obtain connection", ex);
        } finally {
            if (conn != null) {
                releaseConnection(conn);
            }
        }
    }
    
    public Genre readGenre(int id) throws ServletException{
        Connection conn = null;
        Genre genre = new Genre();
        try {
            conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM genre WHERE id="+id);
            while (rs.next()) {
                genre.setId(rs.getInt(1));
                genre.setGenre(rs.getString(2));               
            }    
        } catch (SQLException ex) {
            throw new ServletException("Cannot obtain connection", ex);
        } finally {
            if (conn != null) {
                releaseConnection(conn);
            }
        }
        return genre;
    }
    
    public void updateGenre(Genre genreOld, Genre genreNew) throws ServletException {
        Connection conn = null;
        try {
            conn = getConnection();
            
            PreparedStatement pstmt = conn.prepareStatement("UPDATE genre SET genre=? WHERE id=?");
            pstmt.setString(1, genreNew.getGenre());
            pstmt.setInt(2, genreOld.getId());
            pstmt.executeUpdate();            
        } catch (SQLException ex) {
            throw new ServletException("Cannot obtain connection", ex);
        } finally {
            if (conn != null) {
                releaseConnection(conn);
            }
        }
    }
    
    public void deleteGenre(Genre genre) throws ServletException {
        Connection conn = null;
        try {
            conn = getConnection();
            
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM genre WHERE id=?");
            pstmt.setInt(1, genre.getId());
            
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
     * crud book_genre
     */
    public void createBookGanre(BookGanre bookGanre) throws ServletException {
        Connection conn = null;
        try {
            conn = getConnection();
            
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO book_genre VALUES (?, ?, ?)");
            pstmt.setInt(1, bookGanre.getId());
            pstmt.setInt(2, bookGanre.getBook());
            pstmt.setInt(3, bookGanre.getGanre());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            throw new ServletException("Cannot obtain connection", ex);
        } finally {
            if (conn != null) {
                releaseConnection(conn);
            }
        }
    }
    
    public BookGanre readBookGanre(int id) throws ServletException{
        Connection conn = null;
        BookGanre bookGanre = new BookGanre();
        try {
            conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM book_genre WHERE id="+id);
            while (rs.next()) {
                bookGanre.setId(rs.getInt(1));
                bookGanre.setBook(rs.getInt(2));
                bookGanre.setGanre(rs.getInt(3));
            }    
        } catch (SQLException ex) {
            throw new ServletException("Cannot obtain connection", ex);
        } finally {
            if (conn != null) {
                releaseConnection(conn);
            }
        }
        return bookGanre;
    }
    
    public void updateBookGenre(BookGanre bookGanreOld, BookGanre bookGanreNew) throws ServletException {
        Connection conn = null;
        try {
            conn = getConnection();
            
            PreparedStatement pstmt = conn.prepareStatement("UPDATE book_genre SET book=?, genre=? WHERE id=?");
            pstmt.setInt(1, bookGanreNew.getBook());
            pstmt.setInt(2, bookGanreNew.getGanre());
            pstmt.setInt(3, bookGanreOld.getId());
            pstmt.executeUpdate();            
        } catch (SQLException ex) {
            throw new ServletException("Cannot obtain connection", ex);
        } finally {
            if (conn != null) {
                releaseConnection(conn);
            }
        }
    }
    
    public void deleteBookGenre(BookGanre bookGanre) throws ServletException {
        Connection conn = null;
        try {
            conn = getConnection();
            
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM book_genre WHERE id=?");
            pstmt.setInt(1, bookGanre.getId());
            
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
     * crud book_author
     */
     
    public void createBookAuthor(BookAuthor bookauthor) throws ServletException
    {
         Connection conn = null;
        try {
            conn = getConnection();
            
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO book_author VALUES (?, ?, ?)");
           
            pstmt.setInt(1, bookauthor.getId());
            pstmt.setInt(2,bookauthor.getBook());
            pstmt.setInt(2,bookauthor.getAuthor());
            pstmt.executeUpdate();
            
        } catch (SQLException ex) {
            throw new ServletException("Cannot obtain connection", ex);
        } finally {
            if (conn != null) {
                releaseConnection(conn);
            }
        }
    }
    
    
     public BookAuthor readBookAuthor(int id) throws ServletException
     {
         Connection conn = null;
         BookAuthor bookauthor=new BookAuthor();
        try {
            conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM book_author WHERE id="+id);
            while (rs.next()) {
               
                bookauthor.setId(rs.getInt(1));
                bookauthor.setBook(rs.getInt(2));
                bookauthor.setAuthor(rs.getInt(3));
            }    
        } catch (SQLException ex) {
            throw new ServletException("Cannot obtain connection", ex);
        } finally {
            if (conn != null) {
                releaseConnection(conn);
            }
        }
        return bookauthor;
     }
    
    
    public void updateBookAuthor(BookAuthor bookauthorOld, BookAuthor bookauthorNew) throws ServletException
    {
        Connection conn = null;
        try {
            conn = getConnection();
            
            PreparedStatement pstmt = conn.prepareStatement("UPDATE booka_uthor SET book=?, author=? WHERE id=?");
            
            pstmt.setInt(1,bookauthorNew.getBook() );
            pstmt.setInt(2,bookauthorNew.getAuthor() );
            pstmt.setInt(3, bookauthorOld.getId());
            
            pstmt.executeUpdate();            
        } catch (SQLException ex) {
            throw new ServletException("Cannot obtain connection", ex);
        } finally {
            if (conn != null) {
                releaseConnection(conn);
            }
        }
    }
    
    
    public void deleteBookAuthor(BookAuthor bookauthor) throws ServletException
    {
        Connection conn = null;
        try {
            conn = getConnection();
            
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM book_author WHERE id=?");
            pstmt.setInt(1, bookauthor.getId());
            
            pstmt.executeUpdate();  
        } catch (SQLException ex) {
            throw new ServletException("Cannot obtain connection", ex);
        } finally {
            if (conn != null) {
                releaseConnection(conn);
            }
        }
    }

}
