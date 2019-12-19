package analytica.domain;

import java.util.List;
import java.util.ArrayList;
import analytica.dao.EventDao;

/**
 *
 * @author Mikael Törnwall
 */

public class FakeSQLEventDao implements EventDao {
    
    private List<Event> events;
    
    public FakeSQLEventDao() {
        this.events = new ArrayList<>();
    }
    
    public void create(Event event) {
        if (this.events.contains(event)) {
            return;
        }
        this.events.add(event);
    }
    
    public Event getByName(String name) {                
        for (Event e : this.events) {
            if (e.getName().equals(name)) {
                return e;
            }
        }
        
        return null;
    }
    
    public void delete(String name) {
        this.events.removeIf(event -> event.getName().equals(name));
    }
    
    public List<Event> getAll() {
        return this.events;
    }
    
    public List<Integer> getParticipantsList() {
        List<Integer> participants = new ArrayList<>();
        
        for (Event e : this.events) {
            participants.add(e.getParticipants());
        }
        
        return participants;
    }
    
    public List<Double> getPricesList() {
        List<Double> prices = new ArrayList<>();
        
        for (Event e : this.events) {
            prices.add(e.getPrice());
        }
        
        return prices;
    }
    
    public List<Integer> getOpenedList() {
        List<Integer> opened = new ArrayList<>();
        
        for (Event e : this.events) {
            opened.add(e.getOpenedAccount());
        }
        
        return opened;
    }
    
    public List<Integer> getNotOpenedList() {
        List<Integer> notOpened = new ArrayList<>();
        
        for (Event e : this.events) {
            notOpened.add(e.getDidNotOpenAccount());
        }
        
        return notOpened;
    }
    
    public List<Integer> getMalesList() {
        List<Integer> males = new ArrayList<>();
        
        for (Event e : this.events) {
            males.add(e.getMales());
        }
        
        return males;
    }
    
    public List<Integer> getFemalesList() {
        List<Integer> females = new ArrayList<>();
        
        for (Event e : this.events) {
            females.add(e.getFemales());
        }
        
        return females;
    }
    
}
