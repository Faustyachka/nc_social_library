package sociallibrary.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.sql.DataSource;
import sociallibrary.dao.DAO;
import sociallibrary.dto.Role;
import sociallibrary.entity.*;

/*
   Class CRUD
 */

public class DAOImpl implements DAO{
    
    private DataSource dataSource;
    private Role role;
    private Connection conn;
    private Users users;
    private Library library;
    private Catalog catalog;
    private Rating rating;
    private Author author;
    private Genre genre;
    private BookGenre bookGenre;
    private BookAuthor bookauthor;
    private ResultSet rs;
    private static final String selectQuery="SELECT * FROM ? WHERE id= ?";
    private static final String deleteQuery="DELETE FROM ? WHERE id =?";
    
    /*
     Constructor connect to connection pool
    */
    public DAOImpl() throws ServletException 
    {
        Locale.setDefault(Locale.ENGLISH);
        if (dataSource != null) 
        {
            return;
        }
        try 
        {
	Context ic = new InitialContext();
	dataSource = (DataSource) ic.lookup("jdbc/test");
        } 
        catch (NamingException ex) 
        {
            throw new ServletException("Cannot retrieve jdbc/HRDB", ex);
        }
     }
    
    
    /*
     function establishes connection to connection pool
     */
     public Connection getConnection() throws ServletException
     {
         Locale.setDefault(Locale.ENGLISH);
            try 
            {
                conn = dataSource.getConnection();
                conn.setAutoCommit(false);
                return conn;
            } 
            catch (SQLException ex) 
            {
                throw new ServletException("Cannot obtain connection", ex);
            }
        }
     
     /*
      function for connection release
      */
    public void releaseConnection(Connection conn) throws ServletException 
    {
        Locale.setDefault(Locale.ENGLISH);
        try 
        {
            conn.close();
        } 
        catch (SQLException ex) 
        {
            throw new ServletException("Cannot release connection", ex);
        }
    }

    /*
    @Override
    public Role getAllRole() throws ServletException 
    {
        List <Role> allRole = new ArrayList <Role>();
        Connection conn = null;
        try 
        {
            conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id, name FROM role");
            
            while (rs.next()) 
            {
                allRole.add(new Role(rs.getInt(1), rs.getString(2)));
            }
        } 
        catch (SQLException ex) 
        {
            throw new ServletException("Cannot obtain connection", ex);
        } 
        finally 
        {
            if (conn != null) 
            {
                releaseConnection(conn);
            }
        }
        
        for(Role item :allRole )
            return item;
        
    }*/
  
    /*
     function for reading roles from database
     */
    public Role readRole(int id) throws ServletException
    {
        role = new Role();
        try 
        {
            conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(selectQuery);
            stmt.setString(1, "role");
            stmt.setInt(2, role.getId());
            
            rs = stmt.executeQuery();
            
            while (rs.next()) 
            {
                role.setId(rs.getInt(1));
                role.setName(rs.getString(2));
            }
        } 
        catch (SQLException ex) 
        {
            throw new ServletException("Cannot obtain connection", ex);
        } 
        finally 
        {
            if (conn != null) 
            {
                releaseConnection(conn);
            }
        }
        return role;
    }
  
    /*
     function for inserting role to database
     */
    public void createRole(Role role) throws ServletException 
    {
        try 
        {
            conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO role VALUES(?, ?)");
            
            pstmt.setInt(1, role.getId());
            pstmt.setString(2, role.getName());
            
            pstmt.executeUpdate();
        } 
        catch (SQLException ex) 
        {
            throw new ServletException("Cannot obtain connection", ex);
        } 
        finally 
        {
            if (conn != null) 
            {
                releaseConnection(conn);
            }
        }
    }
 
    /*
     function for deleting role from database
     */
    public void deleteRole(Role role) throws ServletException 
    {
        try 
        {
            conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(deleteQuery);
            
            stmt.setString(1, "role");
            stmt.setInt(2, role.getId());
            
            stmt.executeUpdate();
        } 
        catch (SQLException ex) 
        {
            throw new ServletException("Cannot obtain connection", ex);
        } 
        finally 
        {
            if (conn != null) 
            {
                releaseConnection(conn);
            }
        }
    }
  
    /*
     function for updating role in database
     */
    public void updateRole(Role roleNew, Role roleOld) throws ServletException 
    {
        try 
        {
            conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement("UPDATE role SET name =? where id=?");
            
            pstmt.setString(1, roleNew.getName());
            pstmt.setInt(2, roleOld.getId());
            
            pstmt.executeUpdate();
            
        } catch (SQLException ex) 
        {
            throw new ServletException("Cannot obtain connection", ex);
        } 
        finally 
        {
            if (conn != null) 
            {
                releaseConnection(conn);
            }
        }   
    }
    
    /*
     function for inserting users
     */
    public void createUsers(Users users) throws ServletException 
    {
        try 
        {
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
            
        } 
        catch (SQLException ex) 
        {
            throw new ServletException("Cannot obtain connection", ex);
        } 
        finally 
        {
            if (conn != null) 
            {
                releaseConnection(conn);
            }
        }
    }
    
   
    /*
     function for reading users
     */
    public Users readUsers(int id) throws ServletException
    {
        users = new Users();
        try 
        {
            conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(selectQuery);
            stmt.setString(1, "users");
            stmt.setInt(2, users.getId());
            
            rs = stmt.executeQuery();
            
           while (rs.next()) 
            {
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
        } 
        catch (SQLException ex) 
        {
            throw new ServletException("Cannot obtain connection", ex);
        } 
        finally 
        {
            if (conn != null) 
            {
                releaseConnection(conn);
            }
        }
        return users;
    }
    
    
    /*
     function for updating users
     */
    public void updateUsers(Users usersOld, Users usersNew) throws ServletException 
    {
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
            
        } 
        catch (SQLException ex) 
        {
            throw new ServletException("Cannot obtain connection", ex);
        } 
        finally 
        {
            if (conn != null) 
            {
                releaseConnection(conn);
            }
        }
    }
    
    /*
     delete users in database
     */
    public void deleteUsers(Users users) throws ServletException 
    {
        try 
        {
            conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(deleteQuery);
            
            stmt.setString(1, "users");
            stmt.setInt(2, users.getId());
            
            stmt.executeUpdate();
            
        } 
        catch (SQLException ex) 
        {
            throw new ServletException("Cannot obtain connection", ex);
        } 
        finally 
        {
            if (conn != null) 
            {
                releaseConnection(conn);
            }
        }
    }
    
    /*
     function for inserting books
     */
    public void createLibrary(Library library) throws ServletException 
    {
        try 
        {
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
        } 
        catch (SQLException ex) 
        {
            throw new ServletException("Cannot obtain connection", ex);
        } 
        finally 
        {
            if (conn != null) 
            {
                releaseConnection(conn);
            }
        }
    }
    
    /*
     function for reading books
     */
    public Library readLibrary(int id) throws ServletException
    {
        library = new Library();
        try 
        {
            conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(selectQuery);
            stmt.setString(1, "library");
            stmt.setInt(2, library.getId());
            
            rs=stmt.executeQuery();
            
            while (rs.next()) 
            {
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
        
        } 
        catch (SQLException ex) 
        {
            throw new ServletException("Cannot obtain connection", ex);
        } 
        finally 
        {
            if (conn != null) 
            {
                releaseConnection(conn);
            }
        }
        return library;
    }
    
  
    
    public void updateLibrary(Library libraryOld, Library libraryNew) throws ServletException 
    {
        try 
        {
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
        } 
        catch (SQLException ex) 
        {
            throw new ServletException("Cannot obtain connection", ex);
        } 
        finally 
        {
            if (conn != null) 
            {
                releaseConnection(conn);
            }
        }
    }
    
  
    
    public void deleteLibrary(Library library) throws ServletException 
    {
        try 
        {
            conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(deleteQuery);
            
            stmt.setString(1, "library");
            stmt.setInt(2, library.getId());
            
            stmt.executeUpdate();
        } 
        catch (SQLException ex) 
        {
            throw new ServletException("Cannot obtain connection", ex);
        } 
        finally 
        {
            if (conn != null) 
            {
                releaseConnection(conn);
            }
        }
    }
      
    /*
     function for inserting users whose take books to local library
     */
    public void createCatalog(Catalog catalog) throws ServletException 
    {
        try 
        {
            conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO catalog VALUES(?, ?, ?)");
            
            pstmt.setInt(1, catalog.getId());
            pstmt.setInt(2, catalog.getUsers());
            pstmt.setInt(3, catalog.getBook());  
            
            pstmt.executeUpdate();
        } 
        catch (SQLException ex) 
        {
            throw new ServletException("Cannot obtain connection", ex);
        } 
        finally 
        {
            if (conn != null) 
            {
                releaseConnection(conn);
            }
        }
    }
    
    /*
     function for reading catalog
     */
    public Catalog readCatalog(int id) throws ServletException
    {
        catalog = new Catalog();
        try 
        {
            conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(selectQuery);
            stmt.setString(1, "calalog");
            stmt.setInt(2, catalog.getId());
            
            rs = stmt.executeQuery();
            
            while (rs.next()) 
            {
                catalog.setId(rs.getInt(1));
                catalog.setUsers(rs.getInt(2));
                catalog.setBook(rs.getInt(3));
            }    
        } 
        catch (SQLException ex) 
        {
            throw new ServletException("Cannot obtain connection", ex);
        } 
        finally 
        {
            if (conn != null) 
            {
                releaseConnection(conn);
            }
        }
        return catalog;
    }
    
    
    public void updateCatalog(Catalog catalogOld, Catalog catalogNew) throws ServletException 
    {
        try 
        {
            conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement("UPDATE catalog SET users=?, book=? WHERE id=?");
            
            pstmt.setInt(1, catalogNew.getUsers());
            pstmt.setInt(2, catalogNew.getBook());
            pstmt.setInt(3, catalogOld.getId());
            
            pstmt.executeUpdate();            
        } 
        catch (SQLException ex) 
        {
            throw new ServletException("Cannot obtain connection", ex);
        } 
        finally 
        {
            if (conn != null) 
            {
                releaseConnection(conn);
            }
        }
    }
    
    
    public void deleteCatalog(Catalog catalog) throws ServletException 
    {
        try 
        {
            conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(deleteQuery);
            
            stmt.setString(1, "catalog");
            stmt.setInt(2, catalog.getId());
            
            stmt.executeUpdate(); 
        } 
        catch (SQLException ex) 
        {
            throw new ServletException("Cannot obtain connection", ex);
        } 
        finally 
        {
            if (conn != null) 
            {
                releaseConnection(conn);
            }
        }
    }
    
    /*
     function for inserting rating
     */
    public void createRating(Rating rating) throws ServletException 
    {
        try 
        {
            conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO rating VALUES (?, ?, ?, ?)");
            
            pstmt.setInt(1, rating.getId());
            pstmt.setInt(2, rating.getRate());
            pstmt.setInt(3, rating.getUsers());
            pstmt.setInt(4, rating.getBook());
            
            pstmt.executeUpdate();
        } 
        catch (SQLException ex) 
        {
            throw new ServletException("Cannot obtain connection", ex);
        } 
        finally 
        {
            if (conn != null) 
            {
                releaseConnection(conn);
            }
        }
    }
  
    /*
     function for reading rating
     */
    public Rating readRating(int id) throws ServletException
    {
        rating = new Rating();
        try 
        {
            conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(selectQuery);
            stmt.setString(1, "rating");
            stmt.setInt(2, rating.getId());
            
            rs=stmt.executeQuery();
            
            while (rs.next()) 
            {
                rating.setId(rs.getInt(1));
                rating.setRate(rs.getInt(2));
                rating.setUsers(rs.getInt(3));
                rating.setBook(rs.getInt(4));
            }    
        } 
        catch (SQLException ex) 
        {
            throw new ServletException("Cannot obtain connection", ex);
        } 
        finally 
        {
            if (conn != null) 
            {
                releaseConnection(conn);
            }
        }
        return rating;
    }
    
  
    
    public void updateRating(Rating ratingOld, Rating ratingNew) throws ServletException 
    {
        try {
            conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement("UPDATE rating SET rate=?, users=?, book=? WHERE id=?");
            
            pstmt.setInt(1, ratingNew.getRate());
            pstmt.setInt(2, ratingNew.getUsers());
            pstmt.setInt(3, ratingNew.getBook());
            pstmt.setInt(4, ratingOld.getId());
            
            pstmt.executeUpdate();            
        } 
        catch (SQLException ex) 
        {
            throw new ServletException("Cannot obtain connection", ex);
        } 
        finally 
        {
            if (conn != null) 
            {
                releaseConnection(conn);
            }
        }
    }
    
   
   
    public void deleteRating(Rating rating) throws ServletException 
    {
        try 
        {
            conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(deleteQuery);
            
            stmt.setString(1, "rating");
            stmt.setInt(2, rating.getId());
            
            stmt.executeUpdate(); 
        } 
        catch (SQLException ex) 
        {
            throw new ServletException("Cannot obtain connection", ex);
        } 
        finally 
        {
            if (conn != null) 
            {
                releaseConnection(conn);
            }
        }
    }

    /*
     function for inserting authors
     */
    public void createAuthor(Author author) throws ServletException 
    {
        try {
            conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO author VALUES (?, ?)");
            
            pstmt.setInt(1, author.getId());
            pstmt.setString(2, author.getAuthor());
            
            pstmt.executeUpdate();
        } 
        catch (SQLException ex) 
        {
            throw new ServletException("Cannot obtain connection", ex);
        } 
        finally 
        {
            if (conn != null) 
            {
                releaseConnection(conn);
            }
        }
    }
    
    /*
     function for reading authors
     */
    public Author readAuthor(int id) throws ServletException
    {
        author = new Author();
        try 
        {
            conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(selectQuery);
            stmt.setString(1, "author");
            stmt.setInt(2, author.getId());
            
            rs=stmt.executeQuery();
            
            while (rs.next()) 
            {
                author.setId(rs.getInt(1));
                author.setAuthor(rs.getString(2));               
            }    
        } 
        catch (SQLException ex) 
        {
            throw new ServletException("Cannot obtain connection", ex);
        } 
        finally 
        {
            if (conn != null) 
            {
                releaseConnection(conn);
            }
        }
        return author;
    }
    
    
    public void updateAuthor(Author authorOld, Author authorNew) throws ServletException 
    {
        try 
        {
            conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement("UPDATE author SET author=? WHERE id=?");
            
            pstmt.setString(1, authorNew.getAuthor());
            pstmt.setInt(2, authorOld.getId());
            
            pstmt.executeUpdate();            
        } 
        catch (SQLException ex) 
        {
            throw new ServletException("Cannot obtain connection", ex);
        } 
        finally 
        {
            if (conn != null) 
            {
                releaseConnection(conn);
            }
        }
    }
    
    
    public void deleteAuthor(Author author) throws ServletException 
    {
        try 
        {
            conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(deleteQuery);
            
            stmt.setString(1, "author");
            stmt.setInt(2, author.getId());
            
            stmt.executeUpdate(); 
        } 
        catch (SQLException ex) 
        {
            throw new ServletException("Cannot obtain connection", ex);
        } 
        finally 
        {
            if (conn != null) 
            {
                releaseConnection(conn);
            }
        }
    }

    /*
     function for inserting genres
     */
    public void createGenre(Genre genre) throws ServletException 
    {
        try {
            conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO genre VALUES (?, ?)");
            
            pstmt.setInt(1, genre.getId());
            pstmt.setString(2, genre.getGenre());
            
            pstmt.executeUpdate();
        } 
        catch (SQLException ex) 
        {
            throw new ServletException("Cannot obtain connection", ex);
        } 
        finally 
        {
            if (conn != null) 
            {
                releaseConnection(conn);
            }
        }
    }
    
   /*
     function for reading genre
     */
    public Genre readGenre(int id) throws ServletException
    {
        genre = new Genre();
        try 
        {
            conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(selectQuery);
            stmt.setString(1, "genre");
            stmt.setInt(2, genre.getId());
            
            rs=stmt.executeQuery();
            
            while (rs.next()) 
            {
                genre.setId(rs.getInt(1));
                genre.setGenre(rs.getString(2));               
            }    
        } 
        catch (SQLException ex) 
        {
            throw new ServletException("Cannot obtain connection", ex);
        } 
        finally 
        {
            if (conn != null) 
            {
                releaseConnection(conn);
            }
        }
        return genre;
    }
    
  
    public void updateGenre(Genre genreOld, Genre genreNew) throws ServletException 
    {
        try 
        {
            conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement("UPDATE genre SET genre=? WHERE id=?");
            
            pstmt.setString(1, genreNew.getGenre());
            pstmt.setInt(2, genreOld.getId());
            
            pstmt.executeUpdate();            
        } 
        catch (SQLException ex) 
        {
            throw new ServletException("Cannot obtain connection", ex);
        } 
        finally 
        {
            if (conn != null) 
            {
                releaseConnection(conn);
            }
        }
    }
    
  
    public void deleteGenre(Genre genre) throws ServletException 
    {
        try 
        {
            conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(deleteQuery);
            
            stmt.setString(1, "genre");
            stmt.setInt(2, genre.getId());
            
            stmt.executeUpdate();  
        } 
        catch (SQLException ex) 
        {
            throw new ServletException("Cannot obtain connection", ex);
        } 
        finally 
        {
            if (conn != null) 
            {
                releaseConnection(conn);
            }
        }
    }
    
    /*
     function for inserting book_genres
     */
//    public void createBookGenre(BookGenre bookGenre) throws ServletException 
//    {
//        try 
//        {
    public void createBookGenre(BookGenre bookGanre) throws ServletException {
        Connection conn = null;
        try {

            conn = getConnection();
            
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO book_genre VALUES (?, ?, ?)");
            
            pstmt.setInt(1, bookGenre.getId());
            pstmt.setInt(2, bookGenre.getBook());
            pstmt.setInt(3, bookGenre.getGenre());
            
            pstmt.executeUpdate();
        } 
        catch (SQLException ex) 
        {
            throw new ServletException("Cannot obtain connection", ex);
        } 
        finally 
        {
            if (conn != null) 
            {
                releaseConnection(conn);
            }
        }
    }
    
    /*
     function for reading book_genre
     */
    public BookGenre readBookGenre(int id) throws ServletException
    {
        bookGenre = new BookGenre();
        try 
        {
            conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(selectQuery);
            stmt.setString(1, "bookGenre");
            stmt.setInt(2, bookGenre.getId());
            
            rs=stmt.executeQuery();
            
            while (rs.next()) 
            {
                bookGenre.setId(rs.getInt(1));
                bookGenre.setBook(rs.getInt(2));
                bookGenre.setGenre(rs.getInt(3));
            }    
        } 
        catch (SQLException ex) 
        {
            throw new ServletException("Cannot obtain connection", ex);
        } 
        finally 
        {
            if (conn != null) 
            {
                releaseConnection(conn);
            }
        }
        return bookGenre;
    }
    
    
    public void updateBookGenre(BookGenre bookGenreOld, BookGenre bookGenreNew) throws ServletException 
    {
        try 
        {
            conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement("UPDATE book_genre SET book=?, genre=? WHERE id=?");
            
            pstmt.setInt(1, bookGenreNew.getBook());
            pstmt.setInt(2, bookGenreNew.getGenre());
            pstmt.setInt(3, bookGenreOld.getId());
            
            pstmt.executeUpdate();            
        } 
        catch (SQLException ex) 
        {
            throw new ServletException("Cannot obtain connection", ex);
        } 
        finally 
        {
            if (conn != null) 
            {
                releaseConnection(conn);
            }
        }
    }
    
    
    public void deleteBookGenre(BookGenre bookGenre) throws ServletException 
    {
        try 
        {
            conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(deleteQuery);
            
            stmt.setString(1, "book_genre");
            stmt.setInt(2, bookGenre.getId());
            
            stmt.executeUpdate();
        } 
        catch (SQLException ex) 
        {
            throw new ServletException("Cannot obtain connection", ex);
        } 
        finally 
        {
            if (conn != null) 
            {
                releaseConnection(conn);
            }
        }
    }
    
     /*
     function for inserting book_author
     */
    public void createBookAuthor(BookAuthor bookauthor) throws ServletException
    {
         try 
         {
            conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO book_author VALUES (?, ?, ?)");
           
            pstmt.setInt(1, bookauthor.getId());
            pstmt.setInt(2,bookauthor.getBook());
            pstmt.setInt(2,bookauthor.getAuthor());
            
            pstmt.executeUpdate();
            
        } 
         catch (SQLException ex) 
         {
            throw new ServletException("Cannot obtain connection", ex);
         } 
         finally 
         {
            if (conn != null) 
            {
                releaseConnection(conn);
            }
        }
    }
    
    /*
     function for reading book_author
     */
     public BookAuthor readBookAuthor(int id) throws ServletException
     {
         bookauthor=new BookAuthor();
            try 
        {
            conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(selectQuery);
            stmt.setString(1, "book_author");
            stmt.setInt(2, bookauthor.getId());
            
            rs = stmt.executeQuery();
                while (rs.next()) 
                {
                    bookauthor.setId(rs.getInt(1));
                    bookauthor.setBook(rs.getInt(2));
                    bookauthor.setAuthor(rs.getInt(3));
                }    
            } 
            catch (SQLException ex) 
            {
                throw new ServletException("Cannot obtain connection", ex);
            } 
            finally 
            {
                if (conn != null) 
                {
                    releaseConnection(conn);
                }
        }
        return bookauthor;
     }
    
    
    
    public void updateBookAuthor(BookAuthor bookauthorOld, BookAuthor bookauthorNew) throws ServletException
    {
        try 
        {
            conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement("UPDATE book_author SET book=?, author=? WHERE id=?");
            
            pstmt.setInt(1,bookauthorNew.getBook() );
            pstmt.setInt(2,bookauthorNew.getAuthor() );
            pstmt.setInt(3, bookauthorOld.getId());
            
            pstmt.executeUpdate();            
        } 
        catch (SQLException ex) 
        {
            throw new ServletException("Cannot obtain connection", ex);
        } 
        finally 
        {
            if (conn != null) 
            {
                releaseConnection(conn);
            }
        }
    }
    
    
    
    public void deleteBookAuthor(BookAuthor bookauthor) throws ServletException
    {
        try 
        {
            conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(deleteQuery);
            
            stmt.setString(1, "book_author");
            stmt.setInt(2, bookauthor.getId());
            
            stmt.executeUpdate();
        } 
        catch (SQLException ex) 
        {
            throw new ServletException("Cannot obtain connection", ex);
        } 
        finally 
        {
            if (conn != null) 
            {
                releaseConnection(conn);
            }
        }
    }

}
