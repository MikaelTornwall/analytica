package analytica.service;

import analytica.domain.Event;
import analytica.service.EventService;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mikael Törnwall
 */

public class EventServiceTest {
    
    private EventService service;
            
    @Before
    public void setUp() {
        this.service = new EventService(new FakeSQLEventDao());
    }
    
    @Test
    public void eventCanBeCreated() {
        Event event = new Event("Test", 0.0, 0, 0, 0, 0, 0);
        this.service.createEvent(event);
        assertTrue(this.service.eventExists(event));
    }            
    
    @Test
    public void ifEventExistsEventExistsReturnsTrue() {
        Event event = new Event("Test", 0.0, 0, 0, 0, 0, 0);
        this.service.createEvent(event);
        assertTrue(this.service.eventExists(event));
    }
    
    @Test
    public void ifEventDoesNotEventExistsReturnsFalse() {
        Event event = new Event("Test", 0.0, 0, 0, 0, 0, 0);
        assertFalse(this.service.eventExists(event));
    }
    
    @Test
    public void getEventsReturnsEmptyListWhenNoEventsHaveBeenAdded() {
        assertTrue(this.service.getEvents().isEmpty());
    }
    
    @Test
    public void getEventsReturnsAListOfEventsOfTheRigthSize() {
        Event event1 = new Event("Test1", 0.0, 0, 0, 0, 0, 0);
        Event event2 = new Event("Test2", 0.0, 0, 0, 0, 0, 0);
        Event event3 = new Event("Test3", 0.0, 0, 0, 0, 0, 0);
        this.service.createEvent(event1);
        this.service.createEvent(event2);
        this.service.createEvent(event3);
        
        assertEquals(3, this.service.getEvents().size());
    }
    
    @Test
    public void getParticipantsReturnsAListOfTheRightSize() {
        Event event1 = new Event("Test1", 0.0, 0, 0, 0, 0, 0);
        Event event2 = new Event("Test2", 0.0, 0, 0, 0, 0, 0);
        Event event3 = new Event("Test3", 0.0, 0, 0, 0, 0, 0);
        this.service.createEvent(event1);
        this.service.createEvent(event2);
        this.service.createEvent(event3);
        
        assertEquals(3, this.service.getParticipantsList().size());
    }
    
    @Test
    public void getPricesReturnsAListOfTheRightSize() {
        Event event1 = new Event("Test1", 0.0, 0, 0, 0, 0, 0);
        Event event2 = new Event("Test2", 0.0, 0, 0, 0, 0, 0);
        Event event3 = new Event("Test3", 0.0, 0, 0, 0, 0, 0);
        this.service.createEvent(event1);
        this.service.createEvent(event2);
        this.service.createEvent(event3);
        
        assertEquals(3, this.service.getPricesList().size());
    }
    
    @Test
    public void getOpenedReturnsAListOfTheRightSize() {
        Event event1 = new Event("Test1", 0.0, 0, 0, 0, 0, 0);
        Event event2 = new Event("Test2", 0.0, 0, 0, 0, 0, 0);
        Event event3 = new Event("Test3", 0.0, 0, 0, 0, 0, 0);
        this.service.createEvent(event1);
        this.service.createEvent(event2);
        this.service.createEvent(event3);
        
        assertEquals(3, this.service.getOpenedList().size());
    }
    
    @Test
    public void getNotOpenedReturnsAListOfTheRightSize() {
        Event event1 = new Event("Test1", 0.0, 0, 0, 0, 0, 0);
        Event event2 = new Event("Test2", 0.0, 0, 0, 0, 0, 0);
        Event event3 = new Event("Test3", 0.0, 0, 0, 0, 0, 0);
        this.service.createEvent(event1);
        this.service.createEvent(event2);
        this.service.createEvent(event3);
        
        assertEquals(3, this.service.getNotOpenedList().size());
    }
    
}
