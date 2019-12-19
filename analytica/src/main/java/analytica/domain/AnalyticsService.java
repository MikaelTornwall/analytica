package analytica.domain;

import java.util.List;

/**
 *
 * @author Mikael Törnwall
 */

public class AnalyticsService {
    
    private EventService eventService;          
    
    public AnalyticsService(EventService eventService) {
        this.eventService = eventService;                
    }                
    
    public boolean equalLengths(List<Double> list1, List<Double> list2) {
        return list1.size() == list2.size();
    }
    
    public double[][] createValuePairs(List<Double> list1, List<Double> list2) {                
        if (!this.equalLengths(list1, list2)) {
            return new double[0][0];
        }
        
        double[][] array = new double[list1.size()][2];
        
        for (int i = 0; i < list1.size(); i++) {
            array[i][0] = list1.get(i);
            array[i][1] = list2.get(i);
        }
        
        return array;
    }
    
    public Integer getNumberOfEvents() {
        return this.eventService.getEvents().size();
    }
    
    public double getTotalRevenue() {    
        Regression regression = new Regression();
        List<Double> prices = this.eventService.getPricesList();
        List<Double> participants = this.eventService.getParticipantsList();
        regression.addData(this.createValuePairs(prices, participants));                        
        
        return regression.getProductOfValuePairs();
    }
     
    public Integer getTotalParticipants() {
        Statistics statistics = new Statistics();
        statistics.addValues(this.eventService.getParticipantsList());
        return (int) statistics.getSum();
    }
    
    public Integer getTotalOpened() {
        Statistics statistics = new Statistics();
        statistics.addValues(this.eventService.getOpenedList());
        return (int) statistics.getSum();
    }        
    
    public Double getAverageParticipants() {
        Statistics statistics = new Statistics();
        statistics.addValues(this.eventService.getParticipantsList());
        return statistics.getMean();
    }        
        
    public Double getAveragePrice() {
        Statistics statistics = new Statistics();
        statistics.addValues(this.eventService.getPricesList());
        return statistics.getMean();
    }
    
    public double getOpenedRate() {
        Regression regression = new Regression();
        List<Double> opened = this.eventService.getOpenedList();
        List<Double> notOpened = this.eventService.getNotOpenedList();
        
        regression.addData(this.createValuePairs(opened, notOpened));
        
        return Math.round(regression.getXRate() * 1000) / 10;
    }
    
    public double getNotOpenedRate() {
        return 100.0 - getOpenedRate();
    }
    
    public double getMalesRate() {
        Regression regression = new Regression();
        List<Double> males = this.eventService.getMalesList();
        List<Double> females = this.eventService.getFemalesList();
        
        regression.addData(this.createValuePairs(males, females));
        
        return Math.round(regression.getXRate() * 1000) / 10;
    }
    
    public double getFemalesRate() {
        return 100.0 - getMalesRate();
    }

    public double getAverageRevenue() {
        Regression regression = new Regression();
        List<Double> prices = this.eventService.getPricesList();
        List<Double> participants = this.eventService.getParticipantsList();
        
        regression.addData(this.createValuePairs(prices, participants));
        
        return regression.getMeanOfProductOfValuePairs();
    }
    
    public double getMedianRevenue() {
        Regression regression = new Regression();
        List<Double> prices = this.eventService.getPricesList();
        List<Double> participants = this.eventService.getParticipantsList();
        
        regression.addData(this.createValuePairs(prices, participants));
        
        return regression.getMedianOfProductOfValuePairs();        
    }
    
    public double getModeRevenue() {
        Regression regression = new Regression();
        List<Double> prices = this.eventService.getPricesList();
        List<Double> participants = this.eventService.getParticipantsList();
        
        regression.addData(this.createValuePairs(prices, participants));
        
        return regression.getModeOfProductOfValuePairs();        
    }
    
    public double getCorrelationBetweenParticipantsAndOpenedAccounts() {
        
        return 0.0;
    }        
    
    public double predictRevenueByParticipants() {
        return 0.0;
    }
    
    public double predictRevenueByPrice() {
        return 0.0;
    }
}
