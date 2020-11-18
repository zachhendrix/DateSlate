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
 * @author zach.hendrix
 */
public class Clientele 
{
    
    private static ObservableList<Customers> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    
    
    //Declares variables that are used in the setting of the Part or Product ID number when creating new parts or products
    private static int partIDCount = 0;
    private static int productIDCount = 0;
    
    
    
    //Adds a new part or Product to their respective Observable List
    public static void addPart(Part newPart)
    {
        allParts.add(newPart);
    }
    
    public static void addProduct(Product newProduct)
    {
        allProducts.add(newProduct);
    }

    
    
    public static Part lookupPart(int partID)
    {
        for(Part part : Inventory.getAllParts())
        {
            if(part.getPartID() == partID)
                return part;
        }
        return null;
    }
    
    public static Product lookupProduct(int productID)
    {
        for(Product product : Inventory.getAllProducts())
        {
            if(product.getProductID() == productID)
                return product;
        }
        return null;
    }
    
    public static ObservableList<Part> lookupPart(String partName)
    {
       ObservableList<Part> foundParts = FXCollections.observableArrayList();
       
        //If the entered search term is of no length return all products.
        if(partName.length() == 0) 
        {
            foundParts = allParts;
        } 
        
        //Iterates through the list and detects whether an object contains the "partName" string and returns it.
        else 
        {
            for (int i = 0; i < allParts.size(); i++) 
            {
                if (allParts.get(i).getPartName().toLowerCase().contains(partName.toLowerCase())) 
                {
                    foundParts.add(allParts.get(i));
                }
            }    
        }
        
        return foundParts;

    }
    
    public static ObservableList<Product> lookupProduct(String productName)
    {
        ObservableList<Product> foundProducts = FXCollections.observableArrayList();
        
        //If the entered search term is of no length return all products.
        if(productName.length() == 0) 
        {
            foundProducts = allProducts;
        } 
        
        //Iterates through the list and detects whether an object contains the "productName" string and returns it.
        else 
        {
            for (int i = 0; i < allProducts.size(); i++) 
            {
                if (allProducts.get(i).getProductName().toLowerCase().contains(productName.toLowerCase())) {
                    foundProducts.add(allProducts.get(i));
                }
            }    
        }
        
        return foundProducts;
    }
    
 
    
    public static void updatePart(int index, Part selectedPart)
    {
        allParts.set(index, selectedPart);
    }
    
    public static void updateProduct(int index, Product newProduct)
    {
        allProducts.set(index, newProduct);
    }
    
    
    //Deletes a Part or Product to their respective Observable List
    public static boolean deletePart(Part selectedPart)
    {
        return getAllParts().remove(selectedPart);
    }
    
    public static boolean deleteProduct(Product selectedProduct)
    {
        return getAllProducts().remove(selectedProduct);
    }
    
    
    //Returns all Parts or Products from their respective lists
    public static ObservableList<Part> getAllParts()
    {
        return allParts;
    }
    
    public static ObservableList<Product> getAllProducts()
    {
        return allProducts;
    }
    
    
    //Methods used to set the Part or Product ID number when creating new parts or products
    public static int getPartIDCount()
    {
        partIDCount = allParts.size();
        return partIDCount;
    }
    
    public static int getProductIDCount()
    {
        productIDCount = allProducts.size();
        return productIDCount;
    }


    
}
