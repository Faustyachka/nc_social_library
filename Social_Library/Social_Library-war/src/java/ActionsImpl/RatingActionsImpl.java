/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ActionsImpl;

import ActionsInterfaces.LibraryActions;
import ActionsInterfaces.RatingActions;
import OracleConnection.Oracle;
import OracleDAO.OracleLibraryDAO;
import com.sociallibrary.crud.RatingCRUD;
import OracleDAO.OracleUsersDAO;
import TransferObject.Library;
import TransferObject.Rating;
import com.sociallibrary.EntitiesInterfaces.LibraryDAO;
import com.sociallibrary.EntitiesInterfaces.RatingDAO;
import com.sociallibrary.EntitiesInterfaces.UserDAO;
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
public class RatingActionsImpl implements RatingActions {
    
     public static final Logger log = Logger.getLogger(UsersActionsImpl.class);
    private Library library = new Library();

    public RatingActionsImpl() {
        library = new Library();
    }
    
    public List<Rating> getRatingsByBookId(long id){
        BasicConfigurator.configure();
        RatingCRUD u = new RatingCRUD();
        List<Rating> lList = new ArrayList<Rating>();
        String selectParametr = "select *  from rating where book= ?";
        try {

            Oracle conn1 = new Oracle();
            Connection conn = conn1.getConnection();
            PreparedStatement stmt = conn.prepareStatement(selectParametr);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lList.add(u.readRating(rs.getInt("id")));
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

    public Rating getRatingsByBookAndUserIds(long userId, long bookId){
        BasicConfigurator.configure();
        RatingCRUD u = new RatingCRUD();
        Rating rating = null;
        String selectParametr = "select * from rating where book = ? and users = ?";
        try {

            Oracle conn1 = new Oracle();
            Connection conn = conn1.getConnection();
            PreparedStatement stmt = conn.prepareStatement(selectParametr);
            stmt.setLong(1, bookId);
            stmt.setLong(2, userId);
            ResultSet rs = stmt.executeQuery();
            //while(rs.next())
            rs.next();
            rating = u.readRating(rs.getInt("id"));
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            while (e != null) {
                log.error("SQLException" + e);
            }
        }

        return rating;
    }

}
