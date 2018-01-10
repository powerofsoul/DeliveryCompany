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
    public int getId(){
        return id;
    }

    private int senderId;
    public String getSenderName() throws SQLException{
        return Sender.getSender(id).toString();
    }

    private int receiverId;
    public String getReceiverName() throws SQLException{
        return Receiver.getReceiver(id).toString();
    }

    private int packageId;
    public Package getPackage() throws SQLException{
        return Package.getPackage(id);
    }

    private int postmanId;
    public String getPostmanName() throws SQLException{
        return Postman.getPostman(id).toString();
    }

    private Date deliveryDate;
    public Date getDeliveryDate(){
        return deliveryDate;
    }

    private Date dispatchedDate;
    public Date getDispatchedDate(){
        return dispatchedDate;
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
