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
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionProvider {

    private static Connection con = null;

    private ConnectionProvider(){}

    public static Connection getConnection() {
        if (con != null)
            return con;
        else {
            try {
                Locale.setDefault(Locale.ENGLISH);
                Context ic = new InitialContext();
                DataSource dataSource = (DataSource) ic.lookup("jdbc/test");
                con = dataSource.getConnection();
//                String driver = "com.mysql.jdbc.Driver";
//                String url = "jdbc:mysql://localhost:3306/record";
//                String user = "root";
//                String password = "root";
//                Class.forName(driver);
//                con = DriverManager.getConnection(url, user, password);
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