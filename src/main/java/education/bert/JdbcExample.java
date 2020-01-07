package education.bert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcExample {
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(
//                "jdbc:postgresql://192.168.99.100:32768/postgres", "postgres", "")) {
                "jdbc:postgresql://192.168.99.100:5432/postgres", "postgres", "")) {

            if (conn != null) {
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Failed to make connection!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
