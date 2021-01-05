/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *Sets up the class of FLDivision which is a generic term for what countries are split into.
 * A
 * @author Z
 */
public class FLDivision 
{
    private int divisionID;  
    private String divisionName;
    private int countryID;
    
    public FLDivision(int divisionID, String divisionName, int countryID)
    {
        this.divisionID = divisionID;
        this.divisionName = divisionName;
        this.countryID = countryID;
        
    }
    
    public int getDivisionID()
    {
        return divisionID;
    }
    
    public void setDivisionID(int divisionID)
    {
        this.divisionID = divisionID;
    }
    
    public String getDivisionName()
    {
        return divisionName;
    }
    
    public void setDivisionName(String divisionName)
    {
        this.divisionName = divisionName;
    }
    
    
    public int getCountryID()
    {
        return countryID;
    }
    
    public void setCountryID(int countryID)
    {
        this.countryID = countryID;
    }
    
   
    
    
    
    @Override
    public String toString()
    {
        return(divisionName);
    }
    
    
    
    
}
