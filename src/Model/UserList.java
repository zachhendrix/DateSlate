package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UserList
{

    private static ObservableList<User> allUsers = FXCollections.observableArrayList();

    //Adds a new part or Product to their respective Observable List
    public static void addUser(User newUser)
    {
        allUsers.add(newUser);
    }

    public static ObservableList<User> getAllUsers()
    {
        return allUsers;
    }

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
