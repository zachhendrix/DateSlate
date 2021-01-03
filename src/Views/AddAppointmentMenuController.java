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
import java.util.ResourceBundle;
import java.util.Optional;

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

public class AddAppointmentMenuController implements Initializable
{
    Stage stage;
    Parent scene;
    String[] meetingType = {"Business","Personal"};
    public static int generateAppIDNum;

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


    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {

        appointmentIDLabel.setText(String.valueOf(generateAppIDNum));
        contactComboBox.setItems(ContactList.getAllContacts());
        customerComboBox.setItems(Clientele.getAllCustomers());
        typeChoiceBox.getItems().setAll(meetingType);



    }

    
    
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
        LocalDateTime endDate = startDatePicker.getValue().atTime(Integer.parseInt(endDateHour.getText()), Integer.parseInt(endDateMinute.getText()));
        Customer appCustomer = customerComboBox.getValue();
        User appUser = userComboBox.getValue();

        LocalDateTime createDate = LocalDateTime.now();

        
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
                    "'" + createDate + "'," +
                    "'" + LoginMenuController.loggedIn + "'," +
                    "'" + createDate + "'," +
                    "'" + LoginMenuController.loggedIn + "'," +
                    "'" + appCustomer.getCustomerID() + "'," +
                    "'" + appUser.getUserID() + "'," +
                    "'" + appContact.getContactID() +"'" +
                    ")";

            statement.execute(insertStatement);
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
