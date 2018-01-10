package databaseHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

import static databaseHandler.DatabaseConnection.getSingleRowQueryResult;

public class Postman {
    public int id;
    public String firstName;
    public String lastName;

    public Postman(int id, String firstName, String lastName){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static Postman getPostman(int id) throws SQLException {
        ResultSet rs = getSingleRowQueryResult(String.format("Select * from curier where id=%d", id));

        return new Postman(id,
                rs.getString("Nume"),
                rs.getString("Prenume"));
    }
}
