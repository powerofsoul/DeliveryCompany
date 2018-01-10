package databaseHandler;

import java.sql.SQLException;

public class Receiver extends Person {
    public Receiver(int id, String firstName, String lastName, String address) {
        super(id, firstName, lastName, address, PersonType.Sender);
    }

    public static Receiver getReceiver(int id) throws SQLException {
        Person p = getPerson(id, PersonType.Receiver);

        return new Receiver(p.id, p.firstName, p.lastName, p.address);
    }
}
