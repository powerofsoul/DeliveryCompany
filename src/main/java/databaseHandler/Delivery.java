package databaseHandler;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;

import javax.sound.midi.Soundbank;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class Delivery {
    public static TableView deliveryTableView = new TableView();
    public static ObservableList<Delivery> deliveryList = FXCollections.observableArrayList();

    private int id;

    public int getId() {
        return id;
    }

    private int senderId;

    public String getSenderName() throws SQLException {
        return Sender.getSender(id).toString();
    }

    private int receiverId;

    public String getReceiverName() throws SQLException {
        return Receiver.getReceiver(id).toString();
    }

    private int packageId;

    public Package getPackage() throws SQLException {
        return Package.getPackage(id);
    }

    private int postmanId;

    public String getPostmanName() throws SQLException {
        return Postman.getPostman(id).toString();
    }

    private Date deliveryDate;

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    private Date dispatchedDate;

    public Date getDispatchedDate() {
        return dispatchedDate;
    }

    public Button getDeleteButton() {
        Button b = new Button("Delete");
        b.setMaxWidth(Double.MAX_VALUE);
        b.setOnAction(e -> {
            try {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Dialog");
                alert.setContentText("Are you want to delete this?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    delete(e);
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        });

        return b;
    }

    private void delete(ActionEvent e) throws SQLException {
        DatabaseConnection.executeUpdateQuery(String.format("Delete From livrare where id=%d", id));
        deliveryList.remove(id);
        deliveryTableView.getItems().remove(id);
    }

    public Delivery(int id, int senderId, int receiverId, int packageId, int postmanId, Date deliveryDate, Date dispatchedDate) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.packageId = packageId;
        this.postmanId = postmanId;
        this.deliveryDate = deliveryDate;
        this.dispatchedDate = dispatchedDate;
    }

    public static Delivery getDelivery(int id) throws SQLException {
        ResultSet rs = DatabaseConnection.getSingleRowQueryResult(String.format("Select * from livrare where id=%d", id));
        return deliveryQueryResultToDelivery(rs);
    }

    public static void getAllDeliveries() throws SQLException {
        List<Delivery> deliveries = new ArrayList<Delivery>();
        ResultSet queryResult = DatabaseConnection.getQueryResult("Select * from livrare");
        while (queryResult.next()) {
            deliveries.add(deliveryQueryResultToDelivery(queryResult));
        }
        deliveryList.addAll(deliveries);
    }

    private static Delivery deliveryQueryResultToDelivery(ResultSet queryResult) throws SQLException {
        return new Delivery(queryResult.getInt("id"),
                queryResult.getInt("IdExpeditor"),
                queryResult.getInt("IdDestinatar"),
                queryResult.getInt("IdColet"),
                queryResult.getInt("IdCurier"),
                queryResult.getDate("DataExpediere"),
                queryResult.getDate("DataRidicare")
        );
    }
}
