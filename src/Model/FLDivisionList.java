package Model;

import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author Zach Hendrix
 * A Class that has an ObservableList in which the FLDivision class object is saved to.
 * The class also contains add and get all functions
 */
public class FLDivisionList 
{
    private static ObservableList<FLDivision> allFLDivisions = FXCollections.observableArrayList();


    /**
     * Used to add an object into the "allFLDivisions" list
     * @param newFLDivision
     */
    public static void addFLDivision(FLDivision newFLDivision)
    {
        allFLDivisions.add(newFLDivision);
    }

    /**
     *Returns all of the FLDivision objects within the "allFLDivisions" Observable List
     * @return
     */
    public static ObservableList<FLDivision> getAllFLDivisions()
    {
        return allFLDivisions;
    }

    /**
     * Simple Lambda expression that returns FLDivisions that have a Country ID specified. Used to filter the FLDivisions by their
     * country ID so that the ComboBoxes on the CustomerDataController show only those associated with their country.
     * (Other Expressions in DBConnect and ScheduleMenuController)
     * @param division
     * @return
     */

    public static Predicate<FLDivision> divisionPredicate(Integer division)
    {
        return FLDivision -> FLDivision.getCountryID() == division;
    }

    /**
     *Converts the Integer given by the customer table in the database to a String of the divisions name for storing cached customers
     * @param divisionID
     * @return
     */
    public static FLDivision lookupDivision(int divisionID)
    {
        for(FLDivision flDivision : allFLDivisions)
        {
            if(flDivision.getDivisionID() == divisionID)
                return flDivision;
        }
        return null;
    }


    
}
