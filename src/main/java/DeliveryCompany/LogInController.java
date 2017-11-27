package DeliveryCompany;

import com.jfoenix.controls.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.EventHandler;

public class LogInController {
    @FXML
    private Label errorMessageLabel;
    @FXML
    private JFXTextField userNameField;
    @FXML
    private JFXPasswordField passwordField;

    public LogInController() {}

    @FXML
    private void initialize() {
        addListenerWhenCredentialsAreChanged();
    }
    
    @FXML
    private void logIn(){
        errorMessageLabel.setVisible(true);
    }

    private void addListenerWhenCredentialsAreChanged() {
        userNameField.textProperty().addListener(e->{
            resetError();
        });
        passwordField.textProperty().addListener(e->{
            resetError();
        });
    }
    private void resetError(){
        errorMessageLabel.setVisible(false);
    }
}
