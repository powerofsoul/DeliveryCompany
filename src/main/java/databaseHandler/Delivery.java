package databaseHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import static databaseHandler.DatabaseConnection.getQueryResult;

public class Delivery {
    public int id;
    public int senderId;
    public int recipientId;
    public int packageId;
    public int postmanId;

    public Date deliveryDate;
    public Date dispatchedDate;

    public Delivery(int id, int senderId, int recipientId, int packageId, int postmanId, Date deliveryDate, Date dispatchedDate) {
        this.id = id;
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.packageId = packageId;
        this.postmanId = postmanId;
        this.deliveryDate = deliveryDate;
        this.dispatchedDate = dispatchedDate;
    }

    public static Delivery getDelivery(int id) throws SQLException{
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
