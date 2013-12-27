/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package OracleConnection;

import java.util.Locale;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class Oracle {

    private DataSource dataSource;
    private Connection conn;

    public synchronized Connection getConnection()
    {
        try
        {
            Locale.setDefault(Locale.ENGLISH);
            Context ic = new InitialContext();
            dataSource = (DataSource) ic.lookup("jdbc/test");
            conn = dataSource.getConnection();
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


