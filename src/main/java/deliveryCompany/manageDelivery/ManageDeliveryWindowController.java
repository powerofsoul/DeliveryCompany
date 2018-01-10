package deliveryCompany.manageDelivery;

import databaseHandler.Delivery;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ManageDeliveryWindowController {
    @FXML
    private TableView deliveryTableView;

    @FXML
    private void initialize() throws SQLException {
        Delivery.deliveryTableView = deliveryTableView;
        Delivery.getAllDeliveries();
        deliveryTableView.getColumns().addAll(
                generateColumn("Id","id"),
                generateColumn("Sender","senderName"),
                generateColumn("Receiver","receiverName"),
                generateColumn("Postman","postmanName"),
                generateColumn("Delivery Date","deliveryDate"),
                generateColumn("Dispatched Date","dispatchedDate"),
                generateColumn("Delete Delivery","deleteButton")
        );
        deliveryTableView.getItems().clear();
        deliveryTableView.getItems().addAll(Delivery.deliveryList);
    }

    private TableColumn generateColumn(String identifier, String variableName){
        TableColumn column = new TableColumn(identifier);
        column.setCellValueFactory(new PropertyValueFactory<>(variableName));

        return column;
    }
}
