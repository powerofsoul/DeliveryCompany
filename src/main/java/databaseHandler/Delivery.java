package databaseHandler;

import javafx.geometry.Pos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import static databaseHandler.DatabaseConnection.getQueryResult;
import static databaseHandler.Sender.*;

public class Delivery {
    private int id;

    private int senderId;

    private Sender sender() throws SQLException{
        return Sender.getSender(id);
    }

    private int receiverId;
    private Receiver receiver() throws SQLException{
        return Receiver.getReceiver(id);
    }

    private int packageId;
    private Package aPackage() throws SQLException{
        return Package.getPackage(id);
    }

    private int postmanId;
    private Postman postman() throws SQLException{
        return Postman.getPostman(id);
    }

    private Date deliveryDate;
    private Date dispatchedDate;

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
        ResultSet rs = getQueryResult(String.format("Select * from livrare where id=%d", id));

        return new Delivery(id,
                rs.getInt("IdExpeditor"),
                rs.getInt("IdDestinatar"),
                rs.getInt("IdColet"),
                rs.getInt("IdCurier"),
                rs.getDate("DataExpediere"),
                rs.getDate("DataRidicare")
        );
    }
}
