
package Main;


import Model.Clientele;
import Utils.DBConnection;
import static Utils.DBConnection.conn;
import Utils.DBQuery;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Zach Hendrix
 */
public class Main extends Application 
{


    /**
     * Initializes the Main class
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        ResourceBundle rb = ResourceBundle.getBundle("Main/Lang", Locale.getDefault());

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Views/LoginMenu.fxml"),rb);
        Scene scene = new Scene(root);
        scene.setRoot(root);

        primaryStage.setScene(scene);
        primaryStage.show();   
    }

    /**
     * Calls the methods set in the DBConnection that sets information from the database into the local Observable Lists
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException 
    {

        DBConnection.startConnection();
        DBConnection.loadCountryData();
        DBConnection.loadFirstLevelData();
        DBConnection.loadCustomerData();
        DBConnection.loadContactData();
        DBConnection.loadUserData();
        DBConnection.loadAppointmentData();

        launch(args);
        DBConnection.closeConnection();
    }

}
