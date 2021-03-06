package databaseHandler;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;

import javax.sound.midi.Soundbank;
import java.lang.reflect.Executable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.*;
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
        return Sender.getSender(senderId).toString();
    }

    private int receiverId;

    public String getReceiverName() throws SQLException {
        return Receiver.getReceiver(receiverId).toString();
    }

    private int packageId;

    public int getPackageId() throws SQLException {
        return Package.getPackage(packageId).id;
    }

    private int postmanId;

    public String getPostmanName() throws SQLException {
        return Postman.getPostman(postmanId).firstName + " " + Postman.getPostman(postmanId).lastName;
    }

    private Date deliveryDate;

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    private Date dispatchedDate;
    private DatePicker dispatchedDatePicker = new DatePicker();
    public DatePicker getDispatchedDate() {
        try {
            int a = Integer.parseInt(dispatchedDate.toString().split("-")[0]);
            int b = Integer.parseInt(dispatchedDate.toString().split("-")[1]);
            int c = Integer.parseInt(dispatchedDate.toString().split("-")[2]);
            dispatchedDatePicker.setValue(LocalDate.of(a, b, c));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return dispatchedDatePicker;
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

    public Button getUpdateDateButton(){
        Button b = new Button("Update");
        b.setMaxWidth(Double.MAX_VALUE);
        b.setOnAction(e -> {
            try {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Dialog");
                alert.setContentText("Are you want to update the date?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    updateDate(e);
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        });

        return b;
    }

    private void delete(ActionEvent e) throws SQLException {
        DatabaseConnection.executeUpdateQuery(String.format("Delete From livrare where id=%d", id));
        int index=0;
        for(int i=0;i<deliveryList.size();i++){
            if(deliveryList.get(i).id == id)
                index = i;
        }

        deliveryList.remove(index);
        deliveryTableView.getItems().remove(index);
    }

    private void updateDate(ActionEvent e) throws SQLException{
        try {
            String s = String.format("Update livrare set DataRidicare='%s' where id=%d ", dispatchedDatePicker.getValue(), id);
            DatabaseConnection.executeUpdateQuery(String.format("Update livrare set DataRidicare='%s' where id=%d ", dispatchedDatePicker.getValue(), id));
        }
        catch (Exception d){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("EREOR");
            a.setContentText("Be sure");
        }
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Done");
        a.setContentText("Successful");

        a.showAndWait();
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
        deliveryList.clear();
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
