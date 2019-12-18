package analytica.domain;

import java.util.List;

/**
 *
 * @author Mikael Törnwall
 */

public class AnalyticsService {
    
    private EventService eventService;   
    private Statistics statistics;
    private Regression regression;
    
    public AnalyticsService(EventService eventService) {
        this.eventService = eventService;        
        this.statistics = new Statistics();
        this.regression = new Regression();
    }                
    
    public double getTotalRevenue() {        
        List<Event> events = this.eventService.getEvents();
        
        return 0.0;
    }
    
}
