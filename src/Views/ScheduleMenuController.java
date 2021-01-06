package Views;

import Model.Appointment;
import Model.Customer;
import Model.FLDivision;
import Model.Schedule;
import Utils.DBConnection;
import Utils.DBQuery;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Predicate;

import static Model.Appointment.divisionPredicate;

/**
 *
 * The ScheduleMenuController is a controller for the ScheduleMenu.fxml
 * @author Zach Hendrix
 *
 */
public class ScheduleMenuController implements Initializable 
{

    Stage stage;
    Parent scene;
    private static Integer numberUpcoming = 0;
    private static boolean isChecked;

    
    @FXML
    private Tab overviewTab;
    @FXML
    private TableView<Appointment> appointmentTableview;
    @FXML
    private TableColumn<Appointment, Integer> appointmentIDCol;
    @FXML
    private TableColumn<Appointment, String> titleCol;
    @FXML
    private TableColumn<Appointment, String> descriptionCol;
    @FXML
    private TableColumn<Appointment, String> locationCol;
    @FXML
    private TableColumn<Appointment, Customer> contactCol;
    @FXML
    private TableColumn<Appointment, String> typeCol;
    @FXML
    private TableColumn<Appointment, Date> startDateCol;
    @FXML
    private TableColumn<Appointment, Date> endDateCol;
    @FXML
    private TableColumn<Appointment, Customer> customerIDCol;
    
    @FXML
    private Tab monthTab;
    @FXML
    private TableView<Appointment> appointmentMonthTableview;
    @FXML
    private TableColumn<Appointment, Integer> monthAppointmentIDCol;
    @FXML
    private TableColumn<Appointment, String> monthTitleCol;
    @FXML
    private TableColumn<Appointment, String> monthDescriptionCol;
    @FXML
    private TableColumn<Appointment, String> monthLocationCol;
    @FXML
    private TableColumn<Appointment, Customer> monthContactCol;
    @FXML
    private TableColumn<Appointment, String> monthTypeCol;
    @FXML
    private TableColumn<Appointment, Date> monthStartDateCol;
    @FXML
    private TableColumn<Appointment, Date> monthEndDateCol;
    @FXML
    private TableColumn<Appointment, Customer> monthCustomerIDCol;
   
    @FXML
    private Tab weekTab;
    @FXML
    private TableView<Appointment> appointmentWeekTableview;
    @FXML
    private TableColumn<Appointment, Integer> weekAppointmentIDCol;
    @FXML
    private TableColumn<Appointment, String> weekTitleCol;
    @FXML
    private TableColumn<Appointment, String> weekDescriptionCol;
    @FXML
    private TableColumn<Appointment, String> weekLocationCol;
    @FXML
    private TableColumn<Appointment, Customer> weekContactCol;
    @FXML
    private TableColumn<Appointment, String> weekTypeCol;
    @FXML
    private TableColumn<Appointment, Date> weekStartDateCol;
    @FXML
    private TableColumn<Appointment, Date> weekEndDateCol;
    @FXML
    private TableColumn<Appointment, Customer> weekCustomerIDCol;

    @FXML
    private Button customerButton;
    
    @FXML
    private Button addButton;
    
    @FXML
    private Button deleteButton;
    
    @FXML
    private Button updateButton;

    @FXML
    private Button reportsButton;

    @FXML
    private Label currentTimeLabel;

    @FXML
    private Label currentDateLabel;

    /**
     * Initializes the controller class, checks to see if there are any upcoming appointments one time and initializes the clock display
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        appointmentTableview.setItems(Schedule.getAllAppointments());
        
        appointmentIDCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("appTitle"));  
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("appLocation")); 
        locationCol.setCellValueFactory(new PropertyValueFactory<>("appDescription"));   
        contactCol.setCellValueFactory(new PropertyValueFactory<>("appContact")); 
        typeCol.setCellValueFactory(new PropertyValueFactory<>("appType")); 
        startDateCol.setCellValueFactory(new PropertyValueFactory<>("startDate")); 
        endDateCol.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        customerIDCol.setCellValueFactory(new PropertyValueFactory<>("appCustomer"));

        if(!isChecked)
        {

            appointmentNumber();

            isChecked = true;
        }

        dateAndTimeDisplay();


   }

    /**
     * Uses a timeline to continuously update the clock and date on the Scheduling screen. Lambda expression used
     */
    public void dateAndTimeDisplay() 
    {
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.ZERO, event ->
                {
                      LocalDateTime localDateTime = LocalDateTime.now();
       
                      int hour = localDateTime.getHour();
                      int minute = localDateTime.getMinute();
            
                      String currentTime = String.format("%02d:%02d", hour, minute);
                      currentTimeLabel.setText(currentTime);
            
                      DateTimeFormatter dateFormatted = DateTimeFormatter.ofPattern("MM-dd-yyyy", Locale.ENGLISH);
                      String dateFormatStr = dateFormatted.format(localDateTime);
                      currentDateLabel.setText(dateFormatStr);
                }));
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
     
    }

    /**
     * Checks the amount of appointments that are upcoming within 15 minutes, if there is at least one it warns
     * you with a dialogue box saying there is one, if not it tells you there isnt one.
     */
    public void appointmentNumber()
    {
        for (Appointment appointment : appointmentTableview.getItems())
        {
            LocalTime appTime = appointment.getStartDate().toLocalTime();
            LocalTime timeFrame = LocalTime.now().plusMinutes(15);

            if(appTime.until(timeFrame, ChronoUnit.MINUTES) <= 15 && appTime.until(timeFrame, ChronoUnit.MINUTES) >= 0)
            {
                numberUpcoming = numberUpcoming + 1;

            }

        }

        if(numberUpcoming >= 1)
        {
            Alert alertSE = new Alert(Alert.AlertType.WARNING);
            alertSE.setTitle("Appointment Reminder!");
            alertSE.setHeaderText("You Have an Appointment in less than 15 minutes!");
            alertSE.setContentText("Good Luck");
            alertSE.show();

        }

        else
        {
            Alert alertSE = new Alert(Alert.AlertType.INFORMATION);
            alertSE.setTitle("Appointment Update");
            alertSE.setHeaderText("You Have no Appointments within 15 minutes");
            alertSE.setContentText("Have a Good Day!");
            alertSE.show();
        }

    }

    /**
     * If you click the overview tab it shows you all of the appointments that are scheduled with no filter
     * @param event
     */
    @FXML
    private void overviewTabClicked(Event event)
    {
        appointmentTableview.setItems(Schedule.getAllAppointments());

        appointmentIDCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("appTitle"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("appLocation"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("appDescription"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("appContact"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("appType"));
        startDateCol.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDateCol.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        customerIDCol.setCellValueFactory(new PropertyValueFactory<>("appCustomer"));


    }

    /**
     * If you click the month tab it reloads the table with only appointments that are scheduled within the calendar month
     * @param event
     */
    @FXML
    private void monthTabClicked(Event event)
    {
        appointmentMonthTableview.setItems(Schedule.getAllAppointments());
        monthAppointmentIDCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        monthTitleCol.setCellValueFactory(new PropertyValueFactory<>("appTitle"));
        monthDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("appLocation"));
        monthLocationCol.setCellValueFactory(new PropertyValueFactory<>("appDescription"));
        monthContactCol.setCellValueFactory(new PropertyValueFactory<>("appContact"));
        monthTypeCol.setCellValueFactory(new PropertyValueFactory<>("appType"));
        monthStartDateCol.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        monthEndDateCol.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        monthCustomerIDCol.setCellValueFactory(new PropertyValueFactory<>("appCustomer"));

        int monthInt = LocalDateTime.now().getMonthValue();

        appointmentMonthTableview.setItems(Schedule.getAllAppointments().filtered(divisionPredicate(monthInt)));

    }

    /**
     * If you click the week tab it reloads the table with appointments that are scheduled within the next 7 days.
     * @param event
     */
    @FXML
    private void weekTabClicked(Event event)
    {
        appointmentWeekTableview.setItems(Schedule.getAllAppointments());
        weekAppointmentIDCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        weekTitleCol.setCellValueFactory(new PropertyValueFactory<>("appTitle"));
        weekDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("appLocation"));
        weekLocationCol.setCellValueFactory(new PropertyValueFactory<>("appDescription"));
        weekContactCol.setCellValueFactory(new PropertyValueFactory<>("appContact"));
        weekTypeCol.setCellValueFactory(new PropertyValueFactory<>("appType"));
        weekStartDateCol.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        weekEndDateCol.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        weekCustomerIDCol.setCellValueFactory(new PropertyValueFactory<>("appCustomer"));

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nowPlus7 = now.plusDays(7);
        FilteredList<Appointment> filteredData = new FilteredList<>(Schedule.getAllAppointments());
        filteredData.setPredicate(row -> {

            LocalDateTime rowDate = row.getStartDate();

            return rowDate.isAfter(now) && rowDate.isBefore(nowPlus7);
        });
        appointmentWeekTableview.setItems(filteredData);
    }



    /**
     * When the Customer button is clicked it sends the user to the Customer Data Menu
     * @param event
     * @throws IOException
     */
    @FXML
    private void customerButtonClicked(ActionEvent event) throws IOException 
    {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("CustomerData.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * When the Add button is clicked it sends the user to the Add Appointment Menu
     * @param event
     * @throws IOException
     */
    @FXML
    private void addButtonClicked(ActionEvent event) throws IOException 
    {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("AddAppointmentMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * When the Delete button it clicked it gets the selected object in the table and ask for a confirmation and shows the ID and type
     * @param event
     * @throws SQLException
     */
    @FXML
    private void deleteButtonClicked(ActionEvent event) throws SQLException
    {
        Appointment appointment = appointmentTableview.getSelectionModel().getSelectedItem();


        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(" You are about to delete appointment " + appointment.getAppointmentID() + " of type " + appointment.getAppType());
        alert.setContentText("Are you ok with this?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK)
        {
            Connection conn = DBConnection.startConnection();
            DBQuery.setStatement(conn);
            Statement statement = DBQuery.getStatement();
            String insertStatement = "DELETE FROM appointments WHERE Appointment_ID = '" + appointment.getAppointmentID() + "'";
            statement.execute(insertStatement);

            Schedule.deleteAppointment(appointment);

        }

    }

    /**
     * When the Update button is clicked it sends the user to the Update Appointment menu and sends the data from the screen to the
     * next screen using "sendModData"
     * @param event
     * @throws IOException
     */
    @FXML
    private void updateButtonClicked(ActionEvent event) throws IOException 
    {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("UpdateAppointmentMenu.fxml"));
        loader.load();

        UpdateAppointmentMenuController UAMController = loader.getController();
        UAMController.sendModData(appointmentTableview.getSelectionModel().getSelectedItem());


        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /**
     * When the Report button is clicked it sends the user to the Report Menu.
     * @param event
     * @throws IOException
     */
    @FXML
    void reportsButtonClicked(ActionEvent event) throws IOException
    {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("ReportMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }


}
