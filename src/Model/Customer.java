
package Model;

public class Customer 
{
    
    private int customerID;  
    private String customerName;
    private String address;
    private int postalCode;
    private String country;
    private String state;
    private long phone;

    public Customer(int customerID, String customerName, String address,int postalCode, String country, String state, long phone) 
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

    public int getPostalCode() 
    {
        return postalCode;
    }

    public void setPostalCode(int postalCode) 
    {
        this.postalCode = postalCode;
    }
    
    public String getCountry() 
    {
        return country;
    }

    public void setCountry(String country) 
    {
        this.country = country;
    }
    
    public String getState() 
    {
        return state;
    }

    public void setState(String state) 
    {
        this.state = state;
    }
    
    public long getPhone() 
    {
        return phone;
    }

    public void setPhone(long phone) 
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
