/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Model.Clientele;
import Model.Countries;
import Model.Country;
import Model.Customer;
import Utils.DBConnection;
import Utils.DBQuery;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Z
 */
public class CustomerDataController implements Initializable 
{
    Stage stage;
    Parent scene;
    private Customer customerRef;
    private static int generateIDNum;

    @FXML
    private TableView<Customer> customerTableView;
    @FXML
    private TableColumn<Customer, Integer> customerIDCol;
    @FXML
    private TableColumn<Customer, String> firstNameCol;
    @FXML
    private TableColumn<Customer, String> lastNameCol;
    @FXML
    private TableColumn<Customer, String> addressCol;
    @FXML
    private TableColumn<Customer, Integer> postalCol;
    @FXML
    private TableColumn<Customer, Integer> phoneCol;
    @FXML
    private Label customerIDLabel;
    @FXML
    private TextField firstNameText;
    @FXML
    private TextField lastNameText;
    @FXML
    private TextField addressText;
    @FXML
    private TextField postalText;
    @FXML
    private TextField phoneText;
    @FXML
    private Label regionLabel;
    @FXML
    private ComboBox<Country> countryComboBox;
    @FXML
    private ComboBox<?> regionComboBox;
    @FXML
    private Button addButton;
    @FXML
    private Button modifyButton;
    @FXML
    private Button saveButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Button backButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        
        customerIDLabel.setText(String.valueOf(generateIDNum));
        customerTableView.setItems(Clientele.getAllCustomers());
        countryComboBox.setItems(Countries.getAllCountries() );
        customerIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));  
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName")); 
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));   
        postalCol.setCellValueFactory(new PropertyValueFactory<>("postalCode")); 
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone")); 
        
  
       saveButton.setVisible(false);
    }    

    @FXML
    private void addButtonClicked(ActionEvent event) 
    {
       
        if(firstNameText.getText().length() >= 1 && lastNameText.getText().length() >= 1 && addressText.getText().length() >= 1 && Integer.parseInt(postalText.getText()) >= 1 && Integer.parseInt(phoneText.getText()) >= 1)
        {
            int customerID = generateIDNum++;
            String customerName = firstNameText.getText()+ " " + lastNameText.getText();
            String address = addressText.getText();
            int postalCode = Integer.parseInt(postalText.getText());
            String country = countryComboBox.getSelectionModel().toString();
            String state = regionComboBox.getSelectionModel().toString();
            long phone = Integer.parseInt(phoneText.getText());
        
            Clientele.addCustomer(new Customer(customerID, customerName,address,postalCode,country, state, phone));
        
            customerIDLabel.setText(String.valueOf(generateIDNum));
            firstNameText.clear();
            lastNameText.clear();
            addressText.clear();
            postalText.clear();
            phoneText.clear();
        }
        
        else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information Left Blank");
            alert.setHeaderText("Please Make Sure All Fields are Filled Out");
            alert.setContentText("Try Again");
            alert.showAndWait();
        }
    }
    
    @FXML
    void modifyButtonClicked(ActionEvent event) 
    {
        Customer customerSelect = customerTableView.getSelectionModel().getSelectedItem();
        customerIDLabel.setText(String.valueOf(customerSelect.getCustomerID()));
        firstNameText.setText(String.valueOf((customerSelect).getFirstName()));
        lastNameText.setText(String.valueOf((customerSelect).getLastName()));
        addressText.setText(String.valueOf((customerSelect).getAddress()));
        postalText.setText(String.valueOf((customerSelect).getPostalCode()));

        phoneText.setText(String.valueOf((customerSelect).getPhone()));
        customerRef = customerSelect;
        
        
        addButton.setVisible(false);
        deleteButton.setVisible(false);
        saveButton.setVisible(true);
    }
    
    
     @FXML
    void saveButtonClicked(ActionEvent event) 
    {
        Clientele.deleteCustomer(customerRef);
        
        int customerID = Integer.parseInt(customerIDLabel.getText());
        String customerName = firstNameText.getText()+ " " + lastNameText.getText();
        String address = addressText.getText();
        int postalCode = Integer.parseInt(postalText.getText());
        String country = countryComboBox.getSelectionModel().toString();
        String state = regionComboBox.getSelectionModel().toString();
        long phone = Integer.parseInt(phoneText.getText());
        
        Clientele.addCustomer(new Customer(customerID, customerName,address,postalCode,country, state, phone));
        
        
        customerIDLabel.setText(String.valueOf(generateIDNum));
        firstNameText.clear();
        lastNameText.clear();
        addressText.clear();
        postalText.clear();
        phoneText.clear();
        
        addButton.setVisible(true);
        deleteButton.setVisible(true);
        saveButton.setVisible(false);
        
    }


    @FXML
    private void deleteButtonClicked(ActionEvent event) 
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("onfirmation Dialog");
        alert.setHeaderText("Selected Part Will Be Deleted");
        alert.setContentText("Are you ok with this?");

        Optional<ButtonType> result = alert.showAndWait();
        
        if (result.get() == ButtonType.OK)
        {
            Customer customer = customerTableView.getSelectionModel().getSelectedItem();
            Clientele.deleteCustomer(customer);
            
            customerIDLabel.setText(String.valueOf(generateIDNum));
        } 
        
    }

    @FXML
    private void cancelButtonClicked(ActionEvent event) 
    {
        firstNameText.clear();
        lastNameText.clear();
        addressText.clear();
        postalText.clear();
        phoneText.clear();
    }

    @FXML
    private void backButtonClicked(ActionEvent event) throws IOException 
    {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("ScheduleMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    
}
