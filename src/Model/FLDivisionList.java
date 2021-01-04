
package Model;

import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author Zach Hendrix
 *
 */
public class FLDivisionList 
{
    private static ObservableList<FLDivision> allFLDivisions = FXCollections.observableArrayList();


    /**
     *
     * @param newFLDivision
     */
    public static void addFLDivision(FLDivision newFLDivision)
    {
        allFLDivisions.add(newFLDivision);
    }

    /**
     *
     * @return
     */
    public static ObservableList<FLDivision> getAllFLDivisions()
    {
        return allFLDivisions;
    }

    /**
     *
     * @param division
     * @return
     */
    //Section B
    //Simple Lambda expression to show that the Java Application Thread is running (Other Expression is in DBConnection
    public static Predicate<FLDivision> divisionID(Integer division)
    {
        return FLDivision -> FLDivision.getCountryID() == division;
    }

    /**
     *
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
