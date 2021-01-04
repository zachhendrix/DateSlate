/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;
import Model.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

import Utils.DBConnection;
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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Zach Hendrix
 */
public class LoginMenuController implements Initializable 
{
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

    private ResourceBundle rbRef;
    public static String loggedIn;

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


    /**
     *
     * @param event
     * @throws IOException
     * @throws SQLException
     */
    @FXML
    void loginButtonClicked(ActionEvent event) throws IOException, SQLException
    {
        if(DBConnection.checkUserData(userIdTextField.getText(),passwordTextField.getText()))
        {
            Logging.goodLogin();

            loggedIn = userIdTextField.getText();
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("ScheduleMenu.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
        
        else
        {

            if(rbRef.getLocale().toString().equals("fr"))
            {
                Logging.badLogin();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Avertissement de connexion");
                alert.setHeaderText("Identifiant ou mot de passe incorrect");
                alert.setContentText("Réessayer");
                alert.showAndWait();
            }

            else
            {
                Logging.badLogin();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Login Warning");
                alert.setHeaderText("Incorrect Username or Password");
                alert.setContentText("Try Again");
                alert.showAndWait();

            }
               
        }
    }


}
