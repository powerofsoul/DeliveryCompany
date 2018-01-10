package databaseHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

import static databaseHandler.DatabaseConnection.getQueryResult;

public class Person {
    public enum PersonType {
        Sender, Receiver
    }

    public PersonType personType;
    public int id;
    public String firstName;
    public String lastName;
    public String address;


    public Person(int id, String firstName, String lastName, String address, PersonType personType) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.personType = personType;
    }

    public static Sender getPerson(int id, PersonType personType) throws SQLException {
        String table = personType == PersonType.Sender ? "expeditor" : "destinatar";

        ResultSet rs = getQueryResult(String.format("Select * from %s where id=%d", table, id));

        return new Sender(id,
                rs.getString("Nume"),
                rs.getString("Prenume"),
                rs.getString("Adresa"));
    }
}
