/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package OracleDAO;

import OracleConnection.Oracle;
import TransferObjectInterface.CatalogDAO;
import TransferObject.Catalog;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;
/**
 *
 * @author mazafaka
 */
public class OracleCatalogDAO implements CatalogDAO{
    public static final Logger log=Logger.getLogger(OracleAuthorDAO.class);
   private Oracle conn1;
    private static final String selectQuery="SELECT * FROM catalog WHERE id=?";
    private static final String deleteQuery="DELETE FROM catalog WHERE id =?";
    private static final String insertCatalogQuery="INSERT INTO catalog VALUES(?, ?, ?)";
    private static final String updateCatalogQuery="UPDATE catalog SET users=?, book=? WHERE id=?";

    public void createCatalog(Catalog catalog) {
        Connection conn=conn1.getConnection();
         try
        {
            PreparedStatement pstmt = conn.prepareStatement(insertCatalogQuery);

            pstmt.setLong(1, catalog.getId());

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

    public Catalog readCatalog(int id) {
        Catalog catalog = new Catalog();
        Connection conn=conn1.getConnection();
        try
        {
            PreparedStatement stmt = conn.prepareStatement(selectQuery);

            stmt.setLong(1, catalog.getId());

            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                catalog.setId(rs.getLong(1));
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

        return catalog;
    }

    public void updateCatalog(Catalog catalogOld, Catalog catalogNew) {
        Connection conn=conn1.getConnection();
        try
        {
            PreparedStatement pstmt = conn.prepareStatement(updateCatalogQuery);

            pstmt.setLong(1, catalogOld.getId());

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

    public void deleteCatalog(Catalog catalog) {
        Connection conn=conn1.getConnection();
        try
        {
            PreparedStatement stmt = conn.prepareStatement(deleteQuery);

            stmt.setLong(1, catalog.getId());

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

