/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Z
 */
public class Countries 
{
    private static ObservableList<Country> allCountries = FXCollections.observableArrayList();
    

    public static void addCountry(Country newCountry)
    {
        allCountries.add(newCountry);
    }


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
