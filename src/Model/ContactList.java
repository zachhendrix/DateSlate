package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author Zach Hendrix
 * A Class that has an ObservableList in which the Contact class object is saved to.
 * The class also contains add and get all functions
 */
public class ContactList
{
    private static ObservableList<Contact> allContacts = FXCollections.observableArrayList();

    /**
     * Used to add an object into the "allContacts" list
     * @param newContact
     */
    public static void addContact(Contact newContact)
    {
        allContacts.add(newContact);
    }

    /**
     * Returns all Contacts in the "allContacts" ObservableList
     * @return
     */
    public static ObservableList<Contact> getAllContacts()
    {
        return allContacts;
    }

    /**
     * Returns a Customer in the "allContacts" ObservableList by its Integer. Used to specify associated
     * object when loading in the Appointments from the server
     * @param contactID
     * @return
     */
    public static Contact getByID(int contactID)
    {
        for(Contact c : allContacts)
        {
            if(c.getContactID() == contactID)
            {
                return c;
            }
        }

        return null;
    }


}
