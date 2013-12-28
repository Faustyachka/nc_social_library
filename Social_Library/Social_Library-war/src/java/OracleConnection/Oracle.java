/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package OracleConnection;

import java.io.FileNotFoundException;
import java.util.Locale;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Oracle {

    private DataSource dataSource;
    private Connection conn;
    public synchronized Connection getConnection() throws FileNotFoundException
    {
        try {

            Locale.setDefault(Locale.ENGLISH);
            Context ic = new InitialContext();
            dataSource = (DataSource) ic.lookup("jdbc/test");
            conn = dataSource.getConnection();
//            ResourceBundle jdbc = ResourceBundle.getBundle("connect", Locale.ENGLISH);
//            //ResourceBundle name = ResourceBundle.getBundle("name");
//            //ResourceBundle password = ResourceBundle.getBundle("password");
//            conn = DriverManager.getConnection(jdbc.getString("jdbc"), jdbc.getString("name"), jdbc.getString("password"));
            conn = DriverManager.getConnection("thin:@localhost:1521:XE","faust","");
            conn.setAutoCommit(true);
        }
        catch(NamingException e)
        {
           System.out.println("Cannot retrieve jdbc/test"+e.getMessage());
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return conn;
    }
}


