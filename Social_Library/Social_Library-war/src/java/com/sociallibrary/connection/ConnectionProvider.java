/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sociallibrary.connection;

/**
 *
 * @author �����
 */
import java.sql.Connection;
import java.sql.SQLException;
//import java.util.Locale;
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//import javax.sql.DataSource;
import oracle.jdbc.pool.OracleDataSource;
import java.util.Locale;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import oracle.jdbc.pool.OracleDataSource;





public class ConnectionProvider {

    private /*volatile*/static Connection con = null;

    private ConnectionProvider(){}

    public /*synchronized*/static Connection getConnection()
    {
        try {
//            OracleDataSource ods = new OracleDataSource();
//            String url = "jdbc:oracle:thin:@localhost:1521:xe";
//            ods.setURL(url);
//            ods.setUser("mazafaka");
//            ods.setPassword("mazafaka");
//            con = ods.getConnection();
           
           
            if ((con == null)||(con.isClosed())) {
                Locale.setDefault(Locale.ENGLISH);
                Context ic = new InitialContext();
                DataSource dataSource = (DataSource) ic.lookup("jdbc/test");
                con = dataSource.getConnection();
                con.setAutoCommit(true);
                con = dataSource.getConnection();
                con.setAutoCommit(true);
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