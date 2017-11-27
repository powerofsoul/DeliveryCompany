package deliveryCompany;

import DatabaseHandler.Credentials;
import DatabaseHandler.DatabaseConnection;
import deliveryCompany.logInWindow.LogInController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URI;
import java.sql.SQLException;
import java.util.function.Function;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        DatabaseConnection connection = new DatabaseConnection();
        connection.connect();

        URI url = new File("src/main/java/deliveryCompany/logInWindow/LogInWindow.fxml").toURI();
        FXMLLoader fxmlLoader = new FXMLLoader(url.toURL());
        LogInController controller = new LogInController(credentials -> {
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
    }

    public static void main(String[] args){
        launch(args);
    }
}
