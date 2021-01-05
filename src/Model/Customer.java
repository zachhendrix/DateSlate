package Model;

/**
 * Sets up the Customer class.
 * The Customer Object is used in the Appointment Object.
 * @author Zach Hendrix
 */
public class Customer 
{
    
    private int customerID;  
    private String customerName;
    private String address;
    private String postalCode;
    private Country country;
    private FLDivision state;
    private String phone;

    /**
     * A constructor used to create an Customer object
     * @param customerID
     * @param customerName
     * @param address
     * @param postalCode
     * @param country
     * @param state
     * @param phone
     */
    public Customer(int customerID, String customerName, String address,String postalCode, Country country, FLDivision state, String phone)
    {
        this.customerID = customerID ;   
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.country = country;
        this.state = state;
        this.phone = phone;
    }


    /**
     * Returns the customerID Integer from the Customer Object
     * @return
     */
    public int getCustomerID() 
    {
        return customerID;
    }

    /**
     * Returns the customerName String from the Customer Object
     * @return
     */
    public String getCustomerName() 
    {
        return customerName;
    }

    /**
     * Returns the address String from the Customer Object
     * @return
     */
    public String getAddress() 
    {
        return address;
    }

    /**
     * Returns the postalCode String from the Customer Object
     * @return
     */
    public String getPostalCode()
    {
        return postalCode;
    }

    /**
     * Returns the country Object from the Customer Object
     * @return
     */
    public Country getCountry()
    {
        return country;
    }

    /**
     * Returns the state (FLDivison) Object from the Customer Object
     * @return
     */
    public FLDivision getState()
    {
        return state;
    }

    /**
     * Returns the phone String from the Customer Object
     * @return
     */
    public String getPhone()
    {
        return phone;
    }

    /**
     * Returns only the first name from the customerName String on the Customer Object
     * @return
     */
    public String getFirstName()
    {
        int firstSpace = customerName.indexOf(" ");
        String firstName = customerName.substring(0, firstSpace);
        return firstName;
    }

    /**
     * Returns only the last name from the customerName String on the Customer Object
     * @return
     */
    public String getLastName()
    {
        int firstSpace = customerName.indexOf(" ");
        String lastName = customerName.substring(firstSpace).trim();
        return lastName;
    }

    /**
     * Returns the indexed Customer Objects name for proper ComboBox usage
     * @return
     */
    @Override
    public String toString()
    {
        return(customerName);
    }
    
}
