package analytica.dao;

import analytica.domain.Event;
import java.util.List;

/**
 *
 * @author Mikael Törnwall
 */

public interface EventDao {
    public void create(Event event);    
    public Event getByName(String name);
    public void delete(String name);
    public List<Event> getAll();    
}
