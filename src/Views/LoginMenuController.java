/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;
import Model.*;
import static com.mysql.cj.conf.PropertyKey.logger;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
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
    private ImageView flagIconFrance;

    @FXML
    private ImageView flagIconUK;
    
    @FXML
    private Label languageLabel;

    @FXML
    private Label languageTextLabel;
    
    private ResourceBundle rb;
    private ResourceBundle rbRef;   

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        Locale locale = rb.getLocale();
        rbRef = rb;
        System.out.println(locale);
        
        if(rb.getLocale().toString().equals("fr"))
        {
           System.out.println("FRENCH");
           passwordLabel.setText(rb.getString("password")+ ":");
           userIdLabel.setText(rb.getString("username")+ ":");
           locationLabel.setText(rb.getString("france"));
           languageTextLabel.setText(rb.getString("language") + ":");
           languageLabel.setText(rb.getString("french"));
           loginButton.setText(rb.getString("login"));
           flagIconUK.setImage(null);
                   
        }
        
        
    }   
    
    
    @FXML
    void loginButtonClicked(ActionEvent event) throws IOException 
    {
        if(userIdTextField.getText().equals(username) && passwordTextField.getText().equals(password))
        {
            Logging.goodLogin();
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("ScheduleMenu.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
        
        else
        {
            
            //TODO: DIFFERENT ALERT LANGUAGE
            Logging.badLogin();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Login Warning");
            alert.setHeaderText("Incorrect Username or Password");
            alert.setContentText("Try Again");
            alert.showAndWait();  
               
        }
    }
    
    
}
