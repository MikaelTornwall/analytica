/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analytica.dao;

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
        assertEquals(event, eventDao.getByName("Test"));
    }
    
    @Test
    public void databaseCanBeCleared() {
        Event event = new Event("Test", 30.0, 10, 10, 10, 10, 10); 
        this.eventDao.create(event);
        this.database.clearDatabase();
        assertEquals(0, eventDao.getAll().size());
    }
    
    @Test
    public void getEventsReturnsListOfEvents() {
        Event event1 = new Event("Test", 30.0, 10, 10, 10, 10, 10); 
        Event event2 = new Event("Test2", 30.0, 10, 10, 10, 10, 10); 
        Event event3 = new Event("Test3", 30.0, 10, 10, 10, 10, 10); 
        this.eventDao.create(event1);
        this.eventDao.create(event2);
        this.eventDao.create(event3);
        assertEquals(3, eventDao.getAll().size());
    }
}
