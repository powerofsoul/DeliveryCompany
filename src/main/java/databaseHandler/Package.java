package databaseHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

import static databaseHandler.DatabaseConnection.getSingleRowQueryResult;

public class Package {
    public int id;
    public double length;
    public double width;
    public double height;
    public double weight;

    public String type;

    public Package(int id, double length, double width, double height, double weight, String type) {
        this.id = id;
        this.length = length;
        this.width = width;
        this.height = height;
        this.weight = weight;
        this.type = type;
    }

    public static Package getPackage(int id) throws SQLException{
        ResultSet rs = getSingleRowQueryResult(String.format("Select * from colet where id=%d", id));

        return new Package(id,
                rs.getDouble("Lungime"),
                rs.getDouble("Latime"),
                rs.getDouble("Inaltime"),
                rs.getDouble("Greutate"),
                rs.getString("Tip"));
    }
}
