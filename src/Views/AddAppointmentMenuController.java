package Views;

import Model.Appointment;
import Model.Clientele;
import Model.Customer;
import Model.Schedule;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.Date;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AddAppointmentMenuController 
{
    Stage stage;
    Parent scene;
    private static int generateAppIDNum;

    @FXML
    private Button addButton;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField titleText;

    @FXML
    private TextField descriptionText;

    @FXML
    private TextField locationText;

    @FXML
    private TextField typeText;

    @FXML
    private ComboBox<Customer> contactComboBox;

    @FXML
    private Label appointmentIDLabel;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private ComboBox<Customer> customerComboBox;


    
    public void initialize(URL url, ResourceBundle rb) 
    {
        generateAppIDNum = 500;
        appointmentIDLabel.setText(String.valueOf(generateAppIDNum));
        customerComboBox.setItems(Clientele.getAllCustomers());
        
               
    }    
     
    @FXML
    void addButtonClicked(ActionEvent event) throws IOException 
    {
        
        int appointmentID = generateAppIDNum++;  
        String appTitle = titleText.getText();  
        String appLocation = locationText.getText(); 
        String appDescription = descriptionText.getText();  
        Customer appContact = contactComboBox.getValue();  
        String appType = typeText.getText();
        LocalDate startDate = startDatePicker.getValue();
        LocalDate endDate = endDatePicker.getValue();
        int customerID = customerComboBox.getValue().getCustomerID();
        
        Schedule.addAppointment(new Appointment(appointmentID, appTitle,appLocation,appDescription,appContact,appType, startDate, endDate, customerID ));
 
        
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("ScheduleMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void cancelButtonClicked(ActionEvent event) throws IOException 
    {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("ScheduleMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void startDatePicked(ActionEvent event) 
    {

    }
    @FXML
    void endDatePicked(ActionEvent event) 
    {

    }


}
