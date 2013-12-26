/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package OracleDAO;

import OracleConnection.Oracle;
import TransferObjectInterface.LibraryDAO;
import TransferObject.Library;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author mazafaka
 */
public class OracleLibraryDAO implements LibraryDAO{

    private Oracle conn1;
    private ResultSet rs;
    private Connection conn;
    private static final String selectQuery="SELECT * FROM ? WHERE id=?";
    private static final String deleteQuery="DELETE FROM ? WHERE id =?";
    private static final String insertLibraryQuery="INSERT INTO library VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String updateLibraryQuery="UPDATE library SET isbn = ?, title = ?," +
                                            "cover = ?, description = ?, pages = ?," +
                                            "author = ?, genre = ?, users = ? WHERE id=?";
    private Library library;

     public OracleLibraryDAO()
    {
        try
        {
        conn=conn1.getConnection();
        }
        catch(IOException e1)
        {
            System.out.println("IOExeption:"+e1);
        }
        
    }

    public void createLibrary(Library library) {
        try
        {
            PreparedStatement pstmt = conn.prepareStatement(insertLibraryQuery);

            pstmt.setLong(1, library.getId());
            pstmt.setString(2, library.getIsbn());
            pstmt.setString(3, library.getTitle());
            pstmt.setString(4, library.getCover());
            pstmt.setString(5, library.getDescription());
            pstmt.setInt(6, library.getPages());

            pstmt.executeUpdate();
                conn.close();
            
        }
        catch (SQLException e)
        {
            while(e!=null)
            {
                e.printStackTrace();
                e=e.getNextException();
            }
        }
    }

    public Library readLibrary(int id) {
       library = new Library();
        try
        {
            PreparedStatement stmt = conn.prepareStatement(selectQuery);
            stmt.setString(1, "library");
            stmt.setLong(2, library.getId());

            rs=stmt.executeQuery();

            while (rs.next())
            {
                library.setId(rs.getLong(1));
                library.setIsbn(rs.getString(2));
                library.setTitle(rs.getString(3));
                library.setCover(rs.getString(4));
                library.setDescription(rs.getString(5));
                library.setPages(rs.getInt(6));
                library.setAuthor(rs.getLong(7));
                library.setGenre(rs.getInt(8));
            }
                conn.close();
            
        }
        catch (SQLException e)
        {
            while(e!=null)
            {
                e.printStackTrace();
                e=e.getNextException();
            }
        }

        return library;
    }

    public void updateLibrary(Library libraryOld, Library libraryNew) {
        try
        {
            PreparedStatement pstmt = conn.prepareStatement(updateLibraryQuery);

            pstmt.setString(1, libraryNew.getIsbn());
            pstmt.setString(2, libraryNew.getTitle());
            pstmt.setString(3, libraryNew.getCover());
            pstmt.setString(4, libraryNew.getDescription());
            pstmt.setInt(5, libraryNew.getPages());
            pstmt.setLong(6, libraryNew.getAuthor());
            pstmt.setInt(7, libraryNew.getGenre());
            pstmt.setLong(9, libraryOld.getId());

            pstmt.executeUpdate();
                conn.close();
            
        }
        catch (SQLException e)
        {
            while(e!=null)
            {
                e.printStackTrace();
                e=e.getNextException();
            }
        }
    }

    public void deleteLibrary(Library library) {
        try
        {
            PreparedStatement stmt = conn.prepareStatement(deleteQuery);

            stmt.setString(1, "library");
            stmt.setLong(2, library.getId());

            stmt.executeUpdate();
                conn.close();
            
        }
        catch (SQLException e)
        {
            while(e!=null)
            {
                e.printStackTrace();
                e=e.getNextException();
            }
        }
    }

}
