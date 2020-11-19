/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Model.Clientele;
import Model.Customer;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
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
    private ComboBox<?> countryComboBox;
    @FXML
    private ComboBox<?> regionComboBox;
    @FXML
    private Button addButton;
    @FXML
    private Button modifyButton;
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
        customerIDLabel.setText(String.valueOf(Clientele.getCustomerIDCount() + 1));
        customerTableView.setItems(Clientele.getAllCustomers());
        customerIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));  
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName")); 
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));   
        postalCol.setCellValueFactory(new PropertyValueFactory<>("postalCode")); 
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone")); 
    }    

    @FXML
    private void addButtonClicked(ActionEvent event) 
    {
        int customerID = Clientele.getCustomerIDCount() + 1 ;
        customerIDLabel.setText(String.valueOf(customerID + 1));
        
        String firstName = firstNameText.getText();
        String lastName = lastNameText.getText();
        String address = addressText.getText();
        int postalCode = Integer.parseInt(postalText.getText());
        String country = countryComboBox.getSelectionModel().toString();
        String state = regionComboBox.getSelectionModel().toString();
        int phone = Integer.parseInt(phoneText.getText());
        
        Clientele.addCustomer(new Customer(customerID, firstName,lastName,address,postalCode,country, state, phone));
        
    }
    
    @FXML
    void modifyButtonClicked(ActionEvent event) 
    {
        Customer customerRef = customerTableView.getSelectionModel().getSelectedItem();
        customerIDLabel.setText(String.valueOf(customerRef.getCustomerID()));
        firstNameText.setText(String.valueOf((customerRef).getFirstName()));
        lastNameText.setText(String.valueOf((customerRef).getLastName()));
        addressText.setText(String.valueOf((customerRef).getAddress()));
        postalText.setText(String.valueOf((customerRef).getPostalCode()));

        phoneText.setText(String.valueOf((customerRef).getPhone()));
    }


    @FXML
    private void deleteButtonClicked(ActionEvent event) 
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Selected Part Will Be Deleted");
        alert.setContentText("Are you ok with this?");

        Optional<ButtonType> result = alert.showAndWait();
        
        if (result.get() == ButtonType.OK)
        {
            Customer customer = customerTableView.getSelectionModel().getSelectedItem();
            Clientele.deleteCustomer(customer);
        } 
        
    }

    @FXML
    private void cancelButtonClicked(ActionEvent event) 
    {
        
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
