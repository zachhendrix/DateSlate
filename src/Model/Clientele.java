package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author Zach Hendrix
 * A Class that has an ObservableList in which the Customer class object is saved to.
 * The class also contains add and get all functions
 */
public class Clientele 
{
    
    private static ObservableList<Customer> allCustomers = FXCollections.observableArrayList();

    /**
     * Used to add an object into the "allCustomers" list
     * @param newCustomer
     */
    public static void addCustomer(Customer newCustomer)
    {
        allCustomers.add(newCustomer);
    }

    /**
     * Used to delete an object from the "allCustomers" list at selected value in table
     * @param selectedCustomer
     * @return
     */
    public static boolean deleteCustomer(Customer selectedCustomer)
    {
        return getAllCustomers().remove(selectedCustomer);
    }

    /**
     * Returns all customers in the "allCustomers" ObservableList
     * @return
     */
    public static ObservableList<Customer> getAllCustomers()
    {
        return allCustomers;
    }

    /**
     * Returns a Customer in the "allCustomers" ObservableList by its Integer. Used to specify associated
     * object when loading in the Appointments from the server
     * @param customerID
     * @return
     */
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
