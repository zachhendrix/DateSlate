
package Model;

public class Customer 
{
    
    private int customerID;  
    private String firstName;
    private String lastName;
    private String address;
    private int postalCode;
    private String country;
    private String state;
    private int phone;

    public Customer(int customerID, String firstName, String lastName, String address,int postalCode, String country, String state, int phone) 
    {
        this.customerID = customerID ;   
        this.firstName = firstName;
        this.lastName = lastName;
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

    public String getFirstName() 
    {
        return firstName;
    }

    public void setFirstName(String firstName) 
    {
        this.firstName = firstName;
    }
    
    public String getLastName() 
    {
        return lastName;
    }

    public void setLastName(String lastName) 
    {
        this.lastName = lastName;
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
    
    public int getPhone() 
    {
        return phone;
    }

    public void setPhone(int phone) 
    {
        this.phone = phone;
    }

    
    
}
