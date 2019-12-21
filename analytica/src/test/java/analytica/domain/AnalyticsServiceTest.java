package analytica.domain;

import analytica.service.EventService;
import analytica.service.AnalyticsService;
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
        this.createModel();
    }
    
    public void createModel() {    
        this.eventService.createEvent(new Event("Test1", 10.0, 50, 42, 8, 2, 48));
        this.eventService.createEvent(new Event("Test2", 15.0, 70, 61, 9, 3, 67));
        this.eventService.createEvent(new Event("Test3", 30.0, 80, 69, 11, 2, 88));
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
    public void listsWithEqualLengthsReturnsTrue() {
        List<Double> list1 = new ArrayList<>();
        List<Double> list2 = new ArrayList<>();
        list1.add(10.0);
        list1.add(15.0);
        list2.add(20.0);
        list2.add(30.0);
        assertTrue(analyticsService.equalLengths(list1, list2));
    }
    
    @Test
    public void listsWithNotEqualLengthsReturnsFalse() {
            List<Double> list1 = new ArrayList<>();
            List<Double> list2 = new ArrayList<>();
            list1.add(10.0);            
            list2.add(20.0);
            list2.add(30.0);
            assertFalse(analyticsService.equalLengths(list1, list2));
    }
    
    @Test
    public void numberOfEventsReturnsCorrectNumber() {        
        assertEquals(3, this.analyticsService.getNumberOfEvents());
    }
    
    @Test
    public void regressionModelForPricesAndParticipantsWorksAsIntended() {        
        Regression model = this.analyticsService.createRegressionModelForPricesAndParticipants();
        double[][] data = {{10.0, 50}, {15.0, 70}, {30.0, 80}};        
        assertTrue(Arrays.deepEquals(data, model.getValuePairs()));
    }
    
    @Test
    public void regressionModelForParticipantsAndPricesWorksAsIntended() {        
        Regression model = this.analyticsService.createRegressionModelForParticipantsAndPrices();
        double[][] data = {{50, 10.0}, {70, 15.0}, {80, 30.0}};                
        assertTrue(Arrays.deepEquals(data, model.getValuePairs()));
    }
    
    @Test
    public void regressionModelForOpenedAndNotOpenedWorksAsIntended() {        
        Regression model = this.analyticsService.createRegressionModelForOpenedAndNotOpened();
        double[][] data = {{42, 8}, {61, 9}, {69, 11}};                
        assertTrue(Arrays.deepEquals(data, model.getValuePairs()));
    }
    
    @Test
    public void regressionModelForMalesAndFemalesWorksAsIntended() {        
        Regression model = this.analyticsService.createRegressionModelForMalesAndFemales();
        double[][] data = {{2, 48}, {3, 67}, {2, 88}};                
        assertTrue(Arrays.deepEquals(data, model.getValuePairs()));
    }
    
    @Test
    public void totalParticipantsIsCorrect() {             
        assertEquals(200, this.analyticsService.getTotalParticipants());
    }
    
    @Test
    public void averageParticipantsIsCorrect() {
        assertEquals(67.0, this.analyticsService.getAverageParticipants(), 0.0f);
    }
    
    @Test
    public void averagePriceIsCorrect() {
        assertEquals(18.0, this.analyticsService.getAveragePrice(), 0.0f);
    }
    
    @Test
    public void totalRevenueIsCorrect() {        
        assertEquals(3950.0, this.analyticsService.getTotalRevenue(), 0.0f);
    }        
    
    @Test
    public void averageRevenueIsCorrect() {
        assertEquals(1317.0, this.analyticsService.getAverageRevenue(), 0.0f);
    }
    
    @Test
    public void medianRevenueIsCorrect() {        
        assertEquals(1050.0, this.analyticsService.getMedianRevenue(), 0.0f);
    }                
    
    @Test
    public void totalOpenedIsCorrect() {        
        assertEquals(172, this.analyticsService.getTotalOpened());
    }        
    
    @Test
    public void openedRateIsCorrect() {        
        assertEquals(86.0, this.analyticsService.getOpenedRate(), 0.0f);
    }
    
    @Test
    public void notOpenedRateIsCorrect() {        
        assertEquals(14.0, this.analyticsService.getNotOpenedRate(), 0.0f);
    }
    
    @Test
    public void malesRateIsCorrect() {        
        assertEquals(3.0, this.analyticsService.getMalesRate(), 0.0f);
    }
    
    @Test
    public void femalesRateIsCorrect() {
        assertEquals(97.0, this.analyticsService.getFemalesRate(), 0.0f);
    }
    
    @Test
    public void correlationBetweenParticipantsAndOpenedAccountsIsCorrect() {
        assertEquals(0.9992, this.analyticsService.getCorrelationBetweenParticipantsAndOpenedAccounts(), 0.0f);
    }
    
    @Test
    public void predictPriceByParticipantsProvidesAccuratePrediction() {
        assertEquals(17.0, this.analyticsService.predictPriceByParticipants(65), 0.0f);
    }
    
    @Test
    public void predictParticipantsByPriceProvidesAccuratePrediction() {
        assertEquals(59.0, this.analyticsService.predictParticipantsByPrice(12.5), 0.0f);
    }

    @Test
    public void predictRevenueByParticipantsProvidesAccuratePrediction() {
        assertEquals(961.0, this.analyticsService.predictRevenueByParticipants(62), 0.0f);
    }
    
    @Test
    public void predictRevenueByPriceProvidesAccuratePrediction() {
        assertEquals(854.0, this.analyticsService.predictRevenueByPrice(14.0), 0.0f);
    }

    
}
