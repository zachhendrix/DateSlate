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
    

    //Adds a new part or Product to their respective Observable List
    public static void addCountry(Country newCountry)
    {
        allCountries.add(newCountry);
    }

    public static void updateCountry(int index, Country selectedCountry)
    {
        allCountries.set(index, selectedCountry);
    }
    
    //Deletes a Part or Product to their respective Observable List
    public static boolean deleteCountry (Country selectedCountry)
    {
        return getAllCountries().remove(selectedCountry);
    }


    //Methods used to set the Part or Product ID number when creating new parts or products
    public static ObservableList<Country> getAllCountries()
    {
        return allCountries;
    }
    
    
    
}
