package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * @author Zach Hendrix
 * A Class that has an ObservableList in which the Appointment class object is saved to.
 * The class also contains add and get all functions
 */
public class Schedule 
{
    private static ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();


    /**
     * Used to add an object into the "allAppointments" list
     * @param newAppointment
     */
    public static void addAppointment(Appointment newAppointment)
    {
        allAppointments.add(newAppointment);
    }

    /**
     * Used to delete an object from the "allAppointments" list at selected value in table
     * @param selectedAppointment
     * @return
     */
    public static boolean deleteAppointment(Appointment selectedAppointment)
    {
        return getAllAppointments().remove(selectedAppointment);
    }

    /**
     * Returns all customers in the "allAppointments" ObservableList.
     * @return
     */
    public static ObservableList<Appointment> getAllAppointments()
    {
        return allAppointments;
    }

}
