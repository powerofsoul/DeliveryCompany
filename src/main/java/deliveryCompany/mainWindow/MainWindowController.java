package deliveryCompany.mainWindow;

import databaseHandler.User;
import deliveryCompany.mainWindow.menu.MenuItem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import static deliveryCompany.Utils.WindowUtils.createFmxWindow;

public class MainWindowController {
    private User user;

    @FXML
    private Pane menuBox;
    @FXML
    private Label userNameLabel;

    @FXML
    private AnchorPane mainView;

    @FXML
    private Circle imageCircle;

    @FXML
    private void initialize() throws IOException {
        try {
            userNameLabel.setText(user.username);
            imageCircle.setFill(new ImagePattern(user.getImage()));
            addMenuItems();
        }catch (Exception e){
            System.out.println(e.getStackTrace());
        }
    }

    private void addMenuItems() {
        switch (getPermissionLevel()) {
            case 5:
                addAdminMenuItems();
        }
    }

    private void addAdminMenuItems() {
        try {
            URL menuItemUrl = new File("src/main/java/deliveryCompany/manageDelivery/ManageDeliveryWindow.fxml").toURL();
            FXMLLoader menuItem = createFmxWindow(menuItemUrl, null);
            MenuItem m = new MenuItem("TEST", menuItem.load(), mainView);
            menuBox.getChildren().add(m.getMenuButton());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private int getPermissionLevel() {
        return 5;
    }

    public MainWindowController(User user) {
        this.user = user;
    }
}
