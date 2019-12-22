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
        this.initializeDatabase();
    }        
    
    private void initializeDatabase() {
        Event event1 = new Event("Test", 10.0, 65, 60, 5, 1, 64); 
        Event event2 = new Event("Test2", 20.0, 90, 83, 7, 3, 87); 
        Event event3 = new Event("Test3", 30.0, 70, 62, 8, 2, 68); 
        this.eventDao.create(event1);
        this.eventDao.create(event2);
        this.eventDao.create(event3);
    }
        
    @Test
    public void eventCanBeCreated() {
        Event event = new Event("Test", 30.0, 10, 10, 10, 10, 10); 
        this.eventDao.create(event);
        assertEquals(event, eventDao.getAll().get(3));
    }
    
    @Test
    public void eventCanBeFetchedByName() {
        Event event = new Event("Example", 30.0, 10, 10, 10, 10, 10); 
        this.eventDao.create(event);
        assertEquals(event, eventDao.getByName("Example"));
    }        
    
    @Test
    public void databaseCanBeCleared() {                
        this.database.clearDatabase();
        assertEquals(0, eventDao.getAll().size());
    }
    
    @Test
    public void getEventsReturnsListOfCorrectSize() {        
        assertEquals(3, eventDao.getAll().size());
    }        
    
    @Test
    public void getParticipantsListReturnsListOfCorrectSize() {        
        assertEquals(3, eventDao.getParticipantsList().size());
    }
    
    @Test
    public void getParticipantsListReturnsListCorrectSumOfParticipants() {                
        List<Integer> participants = eventDao.getParticipantsList();
        int sum = 0;
        
        for (Integer value : participants) {
            sum += value;
        }
        
        assertEquals(225, sum);
    }
    
    @Test
    public void getPricesListReturnsListOfCorrectSize() {        
        assertEquals(3, eventDao.getPricesList().size());
    }
    
    @Test
    public void getPricesListReturnsListCorrectSumOfParticipants() {
        List<Double> prices = eventDao.getPricesList();
        int sum = 0;
        
        for (Double value : prices) {
            sum += value;
        }
        
        assertEquals(60.0, sum, 0.0f);
    }
    
    @Test
    public void getOpenedListReturnsListOfCorrectSize() {        
        assertEquals(3, eventDao.getOpenedList().size());
    }
    
    @Test
    public void getOpenedListReturnsListCorrectSumOfParticipants() {                
        List<Integer> opened = eventDao.getOpenedList();
        int sum = 0;
        
        for (Integer value : opened) {
            sum += value;
        }
        
        assertEquals(205, sum);
    }
    
    @Test
    public void getNotOpenedListReturnsListOfCorrectSize() {        
        assertEquals(3, eventDao.getNotOpenedList().size());
    }
    
    @Test
    public void getNotOpenedListReturnsListCorrectSumOfParticipants() {                
        List<Integer> notopened = eventDao.getNotOpenedList();
        int sum = 0;
        
        for (Integer value : notopened) {
            sum += value;
        }
        
        assertEquals(20, sum);
    }
    
    @Test
    public void getMalesListReturnsListOfCorrectSize() {        
        assertEquals(3, eventDao.getMalesList().size());
    }
    
    @Test
    public void getMalesListReturnsListCorrectSumOfParticipants() {                
        List<Integer> males = eventDao.getMalesList();
        int sum = 0;
        
        for (Integer value : males) {
            sum += value;
        }
        
        assertEquals(6, sum);
    }
    
    @Test
    public void getFemalesListReturnsListOfCorrectSize() {        
        assertEquals(3, eventDao.getFemalesList().size());
    }
    
    @Test
    public void getFemalesListReturnsListCorrectSumOfParticipants() {                
        List<Integer> females = eventDao.getFemalesList();
        int sum = 0;
        
        for (Integer value : females) {
            sum += value;
        }
        
        assertEquals(219, sum);
    }
}
