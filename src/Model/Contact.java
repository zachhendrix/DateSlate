package Model;

/**
 * Sets up the Contact class.
 * The Contact Object is used in the Appointment Object.
 * @author Zach Hendrix
 */
public class Contact
{
    private int contactID;
    private String contactName;
    private String email;

    /**
     *A constructor used to create a Contact object
     * @param contactID
     * @param contactName
     * @param email
     */
    public Contact(int contactID, String contactName, String email)
    {
        this.contactID = contactID ;
        this.contactName = contactName;
        this.email = email;
    }

    /**
     * Returns the contactID Integer from the Contact Object
     * @return
     */
    public int getContactID()
    {
        return contactID;
    }

    /**
     * Returns the contactName String from the Contact Object
     * @return
     */
    public String getContactName()
    {
        return contactName;
    }

    /**
     * Returns the email String from the Contact Object
     * @return
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * Returns the indexed Contact Objects name for proper ComboBox usage
     * @return
     */
    @Override
    public String toString()
    {
        return(contactName);
    }



}
