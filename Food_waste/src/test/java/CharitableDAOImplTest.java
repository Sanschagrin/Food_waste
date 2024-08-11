import DataAccess.CharitableDAOImpl;
import DataAccess.CharitableDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CharitableDAOImplTest {

    private Connection connection;
    private CharitableDAOImpl charitableDAO;

    @BeforeEach
    public void setUp() throws SQLException {
        // Set up the in-memory database or use an actual database for tests
        connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "sa", "");

        // Initialize the database schema and data
        try (Statement stmt = connection.createStatement()) {
            // Drop table if it exists to avoid conflicts
            stmt.execute("DROP TABLE IF EXISTS charitable");
            
            // Create the table with AUTO_INCREMENT for the primary key
            stmt.execute("CREATE TABLE charitable (charitable_id INT AUTO_INCREMENT PRIMARY KEY, charitable_name VARCHAR(255), charitable_email VARCHAR(255), charitable_password VARCHAR(255), charitable_description VARCHAR(255))");
            
            // Insert initial data
            stmt.execute("INSERT INTO charitable (charitable_name, charitable_email, charitable_password, charitable_description) VALUES ('Test Charity', 'test@example.com', 'password123', 'Test Description')");
        }

        // Initialize DAO with the connection
        charitableDAO = new CharitableDAOImpl(connection);
    }

    @Test
    public void testGetAllCharitable() throws SQLException {
        List<CharitableDTO> charitables = charitableDAO.getAllCharitable();
        assertNotNull(charitables);
        assertEquals(1, charitables.size());

        CharitableDTO charitable = charitables.get(0);
        assertEquals(1, charitable.getCharitableId()); // ID may differ if AUTO_INCREMENT is used
        assertEquals("Test Charity", charitable.getCharitableName());
        assertEquals("test@example.com", charitable.getCharitableEmail());
        assertEquals("password123", charitable.getCharitablePassword());
        assertEquals("Test Description", charitable.getCharitableDescription());
        
        System.out.println("Charitable : Getting all charitables Pass");
    }

    @Test
    public void testGetCharitableById() throws SQLException {
        CharitableDTO charitable = charitableDAO.getCharitableById(1);
        assertNotNull(charitable);
        assertEquals(1, charitable.getCharitableId()); // ID may differ if AUTO_INCREMENT is used
        assertEquals("Test Charity", charitable.getCharitableName());
        System.out.println("Charitable : Test to get Id passsed");
    }

    @Test
    public void testAddCharitable() throws SQLException {
        CharitableDTO charitable = new CharitableDTO(0, "New Charity", "new@example.com", "newpassword", "New Description"); // Use 0 for auto-generated ID

        charitableDAO.addCharitable(charitable);

        // Fetch the ID of the newly added charitable
        CharitableDTO result = charitableDAO.getCharitableById(2); // Adjust if needed based on actual ID generated
        assertNotNull(result);
        assertEquals("New Charity", result.getCharitableName());
        System.out.println("Charitable : Adding charitable passed");
    }

    @Test
    public void testUpdateCharitable() throws SQLException {
        CharitableDTO charitable = new CharitableDTO(1, "Updated Charity", "updated@example.com", "newpassword", "Updated Description");

        charitableDAO.updateCharitable(charitable);

        CharitableDTO result = charitableDAO.getCharitableById(1);
        assertNotNull(result);
        assertEquals("Updated Charity", result.getCharitableName());
        System.out.println("Charitable : Updating Charitable passed");
    }

    @Test
    public void testDeleteCharitable() throws SQLException {
        CharitableDTO charitable = new CharitableDTO(1, "Test Charity", "test@example.com", "password123", "Test Description");

        charitableDAO.deleteCharitable(charitable);

        CharitableDTO result = charitableDAO.getCharitableById(1);
        assertNull(result);
        System.out.println("Charitable : Delete charitable passed");
    }

}
