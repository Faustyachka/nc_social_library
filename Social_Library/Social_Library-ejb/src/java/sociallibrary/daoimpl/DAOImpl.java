package sociallibrary.daoimpl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sociallibrary.connections.ConnectionOracle;
import sociallibrary.dto.Role;
import sociallibrary.entity.*;

/*
   Class CRUD
 */

public class DAOImpl //implements DAO
{
    private ConnectionOracle conn;
    private Connection con;
    private Role role;
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
    private static final String insertRoleQuery="INSERT INTO role VALUES(?, ?)";
    private static final String updateRoleQuery="UPDATE role SET name =? where id=?";
    private static final String insertUsersQuery="INSERT INTO users VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, '11.02.2013', ?, ?)";
    private static final String updateUsersQuery="UPDATE users SET first_name = ?," + 
                        "last_name = ?, email = ?, login = ?, password = ?," + 
                        "gender = ?, confirmed = ?, banned = ?, registration_date = '01.01.2013'," +
                        "notify = ?, role=? WHERE id = ?";
    private static final String insertLibraryQuery="INSERT INTO library VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String updateLibraryQuery="UPDATE library SET isbn = ?, title = ?," +
                                            "cover = ?, description = ?, pages = ?," + 
                                            "author = ?, genre = ?, users = ? WHERE id=?";
    private static final String insertCatalogQuery="INSERT INTO catalog VALUES(?, ?, ?)";
    private static final String updateCatalogQuery="UPDATE catalog SET users=?, book=? WHERE id=?";
    private static final String insertRatingQuery="INSERT INTO rating VALUES (?, ?, ?, ?)";
    private static final String updateRatingQuery="UPDATE rating SET rate=?, users=?, book=? WHERE id=?";
    private static final String insertAuthorQuery="INSERT INTO author VALUES (?, ?)";
    private static final String updateAuthorQuery="UPDATE author SET author=? WHERE id=?";
    private static final String insertGenreQuery="INSERT INTO genre VALUES (?, ?)";
    private static final String updateGenreQuery="UPDATE genre SET genre=? WHERE id=?";
    private static final String insertBookGenreQuery="INSERT INTO book_genre VALUES (?, ?, ?)";
    private static final String updateBookGenreQuery="UPDATE book_genre SET book=?, genre=? WHERE id=?";;
    private static final String insertBookAuthorQuery="INSERT INTO book_author VALUES (?, ?, ?)";
    private static final String updateBookAuthorQuery="UPDATE book_author SET book=?, author=? WHERE id=?";
     
    /*
     function for reading roles from database
     */
    public Role readRole(int id)
    {
        role = new Role();
        try 
        {
            con=conn.getConnection();
            PreparedStatement stmt = con.prepareStatement(selectQuery);
            stmt.setString(1, "role");
            stmt.setInt(2, role.getId());
            
            rs = stmt.executeQuery();
            
            while (rs.next()) 
            {
                role.setId(rs.getInt(1));
                role.setName(rs.getString(2));
            }
           if (con != null) 
            {
                con.close();
            }
        } 
        catch (SQLException e) 
        {
            while(e!=null)
            {
                e.printStackTrace();
                e=e.getNextException();
            }
        }
        catch(IOException e)
        {
            System.out.println("IOExeption:"+e);
        }
        
        return role;
    }
  
    /*
     function for inserting role to database
     */
    public void createRole(Role role)
    {
        try 
        {
            con = conn.getConnection();
            PreparedStatement pstmt = con.prepareStatement(insertRoleQuery);
            
            pstmt.setInt(1, role.getId());
            pstmt.setString(2, role.getName());
            
            pstmt.executeUpdate();
            if (con != null) 
            {
                con.close();
            }
        } 
        catch (SQLException e) 
        {
            while(e!=null)
            {
                e.printStackTrace();
                e=e.getNextException();
            }
        }
        catch(IOException e)
        {
            System.out.println("IOExeption:"+e);
        }
    }
 
    /*
     function for deleting role from database
     */
    public void deleteRole(Role role) 
    {
        try 
        {
            con = conn.getConnection();
            PreparedStatement stmt = con.prepareStatement(deleteQuery);
            
            stmt.setString(1, "role");
            stmt.setInt(2, role.getId());
            
            stmt.executeUpdate();
            if (con != null) 
            {
                con.close();
            }
        } 
        catch (SQLException e) 
        {
            while(e!=null)
            {
                e.printStackTrace();
                e=e.getNextException();
            }
        }
        catch(IOException e)
        {
            System.out.println("IOExeption:"+e);
        }
    }
  
    /*
     function for updating role in database
     */
    public void updateRole(Role roleNew, Role roleOld)
    {
        try 
        {
            con = conn.getConnection();
            PreparedStatement pstmt = con.prepareStatement(updateRoleQuery);
            
            pstmt.setString(1, roleNew.getName());
            pstmt.setInt(2, roleOld.getId());
            
            pstmt.executeUpdate();
            if (con != null) 
            {
                con.close();
            }
        } 
        catch (SQLException e) 
        {
            while(e!=null)
            {
                e.printStackTrace();
                e=e.getNextException();
            }
        }
        catch(IOException e)
        {
            System.out.println("IOExeption:"+e);
        }
    }
    
    /*
     function for inserting users
     */
    public void createUsers(Users users)
    {
        try 
        {
            con = conn.getConnection();
            PreparedStatement pstmt = con.prepareStatement(insertUsersQuery);
            
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
            if (con != null) 
            {
                con.close();
            }
        } 
        catch (SQLException e) 
        {
            while(e!=null)
            {
                e.printStackTrace();
                e=e.getNextException();
            }
        }
        catch(IOException e)
        {
            System.out.println("IOExeption:"+e);
        }
    }
    
   
    /*
     function for reading users
     */
    public Users readUsers(int id)
    {
        users = new Users();
        try 
        {
            con = conn.getConnection();
            PreparedStatement stmt = con.prepareStatement(selectQuery);
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
        if (con != null) 
            {
                con.close();
            }
        } 
        catch (SQLException e) 
        {
            while(e!=null)
            {
                e.printStackTrace();
                e=e.getNextException();
            }
        }
        catch(IOException e)
        {
            System.out.println("IOExeption:"+e);
        }
        return users;
    }
    
    
    /*
     function for updating users
     */
    public void updateUsers(Users usersOld, Users usersNew)  
    {
        try {
            con = conn.getConnection();
            PreparedStatement pstmt = con.prepareStatement(updateUsersQuery);
            
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
            if (con != null) 
            {
                con.close();
            }
        } 
        catch (SQLException e) 
        {
            while(e!=null)
            {
                e.printStackTrace();
                e=e.getNextException();
            }
        }
        catch(IOException e)
        {
            System.out.println("IOExeption:"+e);
        }
    }
    
    /*
     delete users in database
     */
    public void deleteUsers(Users users)
    {
        try 
        {
           con=conn.getConnection();
            PreparedStatement stmt = con.prepareStatement(deleteQuery);
            
            stmt.setString(1, "users");
            stmt.setInt(2, users.getId());
            
            stmt.executeUpdate();
            if (con != null) 
            {
                con.close();
            }
        } 
        catch (SQLException e) 
        {
            while(e!=null)
            {
                e.printStackTrace();
                e=e.getNextException();
            }
        }
        catch(IOException e)
        {
            System.out.println("IOExeption:"+e);
        }
    }
    
    /*
     function for inserting books
     */
    public void createLibrary(Library library)
    {
        try 
        {
            con = conn.getConnection();
            PreparedStatement pstmt = con.prepareStatement(insertLibraryQuery);
            
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
            if (con != null) 
            {
                con.close();
            }
        } 
        catch (SQLException e) 
        {
            while(e!=null)
            {
                e.printStackTrace();
                e=e.getNextException();
            }
        }
        catch(IOException e)
        {
            System.out.println("IOExeption:"+e);
        }
    }
    
    /*
     function for reading books
     */
    public Library readLibrary(int id)
    {
        library = new Library();
        try 
        {
            con = conn.getConnection();
            PreparedStatement stmt = con.prepareStatement(selectQuery);
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
        if (con != null) 
            {
                con.close();
            }
        } 
        catch (SQLException e) 
        {
            while(e!=null)
            {
                e.printStackTrace();
                e=e.getNextException();
            }
        }
        catch(IOException e)
        {
            System.out.println("IOExeption:"+e);
        }
        return library;
    }
    
  
    
    public void updateLibrary(Library libraryOld, Library libraryNew)
    {
        try 
        {
            con = conn.getConnection();
            PreparedStatement pstmt = con.prepareStatement(updateLibraryQuery);
            
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
            if (con != null) 
            {
                con.close();
            }
        } 
        catch (SQLException e) 
        {
            while(e!=null)
            {
                e.printStackTrace();
                e=e.getNextException();
            }
        }
        catch(IOException e)
        {
            System.out.println("IOExeption:"+e);
        }
    }
    
    public void deleteLibrary(Library library)
    {
        try 
        {
            con = conn.getConnection();
            PreparedStatement stmt = con.prepareStatement(deleteQuery);
            
            stmt.setString(1, "library");
            stmt.setInt(2, library.getId());
            
            stmt.executeUpdate();
            if (con != null) 
            {
                con.close();
            }
        } 
        catch (SQLException e) 
        {
            while(e!=null)
            {
                e.printStackTrace();
                e=e.getNextException();
            }
        }
        catch(IOException e)
        {
            System.out.println("IOExeption:"+e);
        }
    }
      
    /*
     function for inserting users whose take books to local library
     */
    public void createCatalog(Catalog catalog)
    {
        try 
        {
            con = conn.getConnection();
            PreparedStatement pstmt = con.prepareStatement(insertCatalogQuery);
            
            pstmt.setInt(1, catalog.getId());
            pstmt.setInt(2, catalog.getUsers());
            pstmt.setInt(3, catalog.getBook());  
            
            pstmt.executeUpdate();
        if (con != null) 
            {
                con.close();
            }
        } 
        catch (SQLException e) 
        {
            while(e!=null)
            {
                e.printStackTrace();
                e=e.getNextException();
            }
        }
        catch(IOException e)
        {
            System.out.println("IOExeption:"+e);
        }
    }
    
    /*
     function for reading catalog
     */
    public Catalog readCatalog(int id)
    {
        catalog = new Catalog();
        try 
        {
            con = conn.getConnection();
            PreparedStatement stmt = con.prepareStatement(selectQuery);
            stmt.setString(1, "calalog");
            stmt.setInt(2, catalog.getId());
            
            rs = stmt.executeQuery();
            
            while (rs.next()) 
            {
                catalog.setId(rs.getInt(1));
                catalog.setUsers(rs.getInt(2));
                catalog.setBook(rs.getInt(3));
            }    
       if (con != null) 
            {
                con.close();
            }
        } 
        catch (SQLException e) 
        {
            while(e!=null)
            {
                e.printStackTrace();
                e=e.getNextException();
            }
        }
        catch(IOException e)
        {
            System.out.println("IOExeption:"+e);
        }
        return catalog;
    }
    
    
    public void updateCatalog(Catalog catalogOld, Catalog catalogNew)
    {
        try 
        {
            con = conn.getConnection();
            PreparedStatement pstmt = con.prepareStatement(updateCatalogQuery);
            
            pstmt.setInt(1, catalogNew.getUsers());
            pstmt.setInt(2, catalogNew.getBook());
            pstmt.setInt(3, catalogOld.getId());
            
            pstmt.executeUpdate();            
        if (con != null) 
            {
                con.close();
            }
        } 
        catch (SQLException e) 
        {
            while(e!=null)
            {
                e.printStackTrace();
                e=e.getNextException();
            }
        }
        catch(IOException e)
        {
            System.out.println("IOExeption:"+e);
        }
    }
    
    
    public void deleteCatalog(Catalog catalog) 
    {
        try 
        {
           con = conn.getConnection();
            PreparedStatement stmt = con.prepareStatement(deleteQuery);
            
            stmt.setString(1, "catalog");
            stmt.setInt(2, catalog.getId());
            
            stmt.executeUpdate(); 
        if (con != null) 
            {
                con.close();
            }
        } 
        catch (SQLException e) 
        {
            while(e!=null)
            {
                e.printStackTrace();
                e=e.getNextException();
            }
        }
        catch(IOException e)
        {
            System.out.println("IOExeption:"+e);
        }
    }

    
    /*
     function for inserting rating
     */
    public void createRating(Rating rating)
    {
        try 
        {
           con = conn.getConnection();
            PreparedStatement pstmt = con.prepareStatement(insertRatingQuery);
            
            pstmt.setInt(1, rating.getId());
            pstmt.setInt(2, rating.getRate());
            pstmt.setInt(3, rating.getUsers());
            pstmt.setInt(4, rating.getBook());
            
            pstmt.executeUpdate();
        if (con != null) 
            {
                con.close();
            }
        } 
        catch (SQLException e) 
        {
            while(e!=null)
            {
                e.printStackTrace();
                e=e.getNextException();
            }
        }
        catch(IOException e)
        {
            System.out.println("IOExeption:"+e);
        }
    }
  
    /*
     function for reading rating
     */
    public Rating readRating(int id)
    {
        rating = new Rating();
        try 
        {
            con = conn.getConnection();
            PreparedStatement stmt = con.prepareStatement(selectQuery);
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
        if (con != null) 
            {
                con.close();
            }
        } 
        catch (SQLException e) 
        {
            while(e!=null)
            {
                e.printStackTrace();
                e=e.getNextException();
            }
        }
        catch(IOException e)
        {
            System.out.println("IOExeption:"+e);
        }
        return rating;
    }
    
  
    
    public void updateRating(Rating ratingOld, Rating ratingNew)
    {
        try {
            con = conn.getConnection();
            PreparedStatement pstmt = con.prepareStatement(updateRatingQuery);
            
            pstmt.setInt(1, ratingNew.getRate());
            pstmt.setInt(2, ratingNew.getUsers());
            pstmt.setInt(3, ratingNew.getBook());
            pstmt.setInt(4, ratingOld.getId());
            
            pstmt.executeUpdate();            
        if (con != null) 
            {
                con.close();
            }
        } 
        catch (SQLException e) 
        {
            while(e!=null)
            {
                e.printStackTrace();
                e=e.getNextException();
            }
        }
        catch(IOException e)
        {
            System.out.println("IOExeption:"+e);
        }
    }
    
   
   
    public void deleteRating(Rating rating) 
    {
        try 
        {
            con = conn.getConnection();
            PreparedStatement stmt = con.prepareStatement(deleteQuery);
            
            stmt.setString(1, "rating");
            stmt.setInt(2, rating.getId());
            
            stmt.executeUpdate(); 
        if (con != null) 
            {
                con.close();
            }
        } 
        catch (SQLException e) 
        {
            while(e!=null)
            {
                e.printStackTrace();
                e=e.getNextException();
            }
        }
        catch(IOException e)
        {
            System.out.println("IOExeption:"+e);
        }
    }

    /*
     function for inserting authors
     */
    public void createAuthor(Author author)
    {
        try {
            con = conn.getConnection();
            PreparedStatement pstmt = con.prepareStatement(insertAuthorQuery);
            
            pstmt.setInt(1, author.getId());
            pstmt.setString(2, author.getAuthor());
            
            pstmt.executeUpdate();
        if (con != null) 
            {
                con.close();
            }
        } 
        catch (SQLException e) 
        {
            while(e!=null)
            {
                e.printStackTrace();
                e=e.getNextException();
            }
        }
        catch(IOException e)
        {
            System.out.println("IOExeption:"+e);
        }
    }
    
    /*
     function for reading authors
     */
    public Author readAuthor(int id)
    {
        author = new Author();
        try 
        {
            con = conn.getConnection();
            PreparedStatement stmt = con.prepareStatement(selectQuery);
            stmt.setString(1, "author");
            stmt.setInt(2, author.getId());
            
            rs=stmt.executeQuery();
            
            while (rs.next()) 
            {
                author.setId(rs.getInt(1));
                author.setAuthor(rs.getString(2));               
            
            } 
            if (con != null) 
            {
                con.close();
            }
        }
        catch (SQLException e) 
        {
            while(e!=null)
            {
                e.printStackTrace();
                e=e.getNextException();
            }
        }
        catch(IOException e)
        {
            System.out.println("IOExeption:"+e);
        }
        return author;
    }
    
    
    public void updateAuthor(Author authorOld, Author authorNew)
    {
        try 
        {
            con = conn.getConnection();
            PreparedStatement pstmt = con.prepareStatement(updateAuthorQuery);
            
            pstmt.setString(1, authorNew.getAuthor());
            pstmt.setInt(2, authorOld.getId());
            
            pstmt.executeUpdate();            
        if (con != null) 
            {
                con.close();
            }
        } 
        catch (SQLException e) 
        {
            while(e!=null)
            {
                e.printStackTrace();
                e=e.getNextException();
            }
        }
        catch(IOException e)
        {
            System.out.println("IOExeption:"+e);
        }
    }
    
    
    public void deleteAuthor(Author author)
    {
        try 
        {
            con = conn.getConnection();
            PreparedStatement stmt = con.prepareStatement(deleteQuery);
            
            stmt.setString(1, "author");
            stmt.setInt(2, author.getId());
            
            stmt.executeUpdate(); 
        if (con != null) 
            {
                con.close();
            }
        } 
        catch (SQLException e) 
        {
            while(e!=null)
            {
                e.printStackTrace();
                e=e.getNextException();
            }
        }
        catch(IOException e)
        {
            System.out.println("IOExeption:"+e);
        }
    }

    /*
     function for inserting genres
     */
    public void createGenre(Genre genre)
    {
        try 
        {
            con = conn.getConnection();
            PreparedStatement pstmt = con.prepareStatement(insertGenreQuery);
            
            pstmt.setInt(1, genre.getId());
            pstmt.setString(2, genre.getGenre());
            
            pstmt.executeUpdate();
        if (con != null) 
            {
                con.close();
            }
        } 
        catch (SQLException e) 
        {
            while(e!=null)
            {
                e.printStackTrace();
                e=e.getNextException();
            }
        }
        catch(IOException e)
        {
            System.out.println("IOExeption:"+e);
        }
    }
    
   /*
     function for reading genre
     */
    public Genre readGenre(int id)
    {
        genre = new Genre();
        try 
        {
            con = conn.getConnection();
            PreparedStatement stmt = con.prepareStatement(selectQuery);
            stmt.setString(1, "genre");
            stmt.setInt(2, genre.getId());
            
            rs=stmt.executeQuery();
            
            while (rs.next()) 
            {
                genre.setId(rs.getInt(1));
                genre.setGenre(rs.getString(2));               
            }    
        if (con != null) 
            {
                con.close();
            }
        } 
        catch (SQLException e) 
        {
            while(e!=null)
            {
                e.printStackTrace();
                e=e.getNextException();
            }
        }
        catch(IOException e)
        {
            System.out.println("IOExeption:"+e);
        }
        return genre;
    }
    
  
    public void updateGenre(Genre genreOld, Genre genreNew)
    {
        try 
        {
            con = conn.getConnection();
            PreparedStatement pstmt = con.prepareStatement(updateGenreQuery);
            
            pstmt.setString(1, genreNew.getGenre());
            pstmt.setInt(2, genreOld.getId());
            
            pstmt.executeUpdate();            
        if (con != null) 
            {
                con.close();
            }
        } 
        catch (SQLException e) 
        {
            while(e!=null)
            {
                e.printStackTrace();
                e=e.getNextException();
            }
        }
        catch(IOException e)
        {
            System.out.println("IOExeption:"+e);
        }
    }
    
  
    public void deleteGenre(Genre genre)
    {
        try 
        {
            con = conn.getConnection();
            PreparedStatement stmt = con.prepareStatement(deleteQuery);
            
            stmt.setString(1, "genre");
            stmt.setInt(2, genre.getId());
            
            stmt.executeUpdate();  
        if (con != null) 
            {
                con.close();
            }
        } 
        catch (SQLException e) 
        {
            while(e!=null)
            {
                e.printStackTrace();
                e=e.getNextException();
            }
        }
        catch(IOException e)
        {
            System.out.println("IOExeption:"+e);
        }
    }
    
    /*
     function for inserting book_genres
     */

    public void createBookGenre(BookGenre bookGenre)
    {
        try {
            con = conn.getConnection();
            PreparedStatement pstmt = con.prepareStatement(insertBookGenreQuery);
            
            pstmt.setInt(1, bookGenre.getId());
            pstmt.setInt(2, bookGenre.getBook());
            pstmt.setInt(3, bookGenre.getGenre());
            
            pstmt.executeUpdate();
        if (con != null) 
            {
                con.close();
            }
        } 
        catch (SQLException e) 
        {
            while(e!=null)
            {
                e.printStackTrace();
                e=e.getNextException();
            }
        }
        catch(IOException e)
        {
            System.out.println("IOExeption:"+e);
        }
    }
    
    /*
     function for reading book_genre
     */
    public BookGenre readBookGenre(int id)
    {
        bookGenre = new BookGenre();
        try 
        {
            con = conn.getConnection();
            PreparedStatement stmt = con.prepareStatement(selectQuery);
            stmt.setString(1, "bookGenre");
            stmt.setInt(2, bookGenre.getId());
            
            rs=stmt.executeQuery();
            
            while (rs.next()) 
            {
                bookGenre.setId(rs.getInt(1));
                bookGenre.setBook(rs.getInt(2));
                bookGenre.setGenre(rs.getInt(3));
            }    
        if (con != null) 
            {
                con.close();
            }
        } 
        catch (SQLException e) 
        {
            while(e!=null)
            {
                e.printStackTrace();
                e=e.getNextException();
            }
        }
        catch(IOException e)
        {
            System.out.println("IOExeption:"+e);
        }
        return bookGenre;
    }
    
    
    public void updateBookGenre(BookGenre bookGenreOld, BookGenre bookGenreNew)
    {
        try 
        {
            con = conn.getConnection();
            PreparedStatement pstmt = con.prepareStatement(updateBookGenreQuery);
            
            pstmt.setInt(1, bookGenreNew.getBook());
            pstmt.setInt(2, bookGenreNew.getGenre());
            pstmt.setInt(3, bookGenreOld.getId());
            
            pstmt.executeUpdate();            
        if (con != null) 
            {
                con.close();
            }
        } 
        catch (SQLException e) 
        {
            while(e!=null)
            {
                e.printStackTrace();
                e=e.getNextException();
            }
        }
        catch(IOException e)
        {
            System.out.println("IOExeption:"+e);
        }
    }
    
    
    public void deleteBookGenre(BookGenre bookGenre)
    {
        try 
        {
            con = conn.getConnection();
            PreparedStatement stmt = con.prepareStatement(deleteQuery);
            
            stmt.setString(1, "book_genre");
            stmt.setInt(2, bookGenre.getId());
            
            stmt.executeUpdate();
        if (con != null) 
            {
                con.close();
            }
        } 
        catch (SQLException e) 
        {
            while(e!=null)
            {
                e.printStackTrace();
                e=e.getNextException();
            }
        }
        catch(IOException e)
        {
            System.out.println("IOExeption:"+e);
        }
    }
    
     /*
     function for inserting book_author
     */
    public void createBookAuthor(BookAuthor bookauthor)
    {
         try 
         {
            con = conn.getConnection();
            PreparedStatement pstmt = con.prepareStatement(insertBookAuthorQuery);
           
            pstmt.setInt(1, bookauthor.getId());
            pstmt.setInt(2,bookauthor.getBook());
            pstmt.setInt(2,bookauthor.getAuthor());
            
            pstmt.executeUpdate();
          if (con != null) 
            {
                con.close();
            }
        } 
        catch (SQLException e) 
        {
            while(e!=null)
            {
                e.printStackTrace();
                e=e.getNextException();
            }
        }
        catch(IOException e)
        {
            System.out.println("IOExeption:"+e);
        }
    }
    
    /*
     function for reading book_author
     */
     public BookAuthor readBookAuthor(int id)
     {
         bookauthor=new BookAuthor();
            try 
        {
            con = conn.getConnection();
            PreparedStatement stmt = con.prepareStatement(selectQuery);
            stmt.setString(1, "book_author");
            stmt.setInt(2, bookauthor.getId());
            
            rs = stmt.executeQuery();
                while (rs.next()) 
                {
                    bookauthor.setId(rs.getInt(1));
                    bookauthor.setBook(rs.getInt(2));
                    bookauthor.setAuthor(rs.getInt(3));
                }    
            if (con != null) 
            {
                con.close();
            }
        } 
        catch (SQLException e) 
        {
            while(e!=null)
            {
                e.printStackTrace();
                e=e.getNextException();
            }
        }
        catch(IOException e)
        {
            System.out.println("IOExeption:"+e);
        }
        return bookauthor;
     }
    
    
    
    public void updateBookAuthor(BookAuthor bookauthorOld, BookAuthor bookauthorNew)
    {
        try 
        {
            con = conn.getConnection();
            PreparedStatement pstmt = con.prepareStatement(updateBookAuthorQuery);
            
            pstmt.setInt(1,bookauthorNew.getBook() );
            pstmt.setInt(2,bookauthorNew.getAuthor() );
            pstmt.setInt(3, bookauthorOld.getId());
            
            pstmt.executeUpdate();            
        if (con != null) 
            {
                con.close();
            }
        } 
        catch (SQLException e) 
        {
            while(e!=null)
            {
                e.printStackTrace();
                e=e.getNextException();
            }
        }
        catch(IOException e)
        {
            System.out.println("IOExeption:"+e);
        }
    }
    
    
    
    public void deleteBookAuthor(BookAuthor bookauthor)
    {
        try 
        {
            con = conn.getConnection();
            PreparedStatement stmt = con.prepareStatement(deleteQuery);
            
            stmt.setString(1, "book_author");
            stmt.setInt(2, bookauthor.getId());
            
            stmt.executeUpdate();
        if (con != null) 
            {
                con.close();
            }
        } 
        catch (SQLException e) 
        {
            while(e!=null)
            {
                e.printStackTrace();
                e=e.getNextException();
            }
        }
        catch(IOException e)
        {
            System.out.println("IOExeption:"+e);
        }
    }

}
