/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;
import Model.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Z
 */
public class LoginMenuController implements Initializable 
{
    String username = "zhendrix";
    String password = "password";
    
    Stage stage;
    Parent scene;

    @FXML
    private Label passwordLabel;
    @FXML
    private Label userIdLabel;
    @FXML
    private TextField passwordTextField;
    @FXML
    private TextField userIdTextField;
    @FXML
    private Button loginButton;
    @FXML
    private Label locationLabel;
    @FXML
    private ImageView flagIcon;
    @FXML
    private Label languageLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
       
    }   
     @FXML
    void loginButtonClicked(ActionEvent event) throws IOException 
    {
        if(userIdTextField.getText().equals(username) && passwordTextField.getText().equals(password))
        {
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("ScheduleMenu.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
        
        else
        {
            Logging.SevereWarning();
        }


    }
    
    
}
