package deliveryCompany.logInWindow;

import databaseHandler.Credentials;
import com.jfoenix.controls.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.function.Function;


public class LogInController {
    @FXML
    private Label errorMessageLabel;
    @FXML
    private JFXTextField userNameField;
    @FXML
    private JFXPasswordField passwordField;

    private Function<Credentials, Boolean> validCredentials;

    public LogInController(Function<Credentials, Boolean> validCredentials) {
        this.validCredentials =  validCredentials;
    }

    @FXML
    private void initialize() {
        addListenerWhenCredentialsAreChanged();
    }

    @FXML
    private void logIn(){
        boolean credentialsAreCorrect = validCredentials.apply(new Credentials(userNameField.getText(), passwordField.getText()));

        if(credentialsAreCorrect)
            System.out.println("correct credentials");
        else
            showError();
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
