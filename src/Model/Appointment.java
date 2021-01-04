/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.time.LocalDateTime;

/**
 *
 * @author Zach Hendrix
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

    /**
     *
     * @param appointmentID
     * @param appTitle
     * @param appDescription
     * @param appLocation
     * @param appContact
     * @param appType
     * @param startDate
     * @param endDate
     * @param appCustomer
     * @param appUser
     */
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


    /**
     *
     * @return
     */
    public int getAppointmentID() 
    {
        return appointmentID;
    }

    /**
     *
     * @param appointmentID
     */
    public void setAppointmentID(int appointmentID) 
    {
        this.appointmentID = appointmentID;
    }

    /**
     *
     * @return
     */
    public String getAppTitle() 
    {
        return appTitle;
    }

    /**
     *
     * @param appTitle
     */
    public void setAppTitle(String appTitle) 
    {
        this.appTitle = appTitle;
    }

    /**
     *
     * @return
     */
    public String getAppLocation() 
    {
        return appLocation;
    }

    /**
     *
     * @param appLocation
     */
    public void setAppLocation(String appLocation) 
    {
        this.appLocation = appLocation;
    }

    /**
     *
     * @return
     */
    public String getAppDescription() 
    {
        return appDescription;
    }

    /**
     *
     * @param appDescription
     */
    public void setAppDescription(String appDescription) 
    {
        this.appDescription = appDescription;
    }

    /**
     *
     * @return
     */
    public Contact getAppContact()
    {
        return appContact;
    }

    /**
     *
     * @param appContact
     */
    public void setAppContact(Contact appContact)
    {
        this.appContact = appContact;
    }

    /**
     *
     * @return
     */
    public String getAppType()
    {
        return appType;
    }

    /**
     *
     * @param appContact
     */
    public void setAppType(String appContact) 
    {
        this.appType = appType;
    }

    /**
     *
     * @return
     */
    public LocalDateTime getStartDate() 
    {
        return startDate;
    }

    /**
     *
     * @param startDate
     */
    public void setStartDate(LocalDateTime startDate) 
    {
        this.startDate = startDate;
    }

    /**
     *
     * @return
     */
    public LocalDateTime getEndDate() 
    {
        return endDate;
    }

    /**
     *
     * @param endDate
     */
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
