package Views;

import Model.Logging;
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

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * The LoginMenuController is a controller for the LoginMenu.fxml
 * @author Zach Hendrix
 *
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

    public static String loggedIn;
    private ResourceBundle rb;

    /**
     * On Initialize the ResourceBundle is checked and the labels are set to the appropriate language.The picture of
     * the UK flag symbolizing the English language is set to null if the language is French which shows a French flag
     * instead.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        Locale locale = rb.getLocale();
        System.out.println(locale);
        this.rb = rb;
        System.out.println(Locale.getDefault());

        passwordLabel.setText(rb.getString("password")+ ":");
        userIdLabel.setText(rb.getString("username")+ ":");
        locationLabel.setText(rb.getString("france"));
        languageTextLabel.setText(rb.getString("language") + ":");
        languageLabel.setText(rb.getString("french"));
        loginButton.setText(rb.getString("login"));
        flagIconUK.setImage(null);


    }


    /**
     * When the Login button is clicked the userIDTextField and the passwordTextField are checked on the database tables
     * using the "checkUserData" method. If they match the tables the user is sent to the Schedule Menu. Else they are met with a warning label.
     * The resulting bad or good login is appended in the login_activity.txt
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
                Logging.badLogin();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(rb.getString("LoginWarning"));
                alert.setHeaderText(rb.getString("IncorrectUsernamePassword"));
                alert.setContentText(rb.getString("TryAgain"));
                alert.showAndWait();

               
        }
    }


}
