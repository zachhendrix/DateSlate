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
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
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
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Z
 */
public class ScheduleMenuController implements Initializable 
{

    Stage stage;
    Parent scene;
    private static int generateAppIDNum;
    
    @FXML
    private Tab overviewTab;
    @FXML
    private TableView<Appointment> appointmentTableView;
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
    private Tab monthTab;
    @FXML
    private Tab weekTab;
    @FXML
    private Button customerButton;
    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button updateButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        appointmentTableView.setItems(Schedule.getAllAppointments());
        
        appointmentIDCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("appTitle"));  
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("appLocation")); 
        locationCol.setCellValueFactory(new PropertyValueFactory<>("appDescription"));   
        contactCol.setCellValueFactory(new PropertyValueFactory<>("appContact")); 
        typeCol.setCellValueFactory(new PropertyValueFactory<>("appType")); 
        startDateCol.setCellValueFactory(new PropertyValueFactory<>("startDate")); 
        endDateCol.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        
    }    

    @FXML
    private void overviewTabClicked(Event event) 
    {
        
    }

    @FXML
    private void monthTabClicked(Event event) 
    {
        
    }

    @FXML
    private void weekTabClicked(Event event) 
    {
        
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
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Selected Part Will Be Deleted");
        alert.setContentText("Are you ok with this?");

        Optional<ButtonType> result = alert.showAndWait();
        
        if (result.get() == ButtonType.OK)
        {
            Appointment appointment = appointmentTableView.getSelectionModel().getSelectedItem();
            Schedule.deleteAppointment(appointment);

        } 
    }

    @FXML
    private void updateButtonClicked(ActionEvent event) throws IOException 
    {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("UpdateAppointmentMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    
}
