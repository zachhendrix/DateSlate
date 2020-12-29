/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author zach.hendrix
 */
public class Clientele 
{
    
    private static ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
    
    
    //Declares variables that are used in the setting of the Part or Product ID number when creating new parts or products
    private static int customerIDCount = 0;
    
    
    
    //Adds a new part or Product to their respective Observable List
    public static void addCustomer(Customer newCustomer)
    {
        allCustomers.add(newCustomer);
    }

    public static void updateCustomer(int index, Customer selectedCustomer)
    {
        allCustomers.set(index, selectedCustomer);
    }
    

    public static boolean deleteCustomer(Customer selectedCustomer)
    {
        return getAllCustomers().remove(selectedCustomer);
    }


    public static ObservableList<Customer> getAllCustomers()
    {
        return allCustomers;
    }

    public static int getCustomerIDCount()
    {
        customerIDCount = allCustomers.size();
        return customerIDCount;
    }
    
    
    
}
