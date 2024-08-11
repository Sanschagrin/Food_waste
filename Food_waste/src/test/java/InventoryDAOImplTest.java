
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import DataAccess.InventoryDAOImpl;
import DataAccess.InventoryDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryDAOImplTest {

    private Connection connection;
    private InventoryDAOImpl inventoryDAO;

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
            stmt.execute("DROP TABLE IF EXISTS inventory");

            // Create the table with AUTO_INCREMENT for the primary key
            stmt.execute("CREATE TABLE inventory ("
                    + "item_id INT AUTO_INCREMENT PRIMARY KEY, "
                    + "retailer_id INT, "
                    + "item_name VARCHAR(255), "
                    + "item_description VARCHAR(255), "
                    + "price DOUBLE, "
                    + "expiry_date DATE, "
                    + "quantity INT)");

            // Insert initial data
            stmt.execute("INSERT INTO inventory (retailer_id, item_name, item_description, price, expiry_date, quantity) "
                    + "VALUES (101, 'Item1', 'Description1', 10.0, '2024-12-31', 100)");
        }

        // Initialize DAO with the connection
        inventoryDAO = new InventoryDAOImpl(connection);
    }

    /**
     *
     * @throws SQLException
     */
    @Test
    public void testGetAllItems() throws SQLException {
        List<InventoryDTO> items = inventoryDAO.getAllItems();
        assertNotNull(items);
        assertEquals(1, items.size());

        InventoryDTO item = items.get(0);
        assertEquals(1, item.getItemId()); // ID may differ if AUTO_INCREMENT is used
        assertEquals(101, item.getRetailerId());
        assertEquals("Item1", item.getItemName());
        assertEquals("Description1", item.getItemDescription());
        assertEquals(10.0, item.getPrice());
        assertEquals(Date.valueOf("2024-12-31"), item.getExpiryDate());
        assertEquals(100, item.getQuantity());

        System.out.println("Inventory : Getting all items Passed");
    }

    /**
     *
     * @throws SQLException
     */
    @Test
    public void testGetItemById() throws SQLException {
        InventoryDTO item = inventoryDAO.getItemById(1);
        assertNotNull(item);
        assertEquals(1, item.getItemId()); // ID may differ if AUTO_INCREMENT is used
        assertEquals("Item1", item.getItemName());
        System.out.println("Inventory : Get item by ID Passed");
    }

    /**
     *
     * @throws SQLException
     */
    @Test
    public void testAddItem() throws SQLException {
        InventoryDTO newItem = new InventoryDTO(0, 102, "NewItem", "NewDescription", 20.0, Date.valueOf("2025-01-01"), 50); // Use 0 for auto-generated ID

        inventoryDAO.addItem(newItem);

        // Fetch the ID of the newly added item
        InventoryDTO result = inventoryDAO.getItemById(2); // Adjust if needed based on actual ID generated
        assertNotNull(result);
        assertEquals("NewItem", result.getItemName());
        System.out.println("Inventory : Adding item Passed");
    }

    /**
     *
     * @throws SQLException
     */
    @Test
    public void testUpdateItem() throws SQLException {
        InventoryDTO updatedItem = new InventoryDTO(1, 101, "UpdatedItem", "UpdatedDescription", 30.0, Date.valueOf("2025-02-01"), 75);

        inventoryDAO.updateItem(updatedItem);

        InventoryDTO result = inventoryDAO.getItemById(1);
        assertNotNull(result);
        assertEquals("UpdatedItem", result.getItemName());
        System.out.println("Inventory : Updating item Passed");
    }

    /**
     *
     * @throws SQLException
     */
    @Test
    public void testDeleteItem() throws SQLException {
        inventoryDAO.deleteItem(1);

        InventoryDTO result = inventoryDAO.getItemById(1);
        assertNull(result);
        System.out.println("Inventory : Deleting item Passed");
    }

    /**
     *
     * @throws SQLException
     */
    @Test
    public void testGetItemsByRetailerId() throws SQLException {
        List<InventoryDTO> items = inventoryDAO.getItemsByRetailerId(101);
        assertNotNull(items);
        assertEquals(1, items.size());

        InventoryDTO item = items.get(0);
        assertEquals(1, item.getItemId()); // ID may differ if AUTO_INCREMENT is used
        assertEquals("Item1", item.getItemName());

        System.out.println("Inventory : Getting items by retailer ID Passed");
    }
}
