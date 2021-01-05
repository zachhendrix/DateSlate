package Model;

/**
 * Sets up the FLDivision class which is a generic term for what countries are split into.
 * The FLDivision Object is used in the Customer Object.
 * @author Zach Hendrix
 */
public class FLDivision 
{
    private int divisionID;  
    private String divisionName;
    private int countryID;

    /**
     * A constructor used to create an FLDivision object
     * @param divisionID
     * @param divisionName
     * @param countryID
     */
    public FLDivision(int divisionID, String divisionName, int countryID)
    {
        this.divisionID = divisionID;
        this.divisionName = divisionName;
        this.countryID = countryID;
        
    }

    /**
     * Returns the divisionID Integer from the FLDivision Object
     * @return
     */
    public int getDivisionID()
    {
        return divisionID;
    }

    /**
     * Returns the divisionName String from the FLDivision Object
     * @return
     */
    public String getDivisionName()
    {
        return divisionName;
    }

    /**
     * Returns the countryID Integer from the FLDivision Object
     * @return
     */
    public int getCountryID()
    {
        return countryID;
    }


    /**
     * Returns the indexed FLDivision Objects name for proper ComboBox usage
     * @return
     */
    @Override
    public String toString()
    {
        return(divisionName);
    }
    
    
    
    
}
