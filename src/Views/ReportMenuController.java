
package Views;

import Model.Appointment;
import Model.Customer;
import Model.Schedule;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
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



        @FXML
        void totalTabClicked(Event event)
        {
                XYChart.Series<String, Number> series1 = new XYChart.Series<>();
                series1.setName("January");
                series1.getData().add(new XYChart.Data<String,Number>());

                XYChart.Series<String, Number> series2 = new XYChart.Series<>();
                series1.setName("February");

                XYChart.Series<String, Number> series3 = new XYChart.Series<>();
                series1.setName("March");

                XYChart.Series<String, Number> series4 = new XYChart.Series<>();
                series1.setName("April");

                XYChart.Series<String, Number> series5 = new XYChart.Series<>();
                series1.setName("May");

                XYChart.Series<String, Number> series6 = new XYChart.Series<>();
                series1.setName("June");

                XYChart.Series<String, Number> series7 = new XYChart.Series<>();
                series1.setName("July");

                XYChart.Series<String, Number> series8 = new XYChart.Series<>();
                series1.setName("August");

                XYChart.Series<String, Number> series9 = new XYChart.Series<>();
                series1.setName("September");

                XYChart.Series<String, Number> series10 = new XYChart.Series<>();
                series1.setName("October");

                XYChart.Series<String, Number> series11 = new XYChart.Series<>();
                series1.setName("November");

                XYChart.Series<String, Number> series12 = new XYChart.Series<>();
                series1.setName("December");


        }

        @FXML
        void individualScheduleTabClicked(Event event)
        {

        }

        @FXML
        void percentTypeTabClicked(Event event)
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


