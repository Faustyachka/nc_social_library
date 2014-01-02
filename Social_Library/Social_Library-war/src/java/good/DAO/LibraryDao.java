/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package good.DAO;

import good.Entities.Library;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import good.ConnectionProvider;
import good.Entities.BookWorkflow;
import good.Entities.User;
/**
 *
 * @author Антон
 */
public class LibraryDao {

        private Connection connection;

    public LibraryDao() {
        connection = ConnectionProvider.getConnection();
    }

    public boolean createBook(Library library) {
        try {
                String sqlRequest =
                        "INSERT INTO LIBRARY (ID,ISBN,TITLE,COVER,DESCRIPTION,PAGES,USERs,WORKFLOW) " +
                        "values(?,'?','?','?','?', ?, ?, ?)";

            PreparedStatement ps = connection.prepareStatement(sqlRequest);

            ps.setLong(1, library.getId());
            ps.setString(2, library.getIsbn());
            ps.setString(3, library.getTitle());
            ps.setString(4, library.getCover());
            ps.setString(5, library.getDescription());
            ps.setInt(6, library.getPages());
            ps.setLong(7, library.getUser().getId());
            ps.setInt(8, library.getWorkflow().getId());
            ps.executeUpdate();

            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Library getBookById(int id) {
        Library library = new Library();
        try {
                String sqlRequest =
                        "SELECT * FROM Library WHERE id=?";
            PreparedStatement ps = connection.prepareStatement(sqlRequest);

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                library.setId(rs.getLong("ID"));
                library.setIsbn(rs.getString("ISBN"));
                library.setTitle(rs.getString("TITLE"));
                library.setCover(rs.getString("COVER"));
                library.setDescription(rs.getString("DESCRIPTION"));
                library.setPages(rs.getInt("PAGES"));
                library.setUser(new UserDao().getUserById(rs.getLong("USERs")));
                library.setWorkflow(new BookWorkflowDao().getBookWorkflowById(rs.getInt("WORKFLOW")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return library;
    }

    public List<Library> getAllBooks() {
        List<Library> librarys = new ArrayList<Library>();
        PreparedStatement ps;
        ResultSet rs;
        try {
                String sqlRequest =
                        "SELECT * FROM Library";
            ps = connection.prepareStatement(sqlRequest);
            rs = ps.executeQuery();

            while (rs.next()) {
                Library library = new Library();
                library.setId(rs.getLong("ID"));
                library.setIsbn(rs.getString("ISBN"));
                library.setTitle(rs.getString("TITLE"));
                library.setCover(rs.getString("COVER"));
                library.setDescription(rs.getString("DESCRIPTION"));
                library.setPages(rs.getInt("PAGES"));
                library.setUser(new UserDao().getUserById(rs.getLong("USERs")));
                library.setWorkflow(new BookWorkflowDao().getBookWorkflowById(rs.getInt("WORKFLOW")));
                librarys.add(library);
            }

            connection.commit();
            close(connection, ps, rs);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException("Error occurred!", e);
        }

        return librarys;
    }

    public boolean removeBook(int id) {
        try {
                String sqlRequest = "DELETE FROM Library WHERE id=?";
            PreparedStatement ps = connection.prepareStatement(sqlRequest);
            ps.setInt(1, id);
            ps.executeUpdate();

            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
      }

    public boolean  editBook(Library library) {
        try {
                String sqlRequest = "UPDATE Library SET ISBN='?',TITLE='?',COVER='?'," +
                        "DESCRIPTION='?',PAGES=?,USERs=?,WORKFLOW=? WHERE ID=?";
            PreparedStatement ps = connection.prepareStatement(sqlRequest);

            String[] libraryParams = new String[8];
            libraryParams = library.toStringList().toArray(libraryParams);
            for(int i=1; i < 8; i++)
                ps.setString(i, libraryParams[i]);
            ps.setString(8, libraryParams[0]);

            ps.executeUpdate();
            connection.prepareStatement("commit").executeUpdate();

            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void close (Connection connection, Statement statement, ResultSet rs) {
    if (rs != null) {
        try { rs.close(); } catch (Exception e) { e.printStackTrace();}
    }
    if (statement != null) {
        try { statement.close(); } catch (Exception e) { e.printStackTrace(); }
    }
    if (connection != null) {
        try { connection.close(); } catch (Exception e) { e.printStackTrace(); }
    }
}

}
