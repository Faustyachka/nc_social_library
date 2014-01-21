/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sociallibrary.connection;

/**
 *
 * @author Антон
 */
import com.sociallibrary.actions.UsersActions;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import oracle.jdbc.pool.OracleDataSource;
import org.apache.log4j.Logger;

public class ConnectionProvider {

    private /*volatile*/static Connection con = null;
     public static final Logger log = Logger.getLogger(UsersActions.class);

    private ConnectionProvider(){}

    public /*synchronized*/static Connection getConnection()
    {
        
             
        try {
//            OracleDataSource ods = new OracleDataSource();
//            String url = "jdbc:oracle:thin:@localhost:1521:xe";
//            ods.setURL(url);
//            ods.setUser("pavel");
//            ods.setPassword("per123");
//            con = ods.getConnection();
//
             if ((con == null)||(con.isClosed())) {

                Locale.setDefault(Locale.ENGLISH);
                Context ic = new InitialContext();
                DataSource dataSource = (DataSource) ic.lookup("jdbc/test");
                con  = dataSource.getConnection();
                con.setAutoCommit(true);
                if (con==null) log.info("Some message");
               
             }
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