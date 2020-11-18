/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import java.net.URL;
import java.io.IOException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    
    @FXML
    private Tab overviewTab;
    @FXML
    private TableView<?> customerTableView;
    @FXML
    private TableColumn<?, ?> appointmentIDCol;
    @FXML
    private TableColumn<?, ?> titleCol;
    @FXML
    private TableColumn<?, ?> descriptionCol;
    @FXML
    private TableColumn<?, ?> locationCol;
    @FXML
    private TableColumn<?, ?> contactCol;
    @FXML
    private TableColumn<?, ?> typeCol;
    @FXML
    private TableColumn<?, ?> startDateCol;
    @FXML
    private TableColumn<?, ?> endDateCol;
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
    private Button cancelButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
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
    private void addButtonClicked(ActionEvent event) 
    {
        
    }

    @FXML
    private void deleteButtonClicked(ActionEvent event) 
    {
        
    }

    @FXML
    private void cancelButtonClicked(ActionEvent event) 
    {
        
    }
    
}
