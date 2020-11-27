
package Main;


import Utils.DBConnection;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application 
{

    @Override
    public void start(Stage primaryStage) throws Exception
    {        
        System.out.println(Locale.getDefault());
        ResourceBundle bundle = ResourceBundle.getBundle("Main/Lang_fr", Locale.getDefault());

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Views/LoginMenu.fxml"),bundle);


        Scene scene = new Scene(root);
        scene.setRoot(root);

        primaryStage.setScene(scene);
        primaryStage.show();   
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException 
    {

        DBConnection.startConnection();
        launch(args);
        DBConnection.closeConnection();
    }

}
