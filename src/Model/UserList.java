package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * @author Zach Hendrix
 * A Class that has an ObservableList in which the User class object is saved to.
 * The class also contains add and get all functions
 */
public class UserList
{

    private static ObservableList<User> allUsers = FXCollections.observableArrayList();

    /**
     * Used to add an object into the "allUsers" list
     * @param newUser
     */
    public static void addUser(User newUser)
    {
        allUsers.add(newUser);
    }

    /**
     * Returns all customers in the "allUsers" ObservableList.
     * @return
     */
    public static ObservableList<User> getAllUsers()
    {
        return allUsers;
    }

    /**
     * Returns a Country in the "allCountries" ObservableList by its Integer. Used to specify associated
     * object when loading in the Appointments from the server
     * @param userID
     * @return
     */
    public static User getByID(int userID)
    {
        for(User u : allUsers)
        {
            if(u.getUserID() == userID)
            {
                return u;
            }
        }

        return null;
    }

}
