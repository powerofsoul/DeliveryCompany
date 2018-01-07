package deliveryCompany.logInWindow;

import databaseHandler.Credentials;
import com.jfoenix.controls.*;
import deliveryCompany.mainWindow.MainWindowController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.awt.event.InputEvent;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.function.Function;

import static deliveryCompany.Utils.WindowUtils.closeWindowFromInputEvent;
import static deliveryCompany.Utils.WindowUtils.createFmxWindow;
import static deliveryCompany.Utils.WindowUtils.showNewWindow;


public class LogInWindowController {
    @FXML
    private Label errorMessageLabel;
    @FXML
    private JFXTextField userNameField;
    @FXML
    private JFXPasswordField passwordField;

    private Function<Credentials, Boolean> validCredentials;

    public LogInWindowController(Function<Credentials, Boolean> validCredentials) {
        this.validCredentials =  validCredentials;
    }

    @FXML
    private void initialize() {
        addListenerWhenCredentialsAreChanged();
    }

    @FXML
    private void logIn(ActionEvent ae) throws IOException{
        boolean credentialsAreCorrect = validCredentials.apply(new Credentials(userNameField.getText(), passwordField.getText()));

        if(credentialsAreCorrect) {
            ShowMainwindow(ae);
        }
        else
            showError();
    }

    private void ShowMainwindow(ActionEvent ae) throws IOException {
        URL mainWindowUrl = new File("src/main/java/deliveryCompany/mainWindow/MainWindow.fxml").toURL();
        MainWindowController mainWindowController = new MainWindowController(userNameField.getText());
        FXMLLoader mainWindow =  createFmxWindow(mainWindowUrl, mainWindowController);
        closeWindowFromInputEvent(ae);
        showNewWindow(mainWindow, "Delivery Company", true);
    }

    private void addListenerWhenCredentialsAreChanged() {
        userNameField.textProperty().addListener(e->{
            resetError();
        });
        passwordField.textProperty().addListener(e->{
            resetError();
        });
    }

    public void showError(){
        showError("Incorrect Credentials");
    }

    public void showError(String errorMessage){
         errorMessageLabel.setText(errorMessage);
         errorMessageLabel.setVisible(true);
    }

    private void resetError(){
        errorMessageLabel.setVisible(false);
    }
}
