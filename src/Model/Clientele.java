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

    public static Customer getByID(int customerID)
    {
        for(Customer c : allCustomers)
        {
            if(c.getCustomerID() == customerID)
            {
                return c;
            }
        }

        return null;
    }

    
    
    
}
