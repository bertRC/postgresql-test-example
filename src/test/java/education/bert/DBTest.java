package education.bert;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DBTest {
    @Test
    public void dbConnectionTest() throws SQLException {
        Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://192.168.99.100:5432/travis_ci_test", "postgres", "");

        assertNotNull(conn);
    }
}
