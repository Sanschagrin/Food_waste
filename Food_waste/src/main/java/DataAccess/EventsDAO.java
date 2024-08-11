package DataAccess;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ggreg
 */
public interface EventsDAO {

    /**
     *
     * @return List EventsDTO
     * @throws SQLException
     */
    List<EventsDTO> getAllEvents() throws SQLException;

    /**
     *
     * @param event_id
     * @return EventsDTO
     * @throws SQLException
     */
    EventsDTO getEventById(int event_id) throws SQLException;

    /**
     *
     * @param event
     * @throws SQLException
     */
    void addEvent(EventsDTO event) throws SQLException;

    /**
     *
     * @param event
     * @throws SQLException
     */
    void updateEvent(EventsDTO event) throws SQLException;

    /**
     *
     * @param event
     * @throws SQLException
     */
    void deleteEvent(EventsDTO event) throws SQLException;
}
