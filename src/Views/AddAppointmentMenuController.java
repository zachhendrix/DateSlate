package Views;

import Model.*;
import Utils.DBConnection;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 *
 * The AddAppointmentMenuController is a controller for the AddAppointmentMenu.fxml
 * @author Zach Hendrix
 *
 */
public class AddAppointmentMenuController implements Initializable
{
    Stage stage;
    Parent scene;
    String[] meetingType = {"Business","Personal"};
    public static int generateAppIDNum;
    private boolean isOverlapping;

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
     * On Initialize the appointmentIDLabel is set to the generatedAppIDNum
     * contact, customer, user, and type Comboboxes are loaded with data.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {

        appointmentIDLabel.setText(String.valueOf(generateAppIDNum));
        contactComboBox.setItems(ContactList.getAllContacts());
        customerComboBox.setItems(Clientele.getAllCustomers());
        userComboBox.setItems((UserList.getAllUsers()));
        typeChoiceBox.getItems().setAll(meetingType);



    }


    /**
     * When the Add button is clicked the values from the data fields are sent to variables. If the values of the variables
     * are acceptable the data is used to Insert into the database. The Appointment object is then added to the allAppointments
     * Observable List and the user is sent back to the ScheduleMenu
     * @param event
     * @throws IOException
     * @throws SQLException
     */
    @FXML
    void addButtonClicked(ActionEvent event) throws IOException, SQLException
    {
        int appointmentID = generateAppIDNum++;  
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

        LocalDateTime createDate = LocalDateTime.now();
        Timestamp createTS = Timestamp.valueOf(createDate);


        for (Appointment a : Schedule.getAllAppointments())
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

            String sql = "INSERT INTO appointments (Appointment_ID,Title,Description,Location,Type,Start,End,Create_Date,Created_By,Last_Update,Last_Updated_By,Customer_ID,User_ID,Contact_ID) Values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1,appointmentID);
            ps.setString(2, appTitle);
            ps.setString(3, appDescription);
            ps.setString(4,appLocation);
            ps.setString(5,appType);
            ps.setTimestamp(6,startTS);
            ps.setTimestamp(7,endTS);
            ps.setTimestamp(8,createTS);
            ps.setString(9, LoginMenuController.loggedIn);
            ps.setTimestamp(10,createTS);
            ps.setString(11, LoginMenuController.loggedIn);
            ps.setInt(12, appCustomer.getCustomerID());
            ps.setInt(13, appUser.getUserID());
            ps.setInt(14, appContact.getContactID());
            ps.execute();



            Schedule.addAppointment(new Appointment(appointmentID, appTitle,appLocation,appDescription,appContact,appType, startDate, endDate, appCustomer,appUser));



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
     * When the Cancel Button is clicked the user is given a warning alert. If the answer is "OK" the user is sent back
     * to the Schedule Menu
     * @param event
     * @throws IOException
     */
    @FXML
    void cancelButtonClicked(ActionEvent event) throws IOException 
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("You will be sent back to the Schedule Screen");
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
}
