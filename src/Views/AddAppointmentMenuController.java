/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Z
 */
public class AddAppointmentMenuController implements Initializable {

    @FXML
    private Button addButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Label appointmentIDLabel;
    @FXML
    private Label titleLabel;
    @FXML
    private Label descriptionLabel;
    @FXML
    private Label locationLabel;
    @FXML
    private Label contactLabel;
    @FXML
    private Label endDateLabel;
    @FXML
    private TextField appointmentIDText;
    @FXML
    private TextField titleText;
    @FXML
    private TextField descriptionText;
    @FXML
    private TextField locationText;
    @FXML
    private TextField contactText;
    @FXML
    private TextField endDateText;
    @FXML
    private Label typeLabel;
    @FXML
    private Label startDateLabel;
    @FXML
    private TextField startDateText;
    @FXML
    private TextField typeText;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addButtonClicked(ActionEvent event) {
    }

    @FXML
    private void cancelButtonClicked(ActionEvent event) {
    }
    
}
