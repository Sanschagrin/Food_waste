package DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ggreg
 */
public class EventsDAOImpl implements EventsDAO {

    private static Connection connection;

    /**
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public EventsDAOImpl() throws SQLException, ClassNotFoundException {
        this.connection = DBConnection.getConnection();
    }

    private static final String all = "SELECT * FROM Events";
    private static final String byID = "SELECT * FROM Events WHERE event_id = ?";
    private static final String insert = "INSERT INTO Events (event_name, event_description, event_attendees) VALUES (?, ?, ?)";
    private static final String update = "UPDATE Events SET event_name = ?, event_description = ?, event_attendees = ? WHERE charitable_id = ?";
    private static final String delete = "DELETE FROM Events WHERE event_id = ?";

    /**
     *
     * @return List<EventsDTO>
     * @throws SQLException
     */
    @Override
    public List<EventsDTO> getAllEvents() throws SQLException {
        List<EventsDTO> events = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(all)) {
            ResultSet results = preparedStatement.executeQuery();
            while (results.next()) {
                events.add(new EventsDTO(results.getInt("event_id"), results.getString("event_name"), results.getString("event_description"), results.getInt("event_attendees")));
            }
        }
        return events;
    }

    /**
     *
     * @param event_id
     * @return EventsDTO
     * @throws SQLException
     */
    @Override
    public EventsDTO getEventById(int event_id) throws SQLException {
        EventsDTO events = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(byID)) {
            preparedStatement.setInt(1, event_id);
            try (ResultSet results = preparedStatement.executeQuery()) {
                if (results.next()) {
                    return new EventsDTO(results.getInt("event_id"), results.getString("event_name"), results.getString("event_description"), results.getInt("event_attendees"));
                }
            }
        }
        return events;
    }

    /**
     *
     * @param event
     * @throws SQLException
     */
    @Override
    public void addEvent(EventsDTO event) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(insert)) {
            preparedStatement.setString(1, event.getEventName());
            preparedStatement.setString(2, event.getEventDescription());
            preparedStatement.setInt(3, event.getEventAttendees());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Added event: " + event);
            } else {
                System.out.println("Failed to add event: " + event);
            }
        }
    }

    /**
     *
     * @param event
     * @throws SQLException
     */
    @Override
    public void updateEvent(EventsDTO event) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(update)) {
            preparedStatement.setString(1, event.getEventName());
            preparedStatement.setString(2, event.getEventDescription());
            preparedStatement.setInt(4, event.getEventAttendees());
            preparedStatement.setInt(5, event.getEventId());
            preparedStatement.executeUpdate();
        }
    }

    /**
     *
     * @param event
     * @throws SQLException
     */
    @Override
    public void deleteEvent(EventsDTO event) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(delete)) {
            preparedStatement.setInt(1, event.getEventId());
            preparedStatement.executeUpdate();
        }
    }

}
