package deliveryCompany.mainWindow;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class MainWindowController {
    @FXML
    private Pane menuPane;
    @FXML
    private Label userNameLabel;
    private String userName;

    @FXML
    private void initialize() {
        userNameLabel.setText(userName);
    }

    public MainWindowController(String userName) {
        this.userName = userName;
    }
}
