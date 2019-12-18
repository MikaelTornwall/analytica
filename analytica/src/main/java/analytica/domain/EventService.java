package analytica.domain;

import java.util.List;
import java.util.ArrayList;
import analytica.dao.EventDao;

/**
 *
 * @author Mikael Törnwall
 */

public class EventService {
    
    private EventDao eventDao;
    private List<Event> events;
    
    public EventService(EventDao eventDao) {
        this.eventDao = eventDao;
        this.events = this.getEventsFromDatabase();
    }                    
    
    /**
     * Method check is an event exists
     * 
     * @param event is an Event object
     * @return true if event exists in the database, false otherwise
     */
    
    public boolean eventExists(Event event) {
        if (this.eventDao.getByName(event.getName()) == null) {
            return false;
        }
        
        return true;
    }        
    
    /**
     * Methods creates a new event and updates events list
     * 
     * @param event as an Event object
     * @return true if event name is not taken and operation is successful, false otherwise
     */
    
    public boolean createEvent(Event event) {        
        if (eventExists(event)) {
            return false;
        }
        
        this.eventDao.create(event);
        
        this.events = this.getEventsFromDatabase();
        
        return true;        
    }
    
    /**
     * Method deletes an event that matches and updates the events list
     * 
     * @param event as an Event object
     */
    
    public void deleteEvent(Event event) {
        this.eventDao.delete(event.getName());        
        this.events = this.getEventsFromDatabase();
    }
    
    /**
     * Method returns the list of all events
     * 
     * @return events as a List object
     */
    
    public List<Event> getEvents() {
        return this.events;
    }
    
    public List<Integer> getParticipantsList() {        
        return null;
    }
    
    /**
     * Method returns the list of all events from the database
     * 
     * @return events as a List object
     */
    
    public List<Event> getEventsFromDatabase() {
        return this.eventDao.getAll();
    }        
}
