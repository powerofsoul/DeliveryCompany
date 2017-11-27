package DatabaseHandler;

import java.sql.*;

public class DatabaseConnection {
    private Connection connection;
    private String DB = "firmacurierat";
    private String UserName = "root";
    private String Password = "";

    public void connect() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        String connectionUrl = String.format("jdbc:mysql://localhost:3306/%s?useUnicode=true&characterEncoding=UTF-8&user=%s&password=%s",
                DB, UserName, Password);
        connection = DriverManager.getConnection(connectionUrl);
    }

    public boolean validCredentials(String username, String password) throws SQLException {
        String checkCredentialQuery = String.format("select COUNT(*) as Total from users where username='%s' and password='%s'", username, password);
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(checkCredentialQuery);
        //Get first line?
        result.next();
        return result.getInt("Total") == 1;
    }

    public boolean validCredentials(Credentials credentials) throws SQLException {
        return validCredentials(credentials.userName, credentials.password);
    }
}
