/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package OracleDAO;

import OracleConnection.Oracle;
import TransferObjectInterface.LibraryDAO;
import TransferObject.Library;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;
/**
 *
 * @author mazafaka
 */
public class OracleLibraryDAO implements LibraryDAO{
    public static final Logger log=Logger.getLogger(OracleAuthorDAO.class);
    private Oracle conn1;
    private static final String selectQuery="SELECT * FROM library WHERE id=?";
    private static final String deleteQuery="DELETE FROM library WHERE id =?";
    private static final String insertLibraryQuery="INSERT INTO library VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String updateLibraryQuery="UPDATE library SET isbn = ?, title = ?," +
                                            "cover = ?, description = ?, pages = ?," +
                                            "author = ?, genre = ? WHERE id=?";

    public void createLibrary(Library library) {
       
        Connection conn=conn1.getConnection();
        try
        {
            PreparedStatement pstmt = conn.prepareStatement(insertLibraryQuery);

            pstmt.setLong(1, library.getId());
            pstmt.setString(2, library.getIsbn());
            pstmt.setString(3, library.getTitle());
            pstmt.setString(4, library.getCover());
            pstmt.setString(5, library.getDescription());
            pstmt.setInt(6, library.getPages());
            pstmt.setLong(7,library.getAuthor());
            pstmt.setInt(8,library.getGenre());

            pstmt.executeUpdate();
        }
        catch (SQLException e)
        {
            while(e!=null)
            {
                log.error("SQLException"+e);
            }
        }
    }

    public Library readLibrary(int id) {
       Library library = new Library();
       Connection conn=conn1.getConnection();
        try
        {
            PreparedStatement stmt = conn.prepareStatement(selectQuery);
            stmt.setString(1, "library");
            stmt.setLong(2, library.getId());

            ResultSet rs=stmt.executeQuery();

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
            rs.close();
        }
        catch (SQLException e)
        {
            while(e!=null)
            {
                log.error("SQLException"+e);
            }
        }

        return library;
    }

    public void updateLibrary(Library libraryOld, Library libraryNew) {
        
        Connection conn=conn1.getConnection();
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
            pstmt.setLong(8, libraryOld.getId());

            pstmt.executeUpdate();
        }
        catch (SQLException e)
        {
            while(e!=null)
            {
                log.error("SQLException"+e);
            }
        }
    }

    public void deleteLibrary(Library library) {
       
        Connection conn=conn1.getConnection();
        try
        {
            PreparedStatement stmt = conn.prepareStatement(deleteQuery);

            stmt.setLong(1, library.getId());

            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            while(e!=null)
            {
                log.error("SQLException"+e);
            }
        }
    }

}
