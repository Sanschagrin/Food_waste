/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccess;

/**
 *
 * @author ggreg
 */
public class EventsDTO {
    private int event_id;
    private String event_name;
    private String event_description;
    private int event_attendees;
    
    public EventsDTO(int event_id, String event_name, String event_description, int event_attendees){
    this.event_id = event_id;
    this.event_name = event_name;
    this.event_description = event_description;
    this.event_attendees = event_attendees;
    }
    
    public int getEventId(){
        return event_id;
    }
    public void setEventId(int event_id){
        this.event_id = event_id;
    }
    public String getEventName(){
        return event_name;
    }
    public void setEventName(String event_name){
        this.event_name = event_name;
    }
    public String getEventDescription(){
        return event_description;
    }
    public void setEventDescription(String event_description){
        this.event_description = event_description;
    }
    public int getEventAttendees(){
        return event_attendees;
    }
    public void setEventAttendees(int event_attendees){
        this.event_attendees = event_attendees;
    }
    
}
