package Model;

/**
 * Sets up the Country class.
 * The Country Object is used in the Customer Object and in the FLDivision Object.
 * @author Zach Hendrix
 */
public class Country 
{
    private int countryID;  
    private String countryName;

    /**
     * A constructor used to create an Country object
     * @param countryID
     * @param countryName
     */
    public Country(int countryID, String countryName)
    {
        this.countryID = countryID;
        this.countryName = countryName;
    }

    /**
     * Returns the countryID Integer from the Country Object
     * @return
     */
    public int getCountryID()
    {
        return countryID;
    }

    /**
     * Returns the countryName String from the Country Object
     * @return
     */
    public String getCountryName()
    {
        return countryName;
    }

    /**
     * Returns the indexed Country Objects name for proper ComboBox usage
     * @return
     */
    @Override
    public String toString()
    {
        return(countryName);
    }
}
