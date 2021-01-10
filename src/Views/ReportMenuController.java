package Views;

import Model.*;
import Utils.DBConnection;
import Utils.DBQuery;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import static Model.Appointment.appointmentDateIntPredicate;
import static Model.Contact.contactSchedulePredicate;


/**
 *
 * The ReportMenuController is a controller for the ReportMenu.fxml
 * @author Zach Hendrix
 *
 */
public class ReportMenuController implements Initializable
{

    Stage stage;
    Parent scene;


    private boolean pieIsLoaded;
    String[] monthName = {"January","February","March","April","May","June","July","August","September", "October","November", "December"};
    private ObservableList<Report> appReport;



    @FXML
    private Tab totalTab;
    @FXML
    private TableView<Report> monthTypeTableview;

    @FXML
    private TableColumn<Report, String> monthCol;

    @FXML
    private TableColumn<Report, String> monthTypeCol;

    @FXML
    private TableColumn<Report, String> amountCol;


    @FXML
    private Tab individualScheduleTab;
    @FXML
    private TableView<Appointment> contactScheduleTableview;
    @FXML
    private TableColumn<Appointment, Integer> appointmentIDCol;
    @FXML
    private TableColumn<Appointment, String> titleCol;
    @FXML
    private TableColumn<Appointment, String> descriptionCol;
    @FXML
    private TableColumn<Appointment, String> locationCol;
    @FXML
    private TableColumn<Appointment, Contact> contactCol;
    @FXML
    private TableColumn<Appointment, String> typeCol;
    @FXML
    private TableColumn<Appointment, Date> startDateCol;
    @FXML
    private TableColumn<Appointment, Date> endDateCol;
    @FXML
    private TableColumn<Appointment, Customer> customerIDCol;
    @FXML
    private ComboBox<Contact> contactComboBox;

    @FXML
    private Tab percentTypeTab;
    @FXML
    private PieChart pieChart;


    @FXML
    private Button backButton;

    @FXML
    private Label currentTimeLabel;
    @FXML
    private Label currentDateLabel;


    /**
     * Initializes the controller class, sets values to the first table shown and runs the date and time method to start the clock.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {


        contactComboBox.setItems(ContactList.getAllContacts());
        contactScheduleTableview.setItems(Schedule.getAllAppointments());
        appointmentIDCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("appTitle"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("appLocation"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("appDescription"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("appContact"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("appType"));
        startDateCol.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDateCol.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        customerIDCol.setCellValueFactory(new PropertyValueFactory<>("appCustomer"));




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
     * When the value is changed in the Contact Combobox it sets the value to a Lambda expression to check which appointments
     * that particular Contact has.
     * @param event
     */
        @FXML
        void contactComboSelected(ActionEvent event)
        {
            if(contactComboBox.getValue() == null )
            {
                contactScheduleTableview.getSelectionModel().clearSelection();
            }
            else
            {
                contactScheduleTableview.setItems(Schedule.getAllAppointments().filtered(contactSchedulePredicate(contactComboBox.getValue())));
            }
        }


    /**
     * Sets the PieChart with data from the server tables.
     * @throws SQLException
     */
    private void setPieChart() throws SQLException
    {

        Connection conn = DBConnection.startConnection();

        DBQuery.setStatement(conn);
        Statement statement = DBQuery.getStatement();
        String selectStatement = "SELECT appointments.type,  COUNT(*) From appointments Group By type";
        statement.execute(selectStatement);
        ResultSet rs = statement.getResultSet();

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        while (rs.next())
        {

            int number = rs.getInt("COUNT(*)");
            String typeName = rs.getString("type");
            pieChartData.add(new PieChart.Data(typeName, number));


        }
        pieChart.setData(pieChartData);
        pieIsLoaded = true;
    }

    /**
     * When the total tab is clicked a table is called from the database that aggregates the number of similar types of appointments
     * and sets them equal to variables. The rows are then added as a Report object into the observable list.
     * @param event
     * @throws SQLException
     */
    @FXML
    void totalTabClicked(Event event) throws SQLException
    {
        appReport = FXCollections.observableArrayList();
        Connection conn = DBConnection.startConnection();
        DBQuery.setStatement(conn);
        Statement statement = DBQuery.getStatement();
        String selectStatement = "SELECT MonthName(Start) AS monthName , Type AS typeName, COUNT(*) AS amount FROM appointments GROUP BY MONTHNAME(Start), Type";
        statement.execute(selectStatement);
        ResultSet rs = statement.getResultSet();

        while (rs.next())
        {

            String month = rs.getString("monthName");

            String type = rs.getString("typeName");

            String amount = rs.getString("amount");

            appReport.add(new Report(month, type, amount));


        }
        monthCol.setCellValueFactory(new PropertyValueFactory<>("month"));
        monthTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        monthTypeTableview.getItems().setAll(appReport);

    }

    /**
     * When the Percent Type tab is clicked it runs the "setPieChart" method.
     * @param event
     * @throws SQLException
     */
    @FXML
    void percentTypeClicked(Event event) throws SQLException
    {
        if(!pieIsLoaded)
        {
            setPieChart();
        }
    }


    /**
     * When the Back button is clicked it returns the user to the Schedule Menu.
     * @param event
     * @throws IOException
     */
    @FXML
    void backButtonClicked(ActionEvent event) throws IOException
    {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("ScheduleMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
}
