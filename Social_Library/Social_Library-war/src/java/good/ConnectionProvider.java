/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package good;

/**
 *
 * @author Антон
 */
import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionProvider {

    private static Connection con = null;
    private static Context ic;
    private static DataSource dataSource;

    private ConnectionProvider(){}

    public static Connection getConnection() {
        if (con != null)
            return con;
        else {
            try {
                Locale.setDefault(Locale.ENGLISH);
                ic = new InitialContext();
                dataSource = (DataSource) ic.lookup("jdbc/test");
                con = dataSource.getConnection();

            }
            catch(NamingException e)
            {
                System.out.println("Cannot retrieve jdbc/test"+e.getMessage());
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
            return con;
        }

    }
}