/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DataAccess;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ggreg
 */
public interface EventsDAO {
    List<EventsDTO> getAllEvents() throws SQLException;
    EventsDTO getEventById(int event_id) throws SQLException;
    void addEvent(EventsDTO event) throws SQLException;
    void updateEvent(EventsDTO event) throws SQLException;
    void deleteEvent(EventsDTO event) throws SQLException;
}
