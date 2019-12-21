package analytica.service;

import java.util.List;
import java.util.ArrayList;
import analytica.dao.EventDao;
import analytica.domain.Event;

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
     * Method checks if an event exists
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
     * Method returns a list of all events
     * 
     * @return events as a List object
     */
    
    public List<Event> getEvents() {
        return this.events;
    }
    
    /**
     * Method returns the number of participants in each event
     * 
     * @return number of participants in each event as a List object
     */
    
    public List<Double> getParticipantsList() {        
        List<Integer> participants = this.eventDao.getParticipantsList();
        List<Double> list = new ArrayList<>();
        
        for (Integer value : participants) {
            list.add((double) value);
        }
        
        return list;
    }
    
    /**
     * Method returns prices of each event
     * 
     * @return price of each event as a List object
     */
    
    public List<Double> getPricesList() {                        
        return this.eventDao.getPricesList();
    }
    
    /**
     * Method returns the number of opened accounts in each event
     * 
     * @return number opened accounts in each event as a List object
     */
    
    public List<Double> getOpenedList() {         
        List<Integer> opened = this.eventDao.getOpenedList();
        List<Double> list = new ArrayList<>(); 
        
        for (Integer value : opened) {
            list.add((double) value);
        }
        
        return list;
    }
    
    /**
     * Method returns the number of opened accounts not opened in each event
     * 
     * @return number opened accounts not opened in each event as a List object
     */
    
    public List<Double> getNotOpenedList() {         
        List<Integer> notOpened = this.eventDao.getNotOpenedList();
        List<Double> list = new ArrayList<>(); 
        
        for (Integer value : notOpened) {
            list.add((double) value);
        }
        
        return list;
    }
    
    /**
     * Method returns the number of male participants in each event
     * 
     * @return number male participants in each event as a List object
     */
    
    public List<Double> getMalesList() {         
        List<Integer> males = this.eventDao.getMalesList();
        List<Double> list = new ArrayList<>(); 
        
        for (Integer value : males) {
            list.add((double) value);
        }
        
        return list;
    }
    
    /**
     * Method returns the number of female participants in each event
     * 
     * @return number female participants in each event as a List object
     */
    
    public List<Double> getFemalesList() {         
        List<Integer> females = this.eventDao.getFemalesList();
        List<Double> list = new ArrayList<>(); 
        
        for (Integer value : females) {
            list.add((double) value);
        }
        
        return list;
    }
    
    /**
     * Method returns the list of all events from the database
     * 
     * @return events as a List object
     */
    
    private List<Event> getEventsFromDatabase() {
        return this.eventDao.getAll();
    }        
}
