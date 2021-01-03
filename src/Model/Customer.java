
package Model;

public class Customer 
{
    
    private int customerID;  
    private String customerName;
    private String address;
    private String postalCode;
    private Country country;
    private FLDivision state;
    private String phone;

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
    
    
    

    public int getCustomerID() 
    {
        return customerID;
    }

    public void setCustomerID(int customerID) 
    {
        this.customerID = customerID;
    }

    public String getCustomerName() 
    {
        return customerName;
    }

    public void setCustomerName(String customerName) 
    {
        this.customerName = customerName;
    }
    
    public String getAddress() 
    {
        return address;
    }

    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getPostalCode()
    {
        return postalCode;
    }

    public void setPostalCode(String postalCode)
    {
        this.postalCode = postalCode;
    }
    
    public Country getCountry()
    {
        return country;
    }

    public void setCountry(Country country)
    {
        this.country = country;
    }
    
    public FLDivision getState()
    {
        return state;
    }

    public void setState(FLDivision state)
    {
        this.state = state;
    }
    
    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    
    public String getFirstName()
    {
        int firstSpace = customerName.indexOf(" ");
        String firstName = customerName.substring(0, firstSpace);
        return firstName;
    }
    
    public String getLastName()
    {
        int firstSpace = customerName.indexOf(" ");
        String lastName = customerName.substring(firstSpace).trim();
        return lastName;
    }
   

    @Override
    public String toString()
    {
        return(customerName);
    }
    
}
