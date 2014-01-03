/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package OracleDAO;

import OracleConnection.Oracle;
import TransferObject.BookWorkflow;
import com.sociallibrary.EntitiesInterfaces.LibraryDAO;
import TransferObject.Library;
import com.sociallibrary.EntitiesInterfaces.BookWorkflowDAO;
import com.sociallibrary.EntitiesInterfaces.UsersDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
/**
 *
 * @author mazafaka
 */
public class OracleLibraryDAO implements LibraryDAO{
    public static final Logger log=Logger.getLogger(OracleAuthorDAO.class);
    private Oracle conn1 = new Oracle();
    private static final String selectQuery="SELECT * FROM library WHERE id=?";
    private static final String deleteQuery="DELETE FROM library WHERE id =?";
    private static final String insertLibraryQuery="INSERT INTO library VALUES (library_id.nextval, ?, ?, ?, ?, ?, ?, ?)";
    private static final String updateLibraryQuery="UPDATE library SET isbn = ?, title = ?," +
                                            "cover = ?, description = ?, pages = ?," +
                                            "users = ?, workflow = ? WHERE id=?";

    public void createLibrary(Library library) {
           BasicConfigurator.configure();
        Connection conn=conn1.getConnection();
        try
        {
            PreparedStatement pstmt = conn.prepareStatement(insertLibraryQuery);

            pstmt.setString(1, library.getIsbn());
            pstmt.setString(2, library.getTitle());
            pstmt.setString(3, library.getCover());
            pstmt.setString(4, library.getDescription());
            pstmt.setInt(5, library.getPages());
            pstmt.setLong(6,library.getUsers().getId());
            pstmt.setInt(7,library.getWorkflow().getId());

            pstmt.executeUpdate();

            pstmt.close();
                conn.close();
        }
        catch (SQLException e)
        {
            while(e!=null)
            {
                log.error("SQLException"+e);
            }
        }
    }

    public Library readLibrary(long id) {
            BasicConfigurator.configure();
       Library library = new Library();
       Connection conn=conn1.getConnection();
       UsersDAO u = new OracleUsersDAO();
       BookWorkflowDAO w = new OracleBookWorkflowDAO();
       try
        {
            PreparedStatement stmt = conn.prepareStatement(selectQuery);
            stmt.setLong(1, id);

            ResultSet rs=stmt.executeQuery();

            while (rs.next())
            {
                library.setId(rs.getLong(1));
                library.setIsbn(rs.getString(2));
                library.setTitle(rs.getString(3));
                library.setCover(rs.getString(4));
                library.setDescription(rs.getString(5));
                library.setPages(rs.getInt(6));
                library.setUsers(u.readUsers(rs.getInt(7)));
                library.setWorkflow(w.readBookWorkflow(rs.getInt(8)));
            }
            rs.close();
            stmt.close();
                conn.close();
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
            BasicConfigurator.configure();
        Connection conn=conn1.getConnection();
        try
        {
            PreparedStatement pstmt = conn.prepareStatement(updateLibraryQuery);

            pstmt.setString(1, libraryNew.getIsbn());
            pstmt.setString(2, libraryNew.getTitle());
            pstmt.setString(3, libraryNew.getCover());
            pstmt.setString(4, libraryNew.getDescription());
            pstmt.setInt(5, libraryNew.getPages());
            pstmt.setLong(6, libraryNew.getUsers().getId());
            pstmt.setInt(7, libraryNew.getWorkflow().getId());
            pstmt.setLong(8, libraryOld.getId());

            pstmt.executeUpdate();

            pstmt.close();
                conn.close();
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
           BasicConfigurator.configure();
        Connection conn=conn1.getConnection();
        try
        {
            PreparedStatement stmt = conn.prepareStatement(deleteQuery);

            stmt.setLong(1, library.getId());

            stmt.executeUpdate();

            stmt.close();
                conn.close();
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
