package Views;

import Model.*;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;

import Utils.DBConnection;
import Utils.DBQuery;
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
import javafx.scene.control.DateCell;
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
    private ComboBox<Contact> contactComboBox;

    @FXML
    private ComboBox<Customer> customerComboBox;

    @FXML
    private ComboBox<User> userComboBox;
        
    @FXML
    private Label appointmentIDLabel;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private TextField startDateHour;

    @FXML
    private TextField startDateMinute;

    @FXML
    private TextField endDateHour;

    @FXML
    private TextField endDateMinute;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        contactComboBox.setItems(ContactList.getAllContacts());
        customerComboBox.setItems(Clientele.getAllCustomers());
        userComboBox.setItems(UserList.getAllUsers());

    }
    
    
    @FXML
    void saveButtonClicked(ActionEvent event) throws IOException, SQLException
    {

        Schedule.deleteAppointment(appointmentRef);
        
        int appointmentID = Integer.parseInt(appointmentIDLabel.getText());  
        String appTitle = titleText.getText();  
        String appLocation = locationText.getText(); 
        String appDescription = descriptionText.getText();  
        Contact appContact = contactComboBox.getValue();
        String appType = typeText.getText();
        LocalDateTime startDate = startDatePicker.getValue().atTime(Integer.parseInt(startDateHour.getText()), Integer.parseInt(startDateMinute.getText()));
        LocalDateTime endDate = startDatePicker.getValue().atTime(Integer.parseInt(endDateHour.getText()), Integer.parseInt(endDateMinute.getText()));
        Customer appCustomer = customerComboBox.getValue();
        User appUser = userComboBox.getValue();
        LocalDateTime updateDate = LocalDateTime.now();
        
        
        if(appTitle.length() >= 1 && appLocation.length() >= 1 && appType.length() >= 1 && (startDate.getHour()>= 8 && startDate.getHour() <= 22) && endDate.isAfter(startDate))
        {
            Connection conn = DBConnection.startConnection();
            DBQuery.setStatement(conn);
            Statement statement = DBQuery.getStatement();
            String insertStatement = "INSERT INTO appointments (Appointment_ID,Title,Description,Location,Type,Start,End,Create_Date,Created_By,Last_Update,Last_Updated_By,Customer_ID,User_ID,Contact_ID)"+ "VALUES(" +
                    "'" + appointmentID + "'," +
                    "'" + appTitle + "'," +
                    "'" + appDescription + "'," +
                    "'" + appLocation + "'," +
                    "'" + appType + "'," +
                    "'" + startDate + "'," +
                    "'" + endDate + "'," +
                    "'" + null + "'," +
                    "'" + null + "'," +
                    "'" + updateDate + "'," +
                    "'" + LoginMenuController.loggedIn + "'," +
                    "'" + appCustomer.getCustomerID() + "'," +
                    "'" + appUser.getUserID() + "'," +
                    "'" + appContact.getContactID() +"'" +
                    ")";

            statement.execute(insertStatement);
            Schedule.addAppointment(new Appointment(appointmentID, appTitle,appLocation,appDescription,appContact,appType, startDate, endDate, appCustomer,appUser));

            //Loads the Main Menu screen.
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("ScheduleMenu.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
        
        else
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Data Is Invalid");
            alert.setHeaderText("Please Check Data is Accurate and Possible");
            alert.setContentText("Every text should be filled, Times should be scheduled between 8am and 10pm (0800 hours - 2200 hours) and end date and time should fall after start date and time");

            Optional<ButtonType> result = alert.showAndWait();
        
        }

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
        endDateHour.setText(String.valueOf((appointment).getEndDate().getHour()));
        endDateMinute.setText(String.valueOf((appointment).getEndDate().getMinute()));
        customerComboBox.setValue(appointment.getAppCustomer());
        userComboBox.setValue(appointment.getAppUser());
        
        
    }


}
