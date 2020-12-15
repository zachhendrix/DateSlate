/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author zach.hendrix
 */
public class Appointment 
{
    private int appointmentID;  
    private String appTitle;
    private String appLocation;
    private String appDescription;
    private Customer appContact ;
    private String appType;
    private LocalDate startDate;
    private LocalDate endDate;
    private int customerID;

    public Appointment (int appointmentID, String appTitle, String appLocation, String appDescription, Customer appContact, String appType, LocalDate startDate, LocalDate endDate, int customerID) 
    {
        this.appointmentID = appointmentID;
        this.appTitle = appTitle ;   
        this.appLocation = appLocation;
        this.appDescription = appDescription;
        this.appType = appType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.customerID = customerID;
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

    public Customer getAppContact() 
    {
        return appContact;
    }

    public void setAppContact(Customer appContact) 
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
    
    public LocalDate getStartDate() 
    {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) 
    {
        this.startDate = startDate;
    }
    
    public LocalDate getEndDate() 
    {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) 
    {
        this.endDate = endDate;
    }

    
    
}
