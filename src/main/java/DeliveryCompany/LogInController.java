package DeliveryCompany;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class LogInController {
    @FXML
    private Label errorMessageLabel;
    public LogInController() {}

    @FXML
    private void initialize() {}

    @FXML
    private void logIn(){
        errorMessageLabel.setVisible(true);
    }
}
