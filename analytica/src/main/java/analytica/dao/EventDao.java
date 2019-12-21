package analytica.dao;

import analytica.domain.Event;
import java.util.List;

/**
 * Interface for Event dao
 * 
 * @author Mikael Törnwall
 */

public interface EventDao {
    public void create(Event event);    
    public Event getByName(String name);    
    public List<Event> getAll();    
    public List<Integer> getParticipantsList();
    public List<Double> getPricesList();
    public List<Integer> getOpenedList();
    public List<Integer> getNotOpenedList();
    public List<Integer> getMalesList();
    public List<Integer> getFemalesList();
}
