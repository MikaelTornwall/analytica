/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analytica.dao;

import java.util.List;
import analytica.db.SQLDatabase;
import analytica.domain.Event;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mikael Törnwall
 */

public class SQLEventDaoTest {
    
    private SQLDatabase database;
    private SQLEventDao eventDao;
    
    public SQLEventDaoTest() {        
        this.database = new SQLDatabase("jdbc:h2:./analytica_test_db");
        this.eventDao = new SQLEventDao(database);
    }        
    
    @Before
    public void setUp() {        
        this.database.clearDatabase();
    }        
        
    @Test
    public void eventCanBeCreated() {
        Event event = new Event("Test", 30.0, 10, 10, 10, 10, 10); 
        this.eventDao.create(event);
        assertEquals(event, eventDao.getAll().get(0));
    }
    
    @Test
    public void eventCanBeFetchedByName() {
        Event event = new Event("Test", 30.0, 10, 10, 10, 10, 10); 
        this.eventDao.create(event);
        assertEquals(event, eventDao.getByName("Test"));
    }
    
    @Test
    public void eventCanBeDeleted() {
        Event event = new Event("Test", 30.0, 10, 10, 10, 10, 10); 
        this.eventDao.create(event);
        this.eventDao.delete(event.getName());
        assertEquals(0, eventDao.getAll().size());
    }
    
    @Test
    public void databaseCanBeCleared() {
        Event event = new Event("Test", 30.0, 10, 10, 10, 10, 10); 
        this.eventDao.create(event);
        this.database.clearDatabase();
        assertEquals(0, eventDao.getAll().size());
    }
    
    @Test
    public void getEventsReturnsListOfCorrectSize() {
        Event event1 = new Event("Test", 30.0, 10, 10, 10, 10, 10); 
        Event event2 = new Event("Test2", 30.0, 10, 10, 10, 10, 10); 
        Event event3 = new Event("Test3", 30.0, 10, 10, 10, 10, 10); 
        this.eventDao.create(event1);
        this.eventDao.create(event2);
        this.eventDao.create(event3);
        assertEquals(3, eventDao.getAll().size());
    }        
    
    @Test
    public void getParticipantsListReturnsListOfCorrectSize() {
        Event event1 = new Event("Test", 30.0, 10, 10, 10, 10, 10); 
        Event event2 = new Event("Test2", 30.0, 10, 10, 10, 10, 10); 
        Event event3 = new Event("Test3", 30.0, 10, 10, 10, 10, 10); 
        this.eventDao.create(event1);
        this.eventDao.create(event2);
        this.eventDao.create(event3);
        assertEquals(3, eventDao.getParticipantsList().size());
    }
    
    @Test
    public void getParticipantsListReturnsListCorrectSumOfParticipants() {
        Event event1 = new Event("Test", 30.0, 10, 10, 0, 0, 0); 
        Event event2 = new Event("Test2", 30.0, 20, 0, 0, 0, 0); 
        Event event3 = new Event("Test3", 30.0, 30, 0, 0, 0, 0); 
        this.eventDao.create(event1);
        this.eventDao.create(event2);
        this.eventDao.create(event3);
        
        List<Integer> participants = eventDao.getParticipantsList();
        int sum = 0;
        
        for (Integer value : participants) {
            sum += value;
        }
        
        assertEquals(60, sum);
    }
    
    @Test
    public void getPricesListReturnsListOfCorrectSize() {
        Event event1 = new Event("Test", 30.0, 10, 10, 10, 10, 10); 
        Event event2 = new Event("Test2", 30.0, 10, 10, 10, 10, 10); 
        Event event3 = new Event("Test3", 30.0, 10, 10, 10, 10, 10); 
        this.eventDao.create(event1);
        this.eventDao.create(event2);
        this.eventDao.create(event3);
        assertEquals(3, eventDao.getPricesList().size());
    }
    
    @Test
    public void getPricesListReturnsListCorrectSumOfParticipants() {
        Event event1 = new Event("Test", 10.0, 10, 10, 10, 10, 10); 
        Event event2 = new Event("Test2", 10.0, 10, 10, 10, 10, 10); 
        Event event3 = new Event("Test3", 10.0, 10, 10, 10, 10, 10); 
        this.eventDao.create(event1);
        this.eventDao.create(event2);
        this.eventDao.create(event3);
        
        List<Double> prices = eventDao.getPricesList();
        int sum = 0;
        
        for (Double value : prices) {
            sum += value;
        }
        
        assertEquals(30.0, sum, 0.0f);
    }
    
    @Test
    public void getOpenedListReturnsListOfCorrectSize() {
        Event event1 = new Event("Test", 30.0, 10, 10, 10, 10, 10); 
        Event event2 = new Event("Test2", 30.0, 10, 10, 10, 10, 10); 
        Event event3 = new Event("Test3", 30.0, 10, 10, 10, 10, 10); 
        this.eventDao.create(event1);
        this.eventDao.create(event2);
        this.eventDao.create(event3);
        assertEquals(3, eventDao.getParticipantsList().size());
    }
    
    @Test
    public void getOpenedListReturnsListCorrectSumOfParticipants() {
        Event event1 = new Event("Test", 30.0, 30, 20, 0, 0, 0); 
        Event event2 = new Event("Test2", 30.0, 10, 10, 0, 0, 0); 
        Event event3 = new Event("Test3", 30.0, 10, 10, 0, 0, 0); 
        this.eventDao.create(event1);
        this.eventDao.create(event2);
        this.eventDao.create(event3);
        
        List<Integer> opened = eventDao.getOpenedList();
        int sum = 0;
        
        for (Integer value : opened) {
            sum += value;
        }
        
        assertEquals(40, sum);
    }
}
