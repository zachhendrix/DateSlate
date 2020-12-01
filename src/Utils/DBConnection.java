/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Model.Customer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.collections.ObservableList;

/**
 *
 * @author Z
 */
public class DBConnection 
{
    private static final String protocol = "jdbc";
    private static final String vendorName = ":mysql:";
    private static final String serverName = "//wgudb.ucertify.com/WJ06u79";
    
    private static final String databaseURL = protocol + vendorName + serverName;
    
    private static final String MYSQLJDBCDriver = "com.mysql.cj.jdbc.Driver";
    private static Connection conn = null;
    
    private static final String username = "U06u79";
    private static String password = "53688873417";
    private static ObservableList<Customer> countries;
    private static ObservableList<Customer> firstLevelDivision;
    
    
    public static Connection startConnection()
    {
        try
        {
            Class.forName(MYSQLJDBCDriver);
            conn = (Connection)DriverManager.getConnection(databaseURL, username, password);
            System.out.println("Connection Successful!");
        }
        
        catch (ClassNotFoundException exception)
        {
            System.out.println(exception.getMessage());
        }
        
        catch (SQLException exception)
        {
            System.out.println("Error: " + exception.getMessage());
        }
        
        return conn;
    }
    
    public static void closeConnection()throws SQLException
    {
        try
        {
            conn.close();
            System.out.println("Connection Closed!");
        }
        
        catch(SQLException exception)
        {
            System.out.println("Error: " + exception.getMessage());
        }
    }
    
}
