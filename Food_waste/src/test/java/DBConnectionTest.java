import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import DataAccess.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

public class DBConnectionTest {

    @Test
    public void testGetConnection() {
        try {
            // Attempt to get the database connection
            Connection connection = DBConnection.getConnection();
            // Verify that the connection is not null
            assertNotNull(connection, "Connection should not be null");
        } catch (SQLException | ClassNotFoundException e) {
            // If there is an exception, the test should fail
            e.printStackTrace();
            fail("Exception occurred while getting the connection: " + e.getMessage());
        }
    }
}
