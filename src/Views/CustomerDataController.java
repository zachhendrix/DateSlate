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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    private TableView<?> customerTableView;
    @FXML
    private TableColumn<?, ?> customerIDCol;
    @FXML
    private TableColumn<?, ?> firstNameCol;
    @FXML
    private TableColumn<?, ?> lastNameCol;
    @FXML
    private TableColumn<?, ?> addressCol;
    @FXML
    private TableColumn<?, ?> postalCol;
    @FXML
    private TableColumn<?, ?> phoneCol;
    @FXML
    private TextField customerIDText;
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
        // TODO
    }    

    @FXML
    private void addButtonClicked(ActionEvent event) 
    {
        int customerID = Integer.parseInt(customerIDText.getText());
        String firstName = firstNameText.getText();
        String lastName = lastNameText.getText();
        String address = addressText.getText();
        int postalCode = Integer.parseInt(postalText.getText());
        String country = countryComboBox.getText();
        String state = regionComboBox.getText();
        int phone = Integer.parseInt(phoneText.getText());
        Clientele.addCustomer(new Customer(customerID, firstName,lastName,address,postalCode,country, state, phone));
        
    }

    @FXML
    private void deleteButtonClicked(ActionEvent event) 
    {
        
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
