package analytica.service;

import analytica.domain.Regression;
import analytica.domain.Statistics;
import java.util.List;

/**
 *
 * @author Mikael Törnwall
 */

public class AnalyticsService {
    
    private final EventService eventService;          
    
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
    
    public Regression createRegressionModelForPricesAndParticipants() {
        Regression regression = new Regression();
        List<Double> prices = this.eventService.getPricesList();
        List<Double> participants = this.eventService.getParticipantsList();
        
        regression.addData(this.createValuePairs(prices, participants));
        
        return regression;
    }
    
    public Regression createRegressionModelForParticipantsAndPrices() {
        Regression regression = new Regression();
        List<Double> participants = this.eventService.getParticipantsList();
        List<Double> prices = this.eventService.getPricesList();
        
        regression.addData(this.createValuePairs(participants, prices));
        
        return regression;
    }
    
    public Regression createRegressionModelForParticipantsAndOpenedAccounts() {
        Regression regression = new Regression();
        List<Double> participants = this.eventService.getParticipantsList();
        List<Double> opened = this.eventService.getOpenedList();
        
        regression.addData(this.createValuePairs(participants, opened));
        
        return regression;
    }
    
    public Regression createRegressionModelForOpenedAndNotOpened() {
        Regression regression = new Regression();
        List<Double> opened = this.eventService.getOpenedList();
        List<Double> notOpened = this.eventService.getNotOpenedList();
        
        regression.addData(this.createValuePairs(opened, notOpened));
        
        return regression;
    }
    
    public Regression createRegressionModelForMalesAndFemales() {
        Regression regression = new Regression();
        List<Double> males = this.eventService.getMalesList();
        List<Double> females = this.eventService.getFemalesList();
        
        regression.addData(this.createValuePairs(males, females));
    
        
        return regression;
    }
    
    public int getNumberOfEvents() {
        return this.eventService.getEvents().size();
    }
    
    public double getTotalRevenue() {            
        return this.createRegressionModelForPricesAndParticipants().getProductOfValuePairs();
    }
     
    public int getTotalParticipants() {
        Statistics statistics = new Statistics();
        statistics.addValues(this.eventService.getParticipantsList());
        return (int) statistics.getSum();
    }
    
    public int getTotalOpened() {
        Statistics statistics = new Statistics();
        statistics.addValues(this.eventService.getOpenedList());
        return (int) statistics.getSum();
    }        
    
    public double getAverageParticipants() {
        Statistics statistics = new Statistics();
        statistics.addValues(this.eventService.getParticipantsList());
        return (double) Math.round(statistics.getMean());
    }        
        
    public double getAveragePrice() {
        Statistics statistics = new Statistics();
        statistics.addValues(this.eventService.getPricesList());
        return (double) Math.round(statistics.getMean());
    }        
    
    public double getOpenedRate() {                
        return Math.round(createRegressionModelForOpenedAndNotOpened().getXRate() * 100);
    }        
    
    public double getNotOpenedRate() {
        return Math.round(createRegressionModelForOpenedAndNotOpened().getYRate() * 100);
    }        
    
    public double getMalesRate() {            
        return Math.round(createRegressionModelForMalesAndFemales().getXRate() * 100);
    }
    
    public double getFemalesRate() {        
        return Math.round(createRegressionModelForMalesAndFemales().getYRate() * 100);
    }    
    
    public double getAverageRevenue() {                
        return Math.round(this.createRegressionModelForPricesAndParticipants().getMeanOfProductOfValuePairs());
    }
    
    public double getMedianRevenue() {        
        return Math.round(this.createRegressionModelForPricesAndParticipants().getMedianOfProductOfValuePairs());        
    }        
    
    public double getCorrelationBetweenParticipantsAndOpenedAccounts() {
        double correlation = this.createRegressionModelForParticipantsAndOpenedAccounts().getCorrelation() * 10000;
        correlation = Math.round(correlation);
        return correlation / 10000;
    }        
    
    public double predictPriceByParticipants(Integer x) {
        return Math.round(this.createRegressionModelForParticipantsAndPrices().predict(x));
    }
    
    public double predictParticipantsByPrice(Double x) {
        return Math.round(this.createRegressionModelForPricesAndParticipants().predict(x));        
    }
    
    public double predictRevenueByParticipants(Integer x) {        
        return Math.round(this.createRegressionModelForParticipantsAndPrices().predict(x) * x);
    }
    
    public double predictRevenueByPrice(Double x) {        
        return Math.round(this.createRegressionModelForPricesAndParticipants().predict(x) * x);        
    }
}
