
package Model;

import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class FLDivisionList 
{
    private static ObservableList<FLDivision> allFLDivisions = FXCollections.observableArrayList();
    

    public static void addFLDivision(FLDivision newFLDivision)
    {
        allFLDivisions.add(newFLDivision);
    }

    public static ObservableList<FLDivision> getAllFLDivisions()
    {
        return allFLDivisions;
    }

    //Lambda
    public static Predicate<FLDivision> divisionID(Integer division)
    {
        return FLDivision -> FLDivision.getCountryID() == division;
    }

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
