/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sociallibrary.connections;

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
public class ConnectionOracle {
    
    /*
     Constructor
    */
    private Connection conn;
    private DataSource dataSource;
    private static ConnectionOracle connectionOracle;
      
    private ConnectionOracle() 
            
    {
        try 
        {
            Locale.setDefault(Locale.ENGLISH);
            Context ic = new InitialContext();
            dataSource = (DataSource) ic.lookup("jdbc/test");
        }
        catch (NamingException e) 
        {
            System.out.println("Cannot retrieve jdbc/HRDB"+e.getMessage());
        }
    }
    
    public Connection getConnection() 
             throws SQLException,IOException
    {
		if (connectionOracle == null) 
                {
			connectionOracle = new ConnectionOracle();
                        conn = dataSource.getConnection();
                        conn.setAutoCommit(false);
                          
		}
        
		return conn;
    }
    
    public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
}
        

