package deliveryCompany.Utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class WindowUtils {
    public static FXMLLoader createFmxWindow(URL url, Object controller) {
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        fxmlLoader.setController(controller);

        return fxmlLoader;
    }

    public static Stage showNewWindow(FXMLLoader window, String title, boolean resizable) throws IOException{
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setResizable(resizable);
        stage.setScene(new Scene(window.load()));

        stage.show();
        return stage;
    }
}
