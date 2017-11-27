package DeliveryCompany;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URI;
import java.net.URL;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        URI url = new File("src/main/java/DeliveryCompany/LogInWindow.fxml").toURI();
        Parent root = FXMLLoader.load(url.toURL());
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Log in");
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
