package databaseHandler;

import java.sql.SQLException;

public class Sender extends Person {
    public Sender(int id, String firstName, String lastName, String address) {
        super(id, firstName, lastName, address, PersonType.Sender);
    }

    public static Sender getSender(int id) throws SQLException{
        Person p = getPerson(id, PersonType.Sender);

        return new Sender(p.id, p.firstName, p.lastName, p.address);
    }
}
