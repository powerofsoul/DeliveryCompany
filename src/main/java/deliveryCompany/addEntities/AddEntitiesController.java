package deliveryCompany.addEntities;

import databaseHandler.DatabaseConnection;
import databaseHandler.Delivery;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

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
    private ComboBox packageIdComboBox;

    @FXML
    private void initialize() throws SQLException {
        senderComboBox.getItems().addAll(getAll("expeditor"));
        receiverComboBox.getItems().addAll(getAll("destinatar"));
        postmanComboBox.getItems().addAll(getAll("curier"));
        packageIdComboBox.getItems().addAll(getAllPackageIds());
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
    public void addDelivery() throws SQLException{
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
                Integer.parseInt(packageIdComboBox.getSelectionModel().getSelectedItem().toString()),
                postmanId,
                localDate);
        System.out.println(query);
        DatabaseConnection.executeUpdateQuery(query);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Delivery has been created;");

        alert.showAndWait();
    }

    public int getId(String firstName, String lastName, String who) throws SQLException{
        ResultSet r = DatabaseConnection.getSingleRowQueryResult(String.format("Select id from %s where nume='%s' and prenume='%s'",
                who,
                firstName,
                lastName));
        return r.getInt("Id");
    }
}
