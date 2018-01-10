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
        List<Delivery> deliveryList = Delivery.getAllDeliveries();
        FXCollections.observableArrayList(deliveryList);

        deliveryTableView.getColumns().addAll(
                generateColumn("Id","id"),
                generateColumn("Sender","senderName"),
                generateColumn("Receiver","receiverName"),
                generateColumn("Postman","postmanName"),
                generateColumn("Delivery Date","deliveryDate"),
                generateColumn("Dispatched Date","dispatchedDate")
        );

        deliveryTableView.getItems().addAll(deliveryList);
    }

    private TableColumn generateColumn(String identifier, String variableName){
        TableColumn column = new TableColumn(identifier);
        column.setCellValueFactory(new PropertyValueFactory<>(variableName));
        return column;
    }
}
