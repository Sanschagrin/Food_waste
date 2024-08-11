
import DataAccess.ConsumerDAOImpl;
import DataAccess.ConsumerDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ConsumerDAOImplTest {

    private Connection connection;
    private ConsumerDAOImpl consumerDAO;

    /**
     *
     * @throws SQLException
     */
    @BeforeEach
    public void setUp() throws SQLException {
        // Set up the in-memory database
        connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "sa", "");

        // Initialize the database schema and data
        try (Statement stmt = connection.createStatement()) {
            // Drop table if it exists to avoid conflicts
            stmt.execute("DROP TABLE IF EXISTS Consumers");

            // Create the table with AUTO_INCREMENT for the primary key
            stmt.execute("CREATE TABLE Consumers (consumer_id INT AUTO_INCREMENT PRIMARY KEY, consumer_name VARCHAR(255), consumer_email VARCHAR(255), consumer_password VARCHAR(255), subscriber BOOLEAN)");

            // Insert initial data
            stmt.execute("INSERT INTO Consumers (consumer_name, consumer_email, consumer_password, subscriber) VALUES ('Test Consumer', 'test@example.com', 'password123', true)");
        }

        // Initialize DAO with the connection
        consumerDAO = new ConsumerDAOImpl(connection);
    }

    /**
     *
     * @throws SQLException
     */
    @Test
    public void testGetAllConsumers() throws SQLException {
        List<ConsumerDTO> consumers = consumerDAO.getAllConsumers();
        assertNotNull(consumers);
        assertEquals(1, consumers.size());

        ConsumerDTO consumer = consumers.get(0);
        assertEquals(1, consumer.getConsumerId()); // ID may differ if AUTO_INCREMENT is used
        assertEquals("Test Consumer", consumer.getConsumerName());
        assertEquals("test@example.com", consumer.getConsumerEmail());
        assertEquals("password123", consumer.getConsumerPassword());
        assertTrue(consumer.getSubscriber());

        System.out.println("Consumer : Getting all consumers passed");
    }

    /**
     *
     * @throws SQLException
     */
    @Test
    public void testGetConsumerById() throws SQLException {
        ConsumerDTO consumer = consumerDAO.getConsumerById(1);
        assertNotNull(consumer);
        assertEquals(1, consumer.getConsumerId()); // ID may differ if AUTO_INCREMENT is used
        assertEquals("Test Consumer", consumer.getConsumerName());
        System.out.println("Consumer : Test to get by ID passed");
    }

    /**
     *
     * @throws SQLException
     */
    @Test
    public void testAddConsumer() throws SQLException {
        ConsumerDTO consumer = new ConsumerDTO(0, "New Consumer", "new@example.com", "newpassword", false); // Use 0 for auto-generated ID

        consumerDAO.addConsumer(consumer);

        // Fetch the ID of the newly added consumer
        ConsumerDTO result = consumerDAO.getConsumerById(2); // Adjust if needed based on actual ID generated
        assertNotNull(result);
        assertEquals("New Consumer", result.getConsumerName());
        System.out.println("Consumer : Adding consumer passed");
    }

    /**
     *
     * @throws SQLException
     */
    @Test
    public void testDeleteConsumer() throws SQLException {
        ConsumerDTO consumer = new ConsumerDTO(1, "Test Consumer", "test@example.com", "password123", true);

        consumerDAO.deleteConsumer(consumer);

        ConsumerDTO result = consumerDAO.getConsumerById(1);
        assertNull(result);
        System.out.println("Consumer : Deleting consumer passed");
    }

}
