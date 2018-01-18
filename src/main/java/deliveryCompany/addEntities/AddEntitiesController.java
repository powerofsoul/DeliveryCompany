package deliveryCompany.addEntities;

import databaseHandler.DatabaseConnection;
import databaseHandler.Delivery;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddEntitiesController {
    @FXML
    private ComboBox senderComboBox;
    @FXML
    private ComboBox receiverComboBox;
    @FXML
    private ComboBox postmanComboBox;
    @FXML
    private DatePicker pickupDatePicker;
    @FXML
    private TextField packageIdTextField;

    @FXML
    private void initialize() throws SQLException {
        refresh();
    }

    private void refresh() throws SQLException {
        senderComboBox.getItems().clear();
        receiverComboBox.getItems().clear();
        postmanComboBox.getItems().clear();

        senderComboBox.getItems().addAll(getAll("expeditor"));
        receiverComboBox.getItems().addAll(getAll("destinatar"));
        postmanComboBox.getItems().addAll(getAll("curier"));
        tyopeOfPeople.getItems().addAll("Sender", "Receiver");
    }

    private List<String> getAll(String who) throws SQLException {
        ResultSet rs = DatabaseConnection.getQueryResult(String.format("Select * from %s", who));

        List<String> senders = new ArrayList<>();

        while (rs.next()) {
            senders.add(rs.getString("Nume") + " " + rs.getString("Prenume"));
        }
        return senders;
    }

    private List<Integer> getAllPackageIds() throws SQLException {
        ResultSet rs = DatabaseConnection.getQueryResult(String.format("Select * from colet"));

        List<Integer> senders = new ArrayList<Integer>();

        while (rs.next()) {
            senders.add(rs.getInt("Id"));
        }
        return senders;
    }

    @FXML
    public void addDelivery() throws SQLException {
        if (!packageExist()) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Unable to add delivery");
            a.setContentText("Package id is incorrect");
            a.showAndWait();
            return;
        }
        try {
            String senderfirstName = senderComboBox.getSelectionModel().getSelectedItem().toString().split(" ")[0];
            String senderlastName = senderComboBox.getSelectionModel().getSelectedItem().toString().split(" ")[1];

            int senderId = getId(senderfirstName, senderlastName, "expeditor");

            String receiverfirstName = receiverComboBox.getSelectionModel().getSelectedItem().toString().split(" ")[0];
            String receiverlastName = receiverComboBox.getSelectionModel().getSelectedItem().toString().split(" ")[1];

            int receiverId = getId(receiverfirstName, receiverlastName, "destinatar");

            String postmanfirstName = postmanComboBox.getSelectionModel().getSelectedItem().toString().split(" ")[0];
            String postmanlastName = postmanComboBox.getSelectionModel().getSelectedItem().toString().split(" ")[1];

            int postmanId = getId(postmanfirstName, postmanlastName, "curier");


            LocalDate localDate = pickupDatePicker.getValue();

            String query = String.format("INSERT INTO livrare (IdExpeditor, IdDestinatar, IdColet, IdCurier, DataExpediere, DataRidicare)" +
                            "Values(%d, %d, %d, '%d', '%s', null)",
                    senderId,
                    receiverId,
                    Integer.parseInt(packageIdTextField.textProperty().getValue()),
                    postmanId,
                    localDate);
            System.out.println(query);
            DatabaseConnection.executeUpdateQuery(query);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Delivery has been created;");

            alert.showAndWait();
        } catch (Exception e) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error");
            a.setContentText("Please be sure that all information is correct");
            a.showAndWait();
        }
    }

    public int getId(String firstName, String lastName, String who) throws SQLException {
        ResultSet r = DatabaseConnection.getSingleRowQueryResult(String.format("Select id from %s where nume='%s' and prenume='%s'",
                who,
                firstName,
                lastName));
        return r.getInt("Id");
    }

    @FXML
    public boolean checkPackageId() throws SQLException {
        boolean packageIdExists = packageExist();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Checking package id");
        String message = "The package does not exists";
        if (packageIdExists) {
            message = "The package exists";
            alert.setContentText(message);
            alert.showAndWait();
            return true;
        }

        alert.setContentText(message);
        alert.showAndWait();
        return false;
    }

    private boolean packageExist() throws SQLException {
        if (!packageIdTextField.textProperty().getValue().matches("\\d+"))
            return false;

        return DatabaseConnection.getSingleRowQueryResult(String.format("Select Count(*) as c from colet where id=%s", packageIdTextField.textProperty().getValue())).getInt("c") != 0;
    }


    @FXML
    public TextField firstNamePeople;
    @FXML
    public TextField lastNamePeople;
    @FXML
    public TextField addressPeople;
    @FXML
    public ComboBox tyopeOfPeople;

    @FXML
    public void addPeople() throws SQLException {
        String firstName = firstNamePeople.textProperty().getValue();
        String lastName = lastNamePeople.textProperty().getValue();
        String adress = addressPeople.textProperty().getValue();

        String type = tyopeOfPeople.getValue().toString();
        String db = "expeditor";
        if (type == "Receiver")
            db = "destinatar";

        DatabaseConnection.executeUpdateQuery(String.format("Insert into %s Values(null, '%s', '%s', '%s')", db, firstName, lastName, adress));

        addedSuccessfully();

        refresh();
    }


    @FXML
    public TextField firstNamePostman;
    @FXML
    public TextField lastNamePostman;

    @FXML
    public void addPostman() throws SQLException {
        String firstName = firstNamePostman.textProperty().getValue();
        String lastName = lastNamePostman.textProperty().getValue();
        if (firstName == "" || lastName == "") {
            missingInformation();
            return;
        }
        DatabaseConnection.executeUpdateQuery(String.format("Insert into curier Values(null, '%s', '%s')", firstName, lastName));

        addedSuccessfully();

        refresh();
    }

    private void addedSuccessfully() {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Added");
        a.setContentText("Added successfully");
        a.showAndWait();
    }

    private void missingInformation() {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setTitle("Error");
        a.setContentText("You need to complete all fields");
        a.showAndWait();
    }

    @FXML
    public TextField lengthTextField;
    @FXML
    public TextField widthTextField;
    @FXML
    public TextField heightTextField;
    @FXML
    public TextField typeTextField;
    @FXML
    public TextField weightTextField;

    public void addPackage() throws  SQLException{
        String length = lengthTextField.textProperty().getValue();
        String width = widthTextField.textProperty().getValue();
        String height = heightTextField.textProperty().getValue();
        String type = typeTextField.textProperty().getValue();
        String weight = weightTextField.textProperty().getValue();

        if(!length.matches("\\d+") || !width.matches("\\d+") || !height.matches("\\d+") || !weight.matches("\\d+") || type ==""){
            missingInformation();
            return;
        }

        DatabaseConnection.executeUpdateQuery(String.format("Insert into colet Values(null, %s, %s, %s, '%s', %s)", length, width, height, type, weight));

        addedSuccessfully();
    }
}
