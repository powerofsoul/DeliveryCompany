package databaseHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static databaseHandler.DatabaseConnection.getQueryResult;
import static databaseHandler.DatabaseConnection.getSingleRowQueryResult;

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
        ResultSet rs = getSingleRowQueryResult(String.format("Select * from livrare where id=%d", id));
        return deliveryQueryResultToDelivery(id, rs);
    }

    public static List<Delivery> getAllDeliveries() throws SQLException{
        List<Delivery> deliveries = new ArrayList<Delivery>();

        ResultSet queryResult = getQueryResult("Select * from livrare");
        int i=1;
        while(queryResult.next()){
            deliveries.add(deliveryQueryResultToDelivery(i++, queryResult));
        }

        return deliveries;
    }

    private static Delivery deliveryQueryResultToDelivery(int id, ResultSet queryResult) throws SQLException{
        return new Delivery(id,
                queryResult.getInt("IdExpeditor"),
                queryResult.getInt("IdDestinatar"),
                queryResult.getInt("IdColet"),
                queryResult.getInt("IdCurier"),
                queryResult.getDate("DataExpediere"),
                queryResult.getDate("DataRidicare")
        );
    }
}
