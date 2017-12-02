package deliveryCompany;

import databaseHandler.DatabaseConnection;
import deliveryCompany.logInWindow.LogInWindowController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URI;
import java.sql.SQLException;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        DatabaseConnection connection = new DatabaseConnection();
        connection.connect();

        URI url = new File("src/main/java/deliveryCompany/logInWindow/LogInWindow.fxml").toURI();
        FXMLLoader fxmlLoader = new FXMLLoader(url.toURL());
        LogInWindowController controller = new LogInWindowController(credentials -> {
            try{
                return connection.validCredentials(credentials);
            }
            catch (SQLException e){
                System.out.println("Error " +e.getMessage());
            }
            return false;
        });
        fxmlLoader.setController(controller);

        primaryStage.setScene(new Scene(fxmlLoader.load()));
        primaryStage.setTitle("Log in");
        primaryStage.show();
        primaryStage.setResizable(false);
    }

    public static void main(String[] args){
        launch(args);
    }
}
