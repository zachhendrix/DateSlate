package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ContactList
{
    private static ObservableList<Contact> allContacts = FXCollections.observableArrayList();

    //Adds a new part or Product to their respective Observable List
    public static void addContact(Contact newContact)
    {
        allContacts.add(newContact);
    }

    public static ObservableList<Contact> getAllContacts()
    {
        return allContacts;
    }

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
