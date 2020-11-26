
package Main;


import Utils.DBConnection;
import java.sql.SQLException;
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
        Parent root = FXMLLoader.load(getClass().getResource("/Views/LoginMenu.fxml"));


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
