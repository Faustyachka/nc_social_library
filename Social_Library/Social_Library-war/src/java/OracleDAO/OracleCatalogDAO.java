/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package OracleDAO;

import OracleConnection.Oracle;
import TransferObjectInterface.CatalogDAO;
import TransferObject.Catalog;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author mazafaka
 */
public class OracleCatalogDAO implements CatalogDAO{

   private Oracle conn1;
    private ResultSet rs;
    private Connection conn;
    private static final String selectQuery="SELECT * FROM ? WHERE id=?";
    private static final String deleteQuery="DELETE FROM ? WHERE id =?";
    private static final String insertCatalogQuery="INSERT INTO catalog VALUES(?, ?, ?)";
    private static final String updateCatalogQuery="UPDATE catalog SET users=?, book=? WHERE id=?";
    private Catalog catalog;

     public OracleCatalogDAO()
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

    public void createCatalog(Catalog catalog) {
         try
        {
            PreparedStatement pstmt = conn.prepareStatement(insertCatalogQuery);

            pstmt.setLong(1, catalog.getId());

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

    public Catalog readCatalog(int id) {
        catalog = new Catalog();
        try
        {
            PreparedStatement stmt = conn.prepareStatement(selectQuery);
            stmt.setString(1, "calalog");
            stmt.setLong(2, catalog.getId());

            rs = stmt.executeQuery();

            while (rs.next())
            {
                catalog.setId(rs.getLong(1));
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

        return catalog;
    }

    public void updateCatalog(Catalog catalogOld, Catalog catalogNew) {
        try
        {
            PreparedStatement pstmt = conn.prepareStatement(updateCatalogQuery);

            pstmt.setLong(1, catalogOld.getId());

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

    public void deleteCatalog(Catalog catalog) {
        try
        {
            PreparedStatement stmt = conn.prepareStatement(deleteQuery);

            stmt.setString(1, "catalog");
            stmt.setLong(2, catalog.getId());

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

