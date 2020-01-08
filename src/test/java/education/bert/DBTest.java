package education.bert;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DBTest {
    private Connection connection;
    private String url = "jdbc:postgresql://localhost:5432/travis_ci_test";
    private String user = "postgres";
    private String password = "";

    @BeforeEach
    public void setup() throws SQLException {
        connection = DriverManager.getConnection(url, user, password);
        assertNotNull(connection);

        try (Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS accounts");
            statement.execute("CREATE TABLE accounts (" +
                    "  id SERIAL PRIMARY KEY," +
                    "  name TEXT NOT NULL" +
                    ");");
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO accounts(id, name) VALUES (?, ?)")) {
            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, "Vasya");
            preparedStatement.execute();

            preparedStatement.setInt(1, 2);
            preparedStatement.setString(2, "Petya");
            preparedStatement.execute();
        }
    }

    @AfterEach
    public void teardown() throws SQLException {
        connection.close();
    }

    @Test
    public void selectTest() throws SQLException {
        List<Integer> actualId = new ArrayList<>();
        List<String> actualName = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            statement.executeQuery("SELECT id, name FROM accounts");
            try (ResultSet resultSet = statement.getResultSet()) {
                while (resultSet.next()) {
                    actualId.add(resultSet.getInt("id"));
                    actualName.add(resultSet.getString("name"));
                }
            }
        }

        assertEquals(1, actualId.get(0));
        assertEquals(2, actualId.get(1));
        assertEquals("Vasya", actualName.get(0));
        assertEquals("Petya", actualName.get(1));
    }
}