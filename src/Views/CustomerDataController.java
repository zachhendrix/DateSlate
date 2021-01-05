/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Model.*;

import static Model.FLDivisionList.divisionPredicate;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;

import Utils.DBConnection;
import Utils.DBQuery;
import javafx.collections.ObservableList;
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
    public static int generateIDNum;

    @FXML
    private TableView<Customer> customerTableView;
    @FXML
    private TableColumn<Customer, Integer> customerIDCol;
    @FXML
    private TableColumn<Customer, String> nameCol;
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
    private ComboBox<FLDivision> regionComboBox;
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
     * Initializes the CustomerDataController controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

        customerIDLabel.setText(String.valueOf(generateIDNum));
        customerTableView.setItems(Clientele.getAllCustomers());
        countryComboBox.setItems(Countries.getAllCountries());
        customerIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));   
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));   
        postalCol.setCellValueFactory(new PropertyValueFactory<>("postalCode")); 
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone")); 
        
  
       saveButton.setVisible(false);
    }

    /**
     * 
     * @param event
     */
    @FXML
    void countryComboSelected(ActionEvent event) 
    {
        if(countryComboBox.getValue() == null )
        {
            regionComboBox.getSelectionModel().clearSelection();
        }
        else
            {
                regionComboBox.setItems(FLDivisionList.getAllFLDivisions().filtered(divisionPredicate(countryComboBox.getValue().getCountryID())));
            }
    }

    @FXML
    private void addButtonClicked(ActionEvent event) throws SQLException
    {
       
        if(firstNameText.getText().length() >= 1 && lastNameText.getText().length() >= 1 && addressText.getText().length() >= 1 && Integer.parseInt(postalText.getText()) >= 1 && Integer.parseInt(phoneText.getText()) >= 1)
        {
            int customerID = generateIDNum++;
            String customerName = firstNameText.getText()+ " " + lastNameText.getText();
            String address = addressText.getText();
            String postalCode = postalText.getText();
            Country country = countryComboBox.getValue();
            FLDivision state = regionComboBox.getValue();
            String phone = phoneText.getText();
            LocalDateTime createDate = LocalDateTime.now();
            Timestamp createTS = Timestamp.valueOf(createDate);


            Connection conn = DBConnection.startConnection();


            String sql = "INSERT INTO customers (Customer_ID,Customer_Name,Address,Postal_Code,Phone,Create_Date,Created_By,Last_Update,Last_Updated_By,Division_ID) Values (?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, customerID);
            ps.setString(2, customerName);
            ps.setString(3, address);
            ps.setString(4,postalCode);
            ps.setString(5,phone);
            ps.setTimestamp(6, createTS);
            ps.setString(7, LoginMenuController.loggedIn);
            ps.setTimestamp(8,createTS);
            ps.setString(9, LoginMenuController.loggedIn);
            ps.setInt(10, state.getDivisionID());


            ps.execute();


            Clientele.addCustomer(new Customer(customerID, customerName,address,postalCode,country, state, phone));

            customerIDLabel.setText(String.valueOf(generateIDNum));
            firstNameText.clear();
            lastNameText.clear();
            addressText.clear();
            postalText.clear();
            countryComboBox.getSelectionModel().clearSelection();
            regionComboBox.getSelectionModel().clearSelection();
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
        countryComboBox.setValue(customerSelect.getCountry());
        regionComboBox.setValue(customerSelect.getState());
        phoneText.setText(String.valueOf((customerSelect).getPhone()));
        customerRef = customerSelect;
        
        
        addButton.setVisible(false);
        deleteButton.setVisible(false);
        saveButton.setVisible(true);
    }
    
    
     @FXML
    void saveButtonClicked(ActionEvent event) throws SQLException
     {
         //TODO: Problem with editing customer when associated appointment.
        Clientele.deleteCustomer(customerRef);
        
        int customerID = Integer.parseInt(customerIDLabel.getText());
        String customerName = firstNameText.getText()+ " " + lastNameText.getText();
        String address = addressText.getText();
        String postalCode = postalText.getText();
        Country country = countryComboBox.getValue();
        FLDivision state = regionComboBox.getValue();
        String phone = phoneText.getText();
        LocalDateTime createDate = LocalDateTime.now();
        Timestamp createTS = Timestamp.valueOf(createDate);


         Connection conn = DBConnection.startConnection();
         DBQuery.setStatement(conn);


        String sql = "INSERT INTO customers (Customer_ID,Customer_Name,Address,Postal_Code,Phone,Create_Date,Created_By,Last_Update,Last_Updated_By,Division_ID) Values (?,?,?,?,?,?,?,?,?,?)";

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, customerID);
        ps.setString(2, customerName);
        ps.setString(3, address);
        ps.setString(4,postalCode);
        ps.setString(5,phone);
        ps.setTimestamp(6, createTS);
        ps.setString(7, LoginMenuController.loggedIn);
        ps.setTimestamp(8,createTS);
        ps.setString(9, LoginMenuController.loggedIn);
        ps.setInt(10, state.getDivisionID());


        ps.execute();

        Clientele.addCustomer(new Customer(customerID, customerName,address,postalCode,country, state, phone));
        
        
        customerIDLabel.setText(String.valueOf(generateIDNum));
        firstNameText.clear();
        lastNameText.clear();
        addressText.clear();
        postalText.clear();
        countryComboBox.getSelectionModel().clearSelection();
        regionComboBox.getSelectionModel().clearSelection();
        phoneText.clear();
        
        addButton.setVisible(true);
        deleteButton.setVisible(true);
        saveButton.setVisible(false);
        
    }


    @FXML
    private void deleteButtonClicked(ActionEvent event) throws SQLException
    {
        Customer customerSelect = customerTableView.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Selected Part Will Be Deleted");
        alert.setContentText("Are you ok with this?");

        Optional<ButtonType> result = alert.showAndWait();
        
        if (result.get() == ButtonType.OK)
        {
            try {
                Connection conn = DBConnection.startConnection();
                DBQuery.setStatement(conn);
                Statement statement = DBQuery.getStatement();
                String insertStatement = "DELETE FROM customers WHERE Customer_ID = '" + customerSelect.getCustomerID() +"'";
                statement.execute(insertStatement);

                Customer customer = customerTableView.getSelectionModel().getSelectedItem();
                Clientele.deleteCustomer(customer);

                customerIDLabel.setText(String.valueOf(generateIDNum));

            }

            catch (SQLException se)
            {
                Alert alertSE = new Alert(Alert.AlertType.WARNING);
                alertSE.setTitle("ERROR");
                alertSE.setHeaderText("Cannot Delete Customer Without First Deleting Associated Appointments");
                alertSE.setContentText("Continue?");
                alertSE.show();
            }


        }

        
    }

    @FXML
    private void cancelButtonClicked(ActionEvent event) 
    {
        customerIDLabel.setText(String.valueOf(generateIDNum));
        firstNameText.clear();
        lastNameText.clear();
        addressText.clear();
        postalText.clear();
        countryComboBox.getSelectionModel().clearSelection();
        regionComboBox.getSelectionModel().clearSelection();
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
