import databaseHandler.DatabaseConnection;
import deliveryCompany.logInWindow.LogInWindowController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.sql.SQLException;

import static deliveryCompany.Utils.WindowUtils.createFmxWindow;
import static deliveryCompany.Utils.WindowUtils.showNewWindow;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        DatabaseConnection.connect();

        URL fmxLoginWindowUrl = new File("src/main/java/deliveryCompany/logInWindow/LogInWindow.fxml").toURL();

        LogInWindowController controller = new LogInWindowController(credentials -> {
            try {
                return DatabaseConnection.validCredentials(credentials);
            } catch (SQLException e) {
                System.out.println("Error " + e.getMessage());
            }
            return false;
        });

        FXMLLoader loginWindowFXM = createFmxWindow(fmxLoginWindowUrl, controller);
        showNewWindow(loginWindowFXM, "Log in", false);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
