package databaseHandler;

import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    public int id;
    public String username;
    public int accessLevel;
    public String imageUrl;

    public User(int id, String username, int accessLevel, String imageUrl) {
        this.id = id;
        this.username = username;
        this.accessLevel = accessLevel;
        this.imageUrl = imageUrl;
    }

    public Image getImage() throws Exception {
        Image i = new Image(imageUrl, false);
        return i;
    }

    public static User getUser(String username) {
        try {
            ResultSet getUserQuery = DatabaseConnection.getQueryResult(String.format("Select * from users where Username = '%s'", username));
            return new User(
                    getUserQuery.getInt("Id"),
                    username,
                    getUserQuery.getInt("AccessLevel"),
                    getUserQuery.getString("ImageUrl")
            );
        } catch (SQLException e) {
            return null;
        }
    }
}
