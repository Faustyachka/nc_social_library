/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package OracleConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author mazafaka
 */
public class Oracle {
    private DataSource dataSource;
    private static Connection conn;

    private Oracle()
    {
        try
        {
            Locale.setDefault(Locale.ENGLISH);
            Context ic = new InitialContext();
            dataSource = (DataSource) ic.lookup("jdbc/test");
            //conn = dataSource.getConnection();
            //conn.setAutoCommit(true);
        }
        catch(NamingException e)
        {
            System.out.println("Cannot retrieve jdbc/test"+e.getMessage());
        }
    }

    public Connection getConnection()
            throws IOException
    {
        try
        {
            conn = dataSource.getConnection();
            conn.setAutoCommit(true);
        }
        
        catch(SQLException e)
        {
            while(e!=null)
            {
                e.printStackTrace();
                e=e.getNextException();
            }
        }
        return conn;
    }

  

}
