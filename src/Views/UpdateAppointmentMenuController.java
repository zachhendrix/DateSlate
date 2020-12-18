package Views;

import Model.Appointment;
import Model.Clientele;
import Model.Customer;
import Model.Schedule;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpdateAppointmentMenuController implements Initializable
{
    Stage stage;
    Parent scene;
    private Appointment appointmentRef;
    
    @FXML
    private Button saveButton;

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
    private ComboBox<Customer> customerComboBox;
        
    @FXML
    private Label appointmentIDLabel;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private TextField startDateHour;

    @FXML
    private TextField startDateMinute;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private TextField endDateHour;

    @FXML
    private TextField endDateMinute;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        contactComboBox.setItems(Clientele.getAllCustomers());
        customerComboBox.setItems(Clientele.getAllCustomers());
        
    }
    
    @FXML
    void saveButtonClicked(ActionEvent event) throws IOException 
    {

        Schedule.deleteAppointment(appointmentRef);
        
        int appointmentID = Integer.parseInt(appointmentIDLabel.getText());  
        String appTitle = titleText.getText();  
        String appLocation = locationText.getText(); 
        String appDescription = descriptionText.getText();  
        Customer appContact = contactComboBox.getValue();  
        String appType = typeText.getText();
        LocalDateTime startDate = startDatePicker.getValue().atTime(Integer.parseInt(startDateHour.getText()), Integer.parseInt(startDateMinute.getText()));
        LocalDateTime endDate = endDatePicker.getValue().atTime(Integer.parseInt(endDateHour.getText()), Integer.parseInt(endDateMinute.getText()));
        Customer appCustomer = customerComboBox.getValue();
        
        Schedule.addAppointment(new Appointment(appointmentID, appTitle,appLocation,appDescription,appContact,appType, startDate, endDate, appCustomer ));

        

        
        //Loads the Main Menu screen.
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("ScheduleMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void cancelButtonClicked(ActionEvent event) throws IOException 
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Changes will be reset & User will be sent back to the Schedule Menu");
        alert.setContentText("Are you ok with this?");

        Optional<ButtonType> result = alert.showAndWait();
        
        if (result.get() == ButtonType.OK)
        {
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("ScheduleMenu.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        } 
        

    }

    void sendModData(Appointment appointment) 
    {
        appointmentRef = appointment;
        appointmentIDLabel.setText(String.valueOf((appointment).getAppointmentID()));
        titleText.setText(String.valueOf(appointment.getAppTitle()));
        locationText.setText(String.valueOf((appointment).getAppLocation()));
        descriptionText.setText(String.valueOf((appointment).getAppDescription()));
        typeText.setText(String.valueOf((appointment).getAppType()));
        contactComboBox.setValue(appointment.getAppContact());
        startDatePicker.setValue((appointment).getStartDate().toLocalDate());
        startDateHour.setText(String.valueOf((appointment).getStartDate().getHour()));
        startDateMinute.setText(String.valueOf((appointment).getStartDate().getMinute()));
        endDatePicker.setValue((appointment).getEndDate().toLocalDate());
        endDateHour.setText(String.valueOf((appointment).getEndDate().getHour()));
        endDateMinute.setText(String.valueOf((appointment).getEndDate().getMinute()));
        customerComboBox.setValue(appointment.getAppCustomer());
        
        
    }


}
