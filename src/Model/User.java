package Model;

/**
 * Sets up the User class.
 * The User Object is used in the Appointment Object
 * @author Zach Hendrix
 */
public class User
{
    private int userID;
    private String userName;
    private String password;

    /**
     * A constructor used to create an User object
     * @param userID
     * @param userName
     * @param password
     */
    public User(int userID, String userName, String password)
    {
        this.userID = userID ;
        this.userName = userName;
        this.password = password;
    }

    /**
     * Returns the userID Integer from the User Object
     * @return
     */
    public int getUserID()
    {
        return userID;
    }

    /**
     * Returns the userID Integer from the User Object
     * @param userID
     */
    public void setUserID(int userID)
    {
        this.userID = userID;
    }

    /**
     * Returns the userName String from the User Object
     * @return
     */
    public String getUserName()
    {
        return userName;
    }

    /**
     * Returns the password String from the User Object
     * @return
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * Returns the userID Integer from the User Object
     * @param password
     */
    public void setPassword(String password)
    {
        this.password = password;
    }

    /**
     * Returns the indexed User Objects name for proper ComboBox usage
     * @return
     */
    @Override
    public String toString()
    {
        return(userName);
    }

}
