/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ActionsImpl;

import ActionsInterfaces.LibraryActions;
import OracleConnection.Oracle;
import OracleDAO.OracleLibraryDAO;
import OracleDAO.OracleUsersDAO;
import TransferObject.Library;
import TransferObjectInterface.LibraryDAO;
import TransferObjectInterface.UsersDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 *
 * @author Антон
 */
public class LibraryActionsImpl implements LibraryActions {
    public static final Logger log = Logger.getLogger(UsersActionsImpl.class);
    private Library library = new Library();

    public LibraryActionsImpl() {
        library = new Library();
    }

    public List<Library> searchBooksByParameter(String where, String what) {
        BasicConfigurator.configure();
        LibraryDAO u = new OracleLibraryDAO();
        List<Library> lList = new ArrayList<Library>();
        String selectParametr = "select id  from library where "+where+" = ?";
        try {

            Oracle conn1 = new Oracle();
            Connection conn = conn1.getConnection();
            PreparedStatement stmt = conn.prepareStatement(selectParametr);
            stmt.setString(1, what);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                library = u.readLibrary(rs.getInt("id"));
                lList.add(library);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            while (e != null) {
                log.error("SQLException" + e);
            }
        }

        return lList;
    }

    public List<Library> getBooksByIdInInterval(long from, long to){
        BasicConfigurator.configure();
        LibraryDAO u = new OracleLibraryDAO();
        Library lib = new Library();
        List<Library> lList = new ArrayList<Library>();
        for(long i = from; i<to; i++){
        }

        return lList;
    }

}
