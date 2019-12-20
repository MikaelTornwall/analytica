package analytica.domain;

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
    
    public Integer getNumberOfEvents() {
        return this.eventService.getEvents().size();
    }
    
    public double getTotalRevenue() {            
        return this.createRegressionModelForPricesAndParticipants().getProductOfValuePairs();
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
        return (double) Math.round(statistics.getMean());
    }        
        
    public Double getAveragePrice() {
        Statistics statistics = new Statistics();
        statistics.addValues(this.eventService.getPricesList());
        return (double) Math.round(statistics.getMean());
    }
    
    public Regression createRegressionModelForOpenedAndNotOpened() {
        Regression regression = new Regression();
        List<Double> opened = this.eventService.getOpenedList();
        List<Double> notOpened = this.eventService.getNotOpenedList();
        
        regression.addData(this.createValuePairs(opened, notOpened));
        
        return regression;
    }
    
    public double getOpenedRate() {                
        return Math.round(createRegressionModelForOpenedAndNotOpened().getXRate() * 1000) / 10;
    }        
    
    public double getNotOpenedRate() {
        return Math.round(createRegressionModelForOpenedAndNotOpened().getYRate() * 1000) / 10;
    }
    
    public Regression createRegressionModelForMalesAndFemales() {
        Regression regression = new Regression();
        List<Double> males = this.eventService.getMalesList();
        List<Double> females = this.eventService.getFemalesList();
        
        regression.addData(this.createValuePairs(males, females));
    
        
        return regression;
    }
    
    public double getMalesRate() {            
        return Math.round(createRegressionModelForMalesAndFemales().getXRate() * 1000) / 10;
    }
    
    public double getFemalesRate() {        
        return Math.round(createRegressionModelForMalesAndFemales().getYRate() * 1000) / 10;
    }    
    
    public double getAverageRevenue() {                
        return this.createRegressionModelForPricesAndParticipants().getMeanOfProductOfValuePairs();
    }
    
    public double getMedianRevenue() {        
        return this.createRegressionModelForPricesAndParticipants().getMedianOfProductOfValuePairs();        
    }
    
    public double getModeRevenue() {
        return this.createRegressionModelForPricesAndParticipants().getModeOfProductOfValuePairs();        
    }
    
    public double getCorrelationBetweenParticipantsAndOpenedAccounts() {
        return this.createRegressionModelForPricesAndParticipants().getCorrelation();
    }        
    
    public double predictPriceByParticipants(Integer x) {
        return this.createRegressionModelForParticipantsAndPrices().predict(x);
    }
    
    public double predictParticipantsByPrice(Double x) {
        return this.createRegressionModelForPricesAndParticipants().predict(x);        
    }
    
    public double predictRevenueByParticipants(Integer x) {        
        return this.createRegressionModelForParticipantsAndPrices().predict(x) * x;
    }
    
    public double predictRevenueByPrice(Double x) {        
        return this.createRegressionModelForPricesAndParticipants().predict(x) * x;        
    }
}
