/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author Zach Hendrix
 * A Class that has an ObservableList in which the Country class object is saved to.
 * The class also contains add and get all functions
 */
public class Countries 
{
    private static ObservableList<Country> allCountries = FXCollections.observableArrayList();

    /**
     * Used to add an object into the "allCountries" list
     * @param newCountry
     */
    public static void addCountry(Country newCountry)
    {
        allCountries.add(newCountry);
    }


    /**
     * 
     * @return
     */
    public static ObservableList<Country> getAllCountries()
    {
        return allCountries;
    }


    public static Country getByID(int countryID)
    {
        for(Country c : allCountries)
        {
            if(c.getCountryID() == countryID)
            {
                return c;
            }
        }

        return null;
    }
}
