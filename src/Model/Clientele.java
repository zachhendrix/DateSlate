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
    public static void addCustomer(Customer newPart)
    {
        allCustomers.add(newPart);
    }

    public static void updateCustomer(int index, Customer selectedPart)
    {
        allCustomers.set(index, selectedPart);
    }
    
    //Deletes a Part or Product to their respective Observable List
    public static boolean deleteCustomer(Customer selectedPart)
    {
        return getAllCustomers().remove(selectedPart);
    }

    //Returns all Parts or Products from their respective lists
    public static ObservableList<Customer> getAllCustomers()
    {
        return allCustomers;
    }

    //Methods used to set the Part or Product ID number when creating new parts or products
    public static int getCustomerIDCount()
    {
        customerIDCount = allCustomers.size();
        return customerIDCount;
    }
    
}
