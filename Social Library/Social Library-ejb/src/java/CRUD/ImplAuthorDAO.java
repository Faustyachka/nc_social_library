/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package CRUD;

import DAO.OracleDAOFactory;
import Enity_Interfaces.AuthorDAO;
import Entity.Author;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author mazafaka
 */

public class ImplAuthorDAO implements AuthorDAO{

    private Connection conn;
    private OracleDAOFactory conn1;
    private ResultSet rs;
    private static final String selectQuery="SELECT * FROM ? WHERE id=?";
    private static final String deleteQuery="DELETE FROM ? WHERE id =?";
    private static final String insertAuthorQuery="INSERT INTO author VALUES (?, ?)";
    private static final String updateAuthorQuery="UPDATE author SET author=? WHERE id=?";
    private Author author;

    public ImplAuthorDAO()
    {
        try
        {
        conn=conn1.getConnection();
        }
        catch(IOException e1)
        {
            System.out.println("IOExeption:"+e1);
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

        return author;
    }

    public void updateAuthor(Author authorOld, Author authorNew) {
        try
        {
            PreparedStatement pstmt = conn.prepareStatement(updateAuthorQuery);

            pstmt.setString(1, authorNew.getAuthor());
            pstmt.setLong(2, authorOld.getId());

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

    public void deleteAuthor(Author author) {
        try
        {
            PreparedStatement stmt = conn.prepareStatement(deleteQuery);

            stmt.setString(1, "author");
            stmt.setLong(2, author.getId());

            stmt.executeUpdate();
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

}
