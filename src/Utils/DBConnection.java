/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Model.*;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import Views.AddAppointmentMenuController;
import Views.CustomerDataController;
import Views.LoginMenuController;
import javafx.collections.ObservableList;

/**
 * @author Zach Hendrix
 */
public class DBConnection 
{
    private static final String protocol = "jdbc";
    private static final String vendorName = ":mysql:";
    private static final String serverName = "//wgudb.ucertify.com/WJ06u79";
    
    private static final String databaseURL = protocol + vendorName + serverName;
    
    private static final String MYSQLJDBCDriver = "com.mysql.cj.jdbc.Driver";
    public static Connection conn = null;
    
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
    
    public static Boolean checkUserData(String username, String password) throws SQLException
    {

        Statement statement = DBQuery.getStatement();
        String query = "SELECT * FROM users WHERE User_Name='" + username + "' AND Password='" + password + "'";
        ResultSet rs = statement.executeQuery(query);
        if(rs.next())
        {
            if(rs.getString("User_Name").equals(username) && rs.getString("password").equals(password))
            {
                //Section B
                //Simple Lambda expression to show that the Java Application Thread is running (Other Expression is in FLDivisionList
                Runnable runProcess = () ->
                {
                    System.out.println(Thread.currentThread().getName() + " is running");
                };

                runProcess.run();

                return true;
            }
        }
        return false;
    }
    
    public static void loadCountryData() throws SQLException
    {
        DBQuery.setStatement(conn);
        Statement statement = DBQuery.getStatement();
        String selectStatement = "SELECT * From countries";
        statement.execute(selectStatement);
        ResultSet rs = statement.getResultSet();
        
        while(rs.next())
        {
            int countryID = rs.getInt("Country_ID");
            String countryName = rs.getString("Country");
            Countries.addCountry(new Country(countryID, countryName));
            
        }
    }
    
    public static void loadFirstLevelData() throws SQLException
    {
        DBQuery.setStatement(conn);
        Statement statement = DBQuery.getStatement();
        String selectStatement = "SELECT * From first_level_divisions";
        statement.execute(selectStatement);
        ResultSet rs = statement.getResultSet();
        
        while(rs.next())
        {
            int divisionID = rs.getInt("Division_ID");
            String flDivisionName = rs.getString("Division");
            int countryID = rs.getInt("COUNTRY_ID");
            FLDivisionList.addFLDivision(new FLDivision(divisionID, flDivisionName,countryID));

        }
    }

    public static void loadCustomerData() throws SQLException
    {
        DBQuery.setStatement(conn);
        Statement statement = DBQuery.getStatement();

        String selectStatement = "SELECT * From customers, first_level_divisions WHERE customers.Division_ID = first_level_divisions.Division_ID";
        statement.execute(selectStatement);
        ResultSet rs = statement.getResultSet();



        while(rs.next())
        {

            int customerID = rs.getInt("Customer_ID");
            String customerName = rs.getString("Customer_Name");
            String address = rs.getString("Address");
            String postalCode = rs.getString("Postal_Code");
            int countryID = rs.getInt("COUNTRY_ID");
            Country country = Countries.getByID(countryID);
            int stateInt = rs.getInt("Division_ID");
            FLDivision state = FLDivisionList.lookupDivision(stateInt);
            String phone = rs.getString("Phone");

            Clientele.addCustomer(new Customer(customerID, customerName, address, postalCode, country, state, phone));

            CustomerDataController.generateIDNum = customerID + 1;
        }
    }


    public static void loadContactData() throws SQLException
    {
        DBQuery.setStatement(conn);
        Statement statement = DBQuery.getStatement();

        String selectStatement = "SELECT * From contacts";
        statement.execute(selectStatement);
        ResultSet rs = statement.getResultSet();

        while(rs.next())
        {

            int contactID = rs.getInt("Contact_ID");
            String contactName = rs.getString("Contact_Name");
            String email = rs.getString("Email");


            ContactList.addContact(new Contact(contactID, contactName, email));
        }
    }

    public static void loadUserData() throws SQLException
    {
        DBQuery.setStatement(conn);
        Statement statement = DBQuery.getStatement();

        String selectStatement = "SELECT * From users";
        statement.execute(selectStatement);
        ResultSet rs = statement.getResultSet();



        while(rs.next())
        {

            int userID = rs.getInt("User_ID");
            String userName = rs.getString("User_Name");
            String userPass = rs.getString("Password");

            UserList.addUser(new User(userID, userName,userPass));
        }
    }


    public static void loadAppointmentData() throws SQLException
    {
        DBQuery.setStatement(conn);
        Statement statement = DBQuery.getStatement();
        String selectStatement = "SELECT * From appointments";
        statement.execute(selectStatement);
        ResultSet rs = statement.getResultSet();

        while(rs.next())
        {
            int appointmentID = rs.getInt("Appointment_ID");
            String appTitle = rs.getString("Title");
            String appLocation = rs.getString("Description");
            String appDescription = rs.getString("Location");
            String appType = rs.getString("Type");

            //TODO: startDate and endDate times get loaded in wrong

            LocalDateTime startDate = rs.getTimestamp("Start").toLocalDateTime();
            System.out.println(startDate);

            LocalDateTime endDate = rs.getTimestamp("End").toLocalDateTime();


            int appContactInt = rs.getInt("Contact_ID");
            Contact appContact = ContactList.getByID(appContactInt);

            int appCustomerInt = rs.getInt("Customer_ID");
            Customer appCustomer = Clientele.getByID(appCustomerInt);

            int appUserInt = rs.getInt("User_ID");
            User appUser = UserList.getByID(appUserInt);



            Schedule.addAppointment(new Appointment(appointmentID, appTitle,appLocation, appDescription, appContact, appType, startDate,endDate,appCustomer,appUser));
            AddAppointmentMenuController.generateAppIDNum = ++appointmentID;
        }
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
