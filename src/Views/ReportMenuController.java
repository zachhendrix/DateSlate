
package Views;

import Model.Appointment;
import Model.Customer;
import Model.Schedule;
import Utils.DBConnection;
import Utils.DBQuery;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;



public class ReportMenuController implements Initializable
{

    Stage stage;
    Parent scene;


    @FXML
    private Tab totalTab;
    @FXML
    private BarChart<String, Integer> barChart;
    @FXML
    private CategoryAxis monthAxis;
    @FXML
    private NumberAxis numberAxis;

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
    private ComboBox<Customer> contactComboBox;

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



    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

        dateAndTimeDisplay();

    }

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



        public void setBarChart() throws SQLException {
            ObservableList<XYChart.Data<String, Integer>> appointmentByMonth = FXCollections.observableArrayList();
            XYChart.Series<String, Integer> series = new XYChart.Series<>();

            Connection conn = DBConnection.startConnection();
            DBQuery.setStatement(conn);
            Statement statement = DBQuery.getStatement();
            String selectStatement = "SELECT appointments.type,  COUNT(*) From appointments Group By Start";
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
            series.getData().addAll(appointmentByMonth);
            barChart.getData().add(series);

        }

        public void setPieChart() throws SQLException {

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
        }






    @FXML
    void individualTabClicked(Event event) throws SQLException
    {
        setBarChart();
    }

    @FXML
    void percentTypeClicked(Event event) throws SQLException
    {
        setPieChart();
    }

    @FXML
    void totalTabClicked(Event event)
    {

    }


        @FXML
        void backButtonClicked(ActionEvent event) throws IOException
        {
                stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("ScheduleMenu.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
        }

}



