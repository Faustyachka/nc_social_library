/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package OracleDAO;


import OracleConnection.Oracle;
import TransferObject.Author;
import TransferObjectInterface.AuthorDAO;
import java.io.IOException;
//import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
/**
 *
 * @author mazafaka
 */

public class OracleAuthorDAO implements AuthorDAO{

    
    private Oracle conn1;
    private ResultSet rs;
    private Connection conn;
    private static final String selectQuery="SELECT * FROM ? WHERE id=?";
    private static final String deleteQuery="DELETE FROM ? WHERE id =?";
    private static final String insertAuthorQuery="INSERT INTO author VALUES (?, ?)";
    private static final String updateAuthorQuery="UPDATE author SET author=? WHERE id=?";
    private Author author;

    public OracleAuthorDAO()
    {
        try
        {
            conn=conn1.getConnection();
        }
        catch(IOException e)
        {
            System.out.println("IOExeption:"+e);
        }
        
    }

    public void createAuthor(Author author) {
        try
        {

            PreparedStatement pstmt = conn.prepareStatement(insertAuthorQuery);

            pstmt.setLong(1, author.getId());
            pstmt.setString(2, author.getAuthor());

            pstmt.executeUpdate();
        if (conn != null)
            {
                conn.close();
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

    }

    public Author readAuthor(int id) {
        author = new Author();
        try
        {
            PreparedStatement stmt = conn.prepareStatement(selectQuery);
            stmt.setString(1, "author");
            stmt.setLong(2, author.getId());

            rs=stmt.executeQuery();

            while (rs.next())
            {
                author.setId(rs.getLong(1));
                author.setAuthor(rs.getString(2));
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

        return author;
    }

    public void updateAuthor(Author authorOld, Author authorNew) {
        try
        {
            PreparedStatement pstmt = conn.prepareStatement(updateAuthorQuery);

            pstmt.setString(1, authorNew.getAuthor());
            pstmt.setLong(2, authorOld.getId());

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

    public void deleteAuthor(Author author) {
        try
        {
            PreparedStatement stmt = conn.prepareStatement(deleteQuery);

            stmt.setString(1, "author");
            stmt.setLong(2, author.getId());

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
