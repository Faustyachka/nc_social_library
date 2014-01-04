package com.sociallibrary.crud;

import com.sociallibrary.connection.ConnectionProvider;
import com.sociallibrary.entity.*;
import com.sociallibrary.icrud.*;
import org.apache.log4j.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Time;
//import java.sql.Timestamp;

public class CatalogCRUD implements ICatalogCRUD {

    private Connection connection;
    public static final Logger log = Logger.getLogger(AuthorCRUD.class);
    private static final String selectQuery = "SELECT * FROM catalog WHERE id=?";
    private static final String deleteQuery = "DELETE FROM catalog WHERE id =?";
    private static final String insertCatalogQuery = "INSERT INTO catalog VALUES(catalog_id.nextval, ?, ?,?,?)";
    private static final String updateCatalogQuery = "UPDATE catalog SET users=?, book=?, event_time=?, status=?, WHERE id=?";

     public  CatalogCRUD()
    {
        connection = ConnectionProvider.getConnection();
    }
     
    public void createCatalog(Catalog catalog) {
        BasicConfigurator.configure();
        try
        {
            PreparedStatement pstmt = connection.prepareStatement(insertCatalogQuery);

            pstmt.setLong(1, catalog.getUser().getId());
            pstmt.setLong(2, catalog.getBook().getId());
            //pstmt.setTimestamp(3, (Time) catalog.getEventTime());
           // pstmt.setShort(4, catalog.getStatus().getId());

            pstmt.executeUpdate();

            pstmt.close();
                connection.close();
        } catch (SQLException e) {
            while (e != null) {
                log.error("SQLException" + e);
            }
        }
    }

    public Catalog readCatalog(int id) {
        BasicConfigurator.configure();
        Catalog catalog = new Catalog();
        IUserCRUD u = new UserCRUD();
        ILibraryCRUD l = new LibraryCRUD();
        IBookStatusCRUD s = new BookStatusCRUD();
        try {
            PreparedStatement stmt = connection.prepareStatement(selectQuery);

            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                catalog.setId(rs.getLong(1));
                catalog.setUser(u.readUsers(rs.getInt(2)));
                catalog.setBook(l.readLibrary(rs.getInt(3)));
                catalog.setStatus(s.readBookStatus(rs.getInt(4)));
            }
            rs.close();
            stmt.close();
                connection.close();
        } catch (SQLException e) {
            while (e != null) {
                log.error("SQLException" + e);
            }
        }

        return catalog;
    }

    public void updateCatalog(Catalog catalogOld, Catalog catalogNew) {
        BasicConfigurator.configure();
        try {
            PreparedStatement pstmt = connection.prepareStatement(updateCatalogQuery);
            pstmt.setLong(1, catalogOld.getUser().getId());
            pstmt.setLong(2, catalogOld.getBook().getId());
            //pstmt.setShort(3, catalogOld.getStatus().getId());
            pstmt.setLong(4, catalogOld.getId());

            pstmt.executeUpdate();

            pstmt.close();
                connection.close();
        } catch (SQLException e) {
            while (e != null) {
                log.error("SQLException" + e);
            }
        }
    }

    public void deleteCatalog(Catalog catalog) {
        BasicConfigurator.configure();
        try {
            PreparedStatement stmt = connection.prepareStatement(deleteQuery);

            stmt.setLong(1, catalog.getId());

            stmt.executeUpdate();

            stmt.close();
                connection.close();
        } catch (SQLException e) {
            while (e != null) {
                log.error("SQLException" + e);
            }
        }
    }
}

