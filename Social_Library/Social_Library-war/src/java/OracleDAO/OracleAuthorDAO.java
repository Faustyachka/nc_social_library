/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package OracleDAO;


import OracleConnection.Oracle;
import TransferObject.Author;
import TransferObjectInterface.AuthorDAO;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import org.apache.log4j.Logger;
//import org.apache.taglibs.standard.lang.jstl.Logger;


/**
 *
 * @author mazafaka
 */


public class OracleAuthorDAO implements AuthorDAO{

    public static final Logger log=Logger.getLogger(OracleAuthorDAO.class);
    private Oracle conn1;
    private static final String selectQuery="SELECT * FROM author WHERE id=?";
    private static final String deleteQuery="DELETE FROM author WHERE id =?";
    private static final String insertAuthorQuery="INSERT INTO author VALUES (?, ?)";
    private static final String updateAuthorQuery="UPDATE author SET author=? WHERE id=?";
    private Author author;

    public void createAuthor(Author author) {
        try
        {

            Connection conn=conn1.getConnection();
            try
            {
            PreparedStatement pstmt = conn.prepareStatement(insertAuthorQuery);
            pstmt.setLong(1, author.getId());
            pstmt.setString(2, author.getAuthor());
            pstmt.executeUpdate();
            }
            finally
            {
                conn.close();
            }
            
        }
        catch (FileNotFoundException ex) {
            java.util.logging.Logger.getLogger(OracleAuthorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }        catch (SQLException e)
        {
            while(e!=null)
            {
                log.error("SQLException"+e);
            }
        }

    }

    public Author readAuthor(int id) {
        author = new Author();
        try
        {
            Connection conn=conn1.getConnection();
            try
            {
            PreparedStatement stmt = conn.prepareStatement(selectQuery);

            stmt.setLong(1, author.getId());
            ResultSet rs=stmt.executeQuery();

            while (rs.next())
            {
                author.setId(rs.getLong(1));
                author.setAuthor(rs.getString(2));
            }
            rs.close();
            }
            finally
            {
                conn.close();
            }
            
        }
        catch (FileNotFoundException ex) {
            java.util.logging.Logger.getLogger(OracleAuthorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }        catch (SQLException e)
        {
            while(e!=null)
            {
                log.error("SQLException"+e);
            }
        }

        return author;
    }

    public void updateAuthor(Author authorOld, Author authorNew) {
        try
        {
         Connection conn=conn1.getConnection();
        try
        {
            PreparedStatement pstmt = conn.prepareStatement(updateAuthorQuery);

            pstmt.setString(1, authorNew.getAuthor());
            pstmt.setLong(2, authorOld.getId());

            pstmt.executeUpdate();
        }
        finally
        {
            conn.close();
        }
        }
        catch (FileNotFoundException ex) {
            java.util.logging.Logger.getLogger(OracleAuthorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }        catch (SQLException e)
        {
            while(e!=null)
            {
                log.error("SQLException"+e);
            }
        }
    }

    public void deleteAuthor(Author author) {
        try
        {
        Connection conn=conn1.getConnection();
        try
        {
            PreparedStatement stmt = conn.prepareStatement(deleteQuery);

            stmt.setLong(1, author.getId());

            stmt.executeUpdate();
        }
        finally
        {
            conn.close();
        }
        }
        catch (FileNotFoundException ex) {
            java.util.logging.Logger.getLogger(OracleAuthorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }        catch (SQLException e)
        {
            while(e!=null)
            {
                log.error("SQLException"+e);
            }
        }
    }

}
