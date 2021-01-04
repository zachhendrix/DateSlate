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

import java.time.LocalDateTime;
import java.util.function.Predicate;

public class Schedule 
{
    private static ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
    
    
    //Declares variables that are used in the setting of the Part or Product ID number when creating new parts or products
    private static int appointmentIDCount = 0;
    
    
    
    //Adds a new part or Product to their respective Observable List
    public static void addAppointment(Appointment newAppointment)
    {
        allAppointments.add(newAppointment);
    }

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

    //Methods used to set the Part or Product ID number when creating new parts or products
    public static int getAppointmentIDCount()
    {
        appointmentIDCount = allAppointments.size();
        return appointmentIDCount;
    }


    
}
