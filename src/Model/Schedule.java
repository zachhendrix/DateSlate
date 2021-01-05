/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/*
 *
 * @author zach.hendrix
 */

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
     *
     * @param index
     * @param selectedAppointment
     */
    public static void updateAppointment(int index, Appointment selectedAppointment)
    {
        allAppointments.set(index, selectedAppointment);
    }
    
    //Deletes a Part or Product to their respective Observable List
    public static boolean deleteAppointment(Appointment selectedAppointment)
    {
        return getAllAppointments().remove(selectedAppointment);
    }

    //Returns all Parts or Products from their respective lists
    public static ObservableList<Appointment> getAllAppointments()
    {
        return allAppointments;
    }



    
}
