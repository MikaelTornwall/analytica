package analytica.domain;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mikael Törnwall
 */

public class AnalyticsServiceTest {            
    
    private EventService eventService;
    private AnalyticsService analyticsService;    
    
    @Before
    public void setUp() {
        this.eventService = new EventService(new FakeSQLEventDao());
        this.analyticsService = new AnalyticsService(eventService);
    }
    
    @Test
    public void createValuePairsCreatesCorrectValuePairs() {
        List<Double> list1 = new ArrayList<>();
        List<Double> list2 = new ArrayList<>();
        double[][] array = new double[10][2];
        
        for (int i = 0; i < 10; i++) {
            list1.add(i + 1.0);
            list2.add(i + 1.0);
            array[i][0] = i + 1.0;
            array[i][1] = i + 1.0;
        }
        
        double[][] result = this.analyticsService.createValuePairs(list1, list2);        
        
        assertTrue(Arrays.deepEquals(array, result));
    }
    
    @Test
    public void totalRevenueIsCorrectWithNoEvents() {
           assertEquals(0.0, this.analyticsService.getTotalRevenue(), 0.0f);
    }
    
    @Test
    public void totalRevenueIsCorrect() {
        this.eventService.createEvent(new Event("Test1", 10, 10, 0, 0, 0, 0));
        this.eventService.createEvent(new Event("Test2", 20, 10, 0, 0, 0, 0));
        this.eventService.createEvent(new Event("Test3", 30, 10, 0, 0, 0, 0));
        assertEquals(600.0, this.analyticsService.getTotalRevenue(), 0.0f);
    }
    
    @Test
    public void openedRateIsCorrect() {
        this.eventService.createEvent(new Event("Test1", 10.0, 10, 5, 5, 0, 0));
        this.eventService.createEvent(new Event("Test2", 20.0, 10, 5, 5, 0, 0));
        this.eventService.createEvent(new Event("Test3", 30.0, 10, 0, 10, 0, 0));
        assertEquals(0.3333333333333333, this.analyticsService.getOpenedRate(), 0.0f);
    }

}
