package Model;

import java.time.LocalDateTime;
import java.util.function.Predicate;

/**
 * Sets up the Appointment class.
 * The Appointment Object is used to show Appointment Data to the User.
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
     * A constructor used to create an Appointment object
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
     * Returns the appointmentID Integer from the Appointment Object
     * @return
     */
    public int getAppointmentID() 
    {
        return appointmentID;
    }

    /**
     * Returns the appTitle String from the Appointment Object
     * @return
     */
    public String getAppTitle() 
    {
        return appTitle;
    }

    /**
     * Returns the appLocation String from the Appointment Object
     * @return
     */
    public String getAppLocation() 
    {
        return appLocation;
    }

    /**
     *Returns the appDescription String from the Appointment Object
     * @return
     */
    public String getAppDescription() 
    {
        return appDescription;
    }

    /**
     * Returns the appContact Object from the Appointment Object
     * @return
     */
    public Contact getAppContact()
    {
        return appContact;
    }

    /**
     *Returns the appType String from the Appointment Object
     * @return
     */
    public String getAppType()
    {
        return appType;
    }

    /**
     * Returns the startDate LocalDateTime from the Appointment Object
     * @return
     */
    public LocalDateTime getStartDate() 
    {
        return startDate;
    }

    /**
     * Returns the endDate LocalDateTime from the Appointment Object
     * @return
     */
    public LocalDateTime getEndDate() 
    {
        return endDate;
    }

    /**
     * Returns the appCustomer Object from the Appointment Object
     * @return
     */
    public Customer getAppCustomer() 
    {
        return appCustomer;
    }

    /**
     * Returns the appUser Object from the Appointment Object
     * @return
     */
    public User getAppUser()
    {
        return appUser;
    }


    /**
     * Lambda expression used to get all of the appointments from a given month by its Integer value.
     * Used to sort Appointments by current month.
     * @param monthValue
     * @return
     */
    public static Predicate<Appointment> appointmentDateIntPredicate(Integer monthValue)
    {

        return Appointment -> Appointment.getStartDate().getMonthValue() == monthValue;
    }
}
