package databaseHandler;

import java.sql.*;

public class DatabaseConnection {
    private static Connection connection;
    private static String DB = "firmacurierat";
    private static String UserName = "root";
    private static String Password = "";

    public static void connect() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        String connectionUrl = String.format("jdbc:mysql://localhost:3306/%s?useUnicode=true&characterEncoding=UTF-8&user=%s&password=%s",
                DB, UserName, Password);
        connection = DriverManager.getConnection(connectionUrl);
    }

    public static boolean validCredentials(String username, String password) throws SQLException {
        ResultSet result = getSingleRowQueryResult(String.format("select COUNT(*) as Total from users where username='%s' and password='%s'", username, password));
        return result.getInt("Total") == 1;
    }

    public static boolean validCredentials(Credentials credentials) throws SQLException {
        return validCredentials(credentials.userName, credentials.password);
    }

    public static ResultSet getSingleRowQueryResult(String query) throws SQLException {
        ResultSet result = getQueryResult(query);
        result.next();
        return result;
    }

    public static ResultSet getQueryResult(String query) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(query);

        return result;
    }
}
