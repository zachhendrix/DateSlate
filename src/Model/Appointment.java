/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.time.LocalDateTime;

/**
 *
 * @author zach.hendrix
 */
public class Appointment 
{
    private int appointmentID;  
    private String appTitle;
    private String appDescription;
    private String appLocation;
    private Contact appContact ;
    private String appType;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Customer appCustomer;
    private User appUser;

    public Appointment (int appointmentID, String appTitle, String appDescription, String appLocation, Contact appContact, String appType, LocalDateTime startDate, LocalDateTime endDate, Customer appCustomer, User appUser)
    {
        this.appointmentID = appointmentID;
        this.appTitle = appTitle ;   
        this.appLocation = appLocation;
        this.appDescription = appDescription;
        this.appContact = appContact;
        this.appType = appType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.appCustomer = appCustomer;
        this.appUser = appUser;
    }
    
    
    

    public int getAppointmentID() 
    {
        return appointmentID;
    }

    public void setAppointmentID(int appointmentID) 
    {
        this.appointmentID = appointmentID;
    }

    public String getAppTitle() 
    {
        return appTitle;
    }

    public void setAppTitle(String appTitle) 
    {
        this.appTitle = appTitle;
    }
    
    public String getAppLocation() 
    {
        return appLocation;
    }

    public void setAppLocation(String appLocation) 
    {
        this.appLocation = appLocation;
    }
    
    public String getAppDescription() 
    {
        return appDescription;
    }

    public void setAppDescription(String appDescription) 
    {
        this.appDescription = appDescription;
    }

    public Contact getAppContact()
    {
        return appContact;
    }

    public void setAppContact(Contact appContact)
    {
        this.appContact = appContact;
    }
    
    public String getAppType() 
    {
        return appType;
    }

    public void setAppType(String appContact) 
    {
        this.appType = appType;
    }
    
    public LocalDateTime getStartDate() 
    {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) 
    {
        this.startDate = startDate;
    }
    
    public LocalDateTime getEndDate() 
    {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) 
    {
        this.endDate = endDate;
    }
    
    public Customer getAppCustomer() 
    {
        return appCustomer;
    }

    public void setAppCustomer(Customer appCustomer) 
    {
        this.appCustomer = appCustomer;
    }

    public User getAppUser()
    {
        return appUser;
    }

    public void setAppUser(User appUser)
    {
        this.appUser = appUser;
    }




}
