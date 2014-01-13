package com.sociallibrary.connection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
/*
 * @author Pavlova Nastya
 */
public class ConnectionProvider
{

    private static Connection conn = null;

    private ConnectionProvider(){}

    public synchronized static Connection getConnection()
    {
        try
        {
                Locale.setDefault(Locale.ENGLISH);
                Context ic = new InitialContext();
                DataSource dataSource = (DataSource) ic.lookup("jdbc/test");
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