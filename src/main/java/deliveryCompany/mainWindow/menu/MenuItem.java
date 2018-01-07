package deliveryCompany.mainWindow.menu;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import static deliveryCompany.Utils.WindowUtils.createFmxWindow;

public class MenuItem {
    private AnchorPane viewLocation;
    private AnchorPane menuContent;
    private String name;

    @FXML
    private void showView() throws IOException{
        this.viewLocation.getChildren().removeAll();
        this.viewLocation.getChildren().addAll(menuContent.getChildren());
    }

    public MenuItem(String name, AnchorPane menuContent, AnchorPane viewLocation){
        this.name = name;
        this.menuContent = menuContent;
        this.viewLocation = viewLocation;
    }

    public Node getMenuButton() throws IOException{
        URL buttonUrl = new File("src/main/java/deliveryCompany/mainWindow/menu/MenuButton.fxml").toURL();
        FXMLLoader button =  createFmxWindow(buttonUrl, this);
        return button.load();
    }
}
