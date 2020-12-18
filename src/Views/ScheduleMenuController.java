/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Model.Appointment;
import Model.Clientele;
import Model.Customer;
import Model.Schedule;
import java.net.URL;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Z
 */
public class ScheduleMenuController implements Initializable 
{

    Stage stage;
    Parent scene;

    
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
    private Label currentTimeLabel;

    @FXML
    private Label currentDateLabel;

    /**
     * Initializes the controller class.
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
    }

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
    }

    @FXML
    private void customerButtonClicked(ActionEvent event) throws IOException 
    {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("CustomerData.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    private void addButtonClicked(ActionEvent event) throws IOException 
    {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("AddAppointmentMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    private void deleteButtonClicked(ActionEvent event)
    {
        Appointment appointment = appointmentTableview.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(" You are about to delete appointment " + appointment.getAppointmentID() + " of type " + appointment.getAppType());
        alert.setContentText("Are you ok with this?");

        Optional<ButtonType> result = alert.showAndWait();
        
        if (result.get() == ButtonType.OK)
        {
            Schedule.deleteAppointment(appointment);

        } 
    }

    @FXML
    private void updateButtonClicked(ActionEvent event) throws IOException 
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("UpdateAppointmentMenu.fxml"));
        loader.load();
        
        UpdateAppointmentMenuController UAMController = loader.getController();
        UAMController.sendModData(appointmentTableview.getSelectionModel().getSelectedItem());


        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }
    
}
