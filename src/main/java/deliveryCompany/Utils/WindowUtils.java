package deliveryCompany.Utils;

import javafx.fxml.FXMLLoader;

import java.net.URL;

public class WindowUtils {
    public static FXMLLoader createFmxWindow(URL url, Object controller) {
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        fxmlLoader.setController(controller);

        return fxmlLoader;
    }
}
