/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analytica.domain;


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
    public void eventCanBeDeleted() {
        Event event = new Event("Test", 0.0, 0, 0, 0, 0, 0);
        this.service.createEvent(event);
        this.service.deleteEvent(event);
        assertFalse(this.service.eventExists(event));
    }
    
    
}
