package deliveryCompany.mainWindow.menu;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import static deliveryCompany.Utils.WindowUtils.createFmxWindow;

public class MenuItem {
    private AnchorPane viewLocation;
    private String name;
    private URL fxmlURL;

    @FXML
    public JFXButton menuButton;

    @FXML
    private void initialize() throws IOException {
        menuButton.setText(name);
    }

    @FXML
    private void showView() throws IOException{
        AnchorPane menuContent = createFmxWindow(fxmlURL, null).load();
        this.viewLocation.getChildren().clear();
        this.viewLocation.getChildren().addAll(menuContent.getChildren());
    }

    public MenuItem(URL fxmlUrl, String name, AnchorPane viewLocation){
        this.name = name;
        this.fxmlURL = fxmlUrl;
        this.viewLocation = viewLocation;
    }

    public Node getMenuButton() throws IOException{
        URL buttonUrl = new File("src/main/java/deliveryCompany/mainWindow/menu/MenuButton.fxml").toURL();
        FXMLLoader button =  createFmxWindow(buttonUrl, this);
        return button.load();
    }
}
