package Views;

import Model.*;
import Utils.DBConnection;
import Utils.DBQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 *
 * The UpdateAppointmentMenuController is a controller for the UpdateAppointmentMenu.fxml
 * @author Zach Hendrix
 *
 */
public class UpdateAppointmentMenuController implements Initializable
{
    Stage stage;
    Parent scene;
    String[] meetingType = {"Business","Personal"};
    private Appointment appointmentRef;
    private boolean isOverlapping;


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
    private ChoiceBox<String> typeChoiceBox;

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

    /**
     * On Initialize the contact, customer, user and type ComboBoxes are all set with their
     * respective data types.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        contactComboBox.setItems(ContactList.getAllContacts());
        customerComboBox.setItems(Clientele.getAllCustomers());
        userComboBox.setItems(UserList.getAllUsers());
        typeChoiceBox.getItems().setAll(meetingType);

    }

    /**
     * When the Save button is clicked the data in the fields are set to variables. If the variables are acceptable the original
     * Appointment is deleted and then the data is used to Insert a new Appointment using the values changed in the same appointmentID
     * location. The Appointment object in the local allAppointments ObservableList is the deleted, added with new values and
     * the user is sent back to the ScheduleMenu
     * @param event
     * @throws IOException
     * @throws SQLException
     */
    @FXML
    void saveButtonClicked(ActionEvent event) throws IOException, SQLException
    {
        int appointmentID = Integer.parseInt(appointmentIDLabel.getText());  
        String appTitle = titleText.getText();  
        String appLocation = locationText.getText(); 
        String appDescription = descriptionText.getText();  
        Contact appContact = contactComboBox.getValue();
        String appType = typeChoiceBox.getValue();
        LocalDateTime startDate = startDatePicker.getValue().atTime(Integer.parseInt(startDateHour.getText()), Integer.parseInt(startDateMinute.getText()));
        Timestamp startTS = Timestamp.valueOf(startDate);
        LocalDateTime endDate = startDatePicker.getValue().atTime(Integer.parseInt(endDateHour.getText()), Integer.parseInt(endDateMinute.getText()));
        Timestamp endTS = Timestamp.valueOf(endDate);
        Customer appCustomer = customerComboBox.getValue();
        User appUser = userComboBox.getValue();
        LocalDateTime updateDate = LocalDateTime.now();
        Timestamp updateTS = Timestamp.valueOf(updateDate);

        for (Appointment a: Schedule.getAllAppointments())
        {

            if ((startDate.isAfter(a.getStartDate()) && startDate.isBefore(a.getEndDate())) ||
                    (endDate.isAfter(a.getStartDate()) && endDate.isBefore(a.getEndDate())) ||
                    (startDate.isAfter(a.getStartDate()) && endDate.isBefore(a.getEndDate())) ||
                    (startDate.isBefore(a.getStartDate()) && endDate.isAfter(a.getEndDate())) ||
                    (startDate.equals(a.getStartDate()) && endDate.equals(a.getEndDate())))
            {
                isOverlapping = true;

            }

            else if (startDate.equals(a.getStartDate())||
                    startDate.equals(a.getEndDate()) ||
                    endDate.equals(a.getStartDate()) ||
                    endDate.equals(a.getEndDate()))
            {
                isOverlapping = true;

            }
            else
            {
                isOverlapping = false;
            }
        }
        
        if(appTitle.length() >= 1 && appLocation.length() >= 1 && appType.length() >= 1 && (startDate.getHour()>= 8 && startDate.getHour() <= 22) && endDate.isAfter(startDate) && isOverlapping == false)
        {
            Connection conn = DBConnection.startConnection();
            DBQuery.setStatement(conn);
            Statement statement = DBQuery.getStatement();
            String deleteStatement = "DELETE FROM appointments WHERE Appointment_ID = '" + appointmentID +"'";
            statement.execute(deleteStatement);

            String sql = "INSERT INTO appointments (Appointment_ID,Title,Description,Location,Type,Start,End,Last_Update,Last_Updated_By,Customer_ID,User_ID,Contact_ID) Values (?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1,appointmentID);
            ps.setString(2, appTitle);
            ps.setString(3, appDescription);
            ps.setString(4,appLocation);
            ps.setString(5,appType);
            ps.setTimestamp(6,startTS);
            ps.setTimestamp(7,endTS);
            ps.setTimestamp(8,updateTS);
            ps.setString(9, LoginMenuController.loggedIn);
            ps.setInt(10, appCustomer.getCustomerID());
            ps.setInt(11, appUser.getUserID());
            ps.setInt(12, appContact.getContactID());
            ps.execute();

            Schedule.deleteAppointment(appointmentRef);
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

    /**
     * When the Cancel button is clicked a warning alert will be shown, if the answer is "OK" user is sent
     * back to the ScheduleMenu
     * @param event
     * @throws IOException
     */
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




    /**
     * The data is sent from the previous Appointment Table to populate the data fields in the current screen.
     * @param appointment
     */
    void sendModData(Appointment appointment) 
    {
        appointmentRef = appointment;
        appointmentIDLabel.setText(String.valueOf((appointment).getAppointmentID()));
        titleText.setText(String.valueOf(appointment.getAppTitle()));
        locationText.setText(String.valueOf((appointment).getAppLocation()));
        descriptionText.setText(String.valueOf((appointment).getAppDescription()));
        typeChoiceBox.setValue(appointment.getAppType());
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
