import DataAccess.EventsDAO;
import DataAccess.EventsDTO;
import DataAccess.EventsDAOImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EventsDAOImplTest {

    private Connection connection;
    private EventsDAO eventsDAO;

    @BeforeEach
    public void setUp() throws SQLException {
        // Set up the in-memory H2 database
        connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "sa", "");

        // Initialize the database schema and data
        try (Statement stmt = connection.createStatement()) {
            // Drop table if it exists to avoid conflicts
            stmt.execute("DROP TABLE IF EXISTS Events");

            // Create the table with AUTO_INCREMENT for the primary key
            stmt.execute("CREATE TABLE Events (event_id INT AUTO_INCREMENT PRIMARY KEY, event_name VARCHAR(255), event_description VARCHAR(255), event_attendees INT)");

            // Insert initial data
            stmt.execute("INSERT INTO Events (event_name, event_description, event_attendees) VALUES ('Event 1', 'Description 1', 100)");
            stmt.execute("INSERT INTO Events (event_name, event_description, event_attendees) VALUES ('Event 2', 'Description 2', 200)");
        }

        // Initialize DAO with the connection
        eventsDAO = new EventsDAOImpl(connection);
    }

    @Test
    public void testGetAllEvents() throws SQLException {
        List<EventsDTO> events = eventsDAO.getAllEvents();
        assertNotNull(events);
        assertEquals(2, events.size());

        assertEquals(1, events.get(0).getEventId());
        assertEquals("Event 1", events.get(0).getEventName());
        assertEquals("Description 1", events.get(0).getEventDescription());
        assertEquals(100, events.get(0).getEventAttendees());

        assertEquals(2, events.get(1).getEventId());
        assertEquals("Event 2", events.get(1).getEventName());
        assertEquals("Description 2", events.get(1).getEventDescription());
        assertEquals(200, events.get(1).getEventAttendees());

        System.out.println("Events: Getting all events passed");
    }

    @Test
    public void testGetEventById() throws SQLException {
        EventsDTO event = eventsDAO.getEventById(1);
        assertNotNull(event);
        assertEquals(1, event.getEventId());
        assertEquals("Event 1", event.getEventName());
        assertEquals("Description 1", event.getEventDescription());
        assertEquals(100, event.getEventAttendees());

        System.out.println("Events: Test to get event by ID passed");
    }

    @Test
    public void testAddEvent() throws SQLException {
        EventsDTO event = new EventsDTO(0, "New Event", "New Description", 300); // Use 0 for auto-generated ID

        eventsDAO.addEvent(event);

        // Fetch the ID of the newly added event
        EventsDTO result = eventsDAO.getEventById(3); // Adjust if needed based on actual ID generated
        assertNotNull(result);
        assertEquals("New Event", result.getEventName());
        assertEquals("New Description", result.getEventDescription());
        assertEquals(300, result.getEventAttendees());

        System.out.println("Events: Adding event passed");
    }

    @Test
    public void testDeleteEvent() throws SQLException {
        EventsDTO event = new EventsDTO(1, "Event 1", "Description 1", 100);

        eventsDAO.deleteEvent(event);

        EventsDTO result = eventsDAO.getEventById(1);
        assertNull(result);

        System.out.println("Events: Deleting event passed");
    }
}
