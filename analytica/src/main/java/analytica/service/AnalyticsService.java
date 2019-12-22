package analytica.service;

import analytica.domain.Regression;
import analytica.domain.Statistics;
import java.util.List;

/**
 * AnalyticsService class
 * 
 * @author Mikael Törnwall
 */

public class AnalyticsService {
    
    private final EventService eventService;          
    
    public AnalyticsService(EventService eventService) {
        this.eventService = eventService;                
    }                
    
    /**
     * Method checks if two list objects are of equal size
     * 
     * @param list1 is a List object containing double values
     * @param list2 is a List object containing double values
     * @return true if list sizes are equal, otherwise false
     */       
    
    public boolean equalLengths(List<Double> list1, List<Double> list2) {
        return list1.size() == list2.size();
    }
    
    /**
     * Method creates an array of value pairs from the given lists
     * 
     * @param list1 is a List object containing double values
     * @param list2 is a List object containing double values
     * @return an array of n x 2 dimensions containing double values
     */       
    
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
    
    /**
     * Method generates a regression object for the use of class methods
     *      
     * @return Regression object containing prices as x values and participants as y values
     */       
    
    public Regression createRegressionModelForPricesAndParticipants() {
        Regression regression = new Regression();
        List<Double> prices = this.eventService.getPricesList();
        List<Double> participants = this.eventService.getParticipantsList();
        
        regression.addData(this.createValuePairs(prices, participants));
        
        return regression;
    }
    
    /**
     * Method generates a regression object for the use of class methods
     *      
     * @return Regression object containing participants as x values and prices as y values
     */
    
    public Regression createRegressionModelForParticipantsAndPrices() {
        Regression regression = new Regression();
        List<Double> participants = this.eventService.getParticipantsList();
        List<Double> prices = this.eventService.getPricesList();
        
        regression.addData(this.createValuePairs(participants, prices));
        
        return regression;
    }
    
    /**
     * Method generates a regression object for the use of class methods
     *      
     * @return Regression object containing participants as x values and opened accounts as y values
     */
    
    public Regression createRegressionModelForParticipantsAndOpenedAccounts() {
        Regression regression = new Regression();
        List<Double> participants = this.eventService.getParticipantsList();
        List<Double> opened = this.eventService.getOpenedList();
        
        regression.addData(this.createValuePairs(participants, opened));
        
        return regression;
    }
    
    /**
     * Method generates a regression object for the use of class methods
     *      
     * @return Regression object containing opened accounts as x values and not opened accounts as y values
     */
    
    public Regression createRegressionModelForOpenedAndNotOpened() {
        Regression regression = new Regression();
        List<Double> opened = this.eventService.getOpenedList();
        List<Double> notOpened = this.eventService.getNotOpenedList();
        
        regression.addData(this.createValuePairs(opened, notOpened));
        
        return regression;
    }
    
    /**
     * Method generates a regression object for the use of class methods
     *      
     * @return Regression object containing males as x values and females as y values
     */
    
    public Regression createRegressionModelForMalesAndFemales() {
        Regression regression = new Regression();
        List<Double> males = this.eventService.getMalesList();
        List<Double> females = this.eventService.getFemalesList();
        
        regression.addData(this.createValuePairs(males, females));
    
        
        return regression;
    }
    
    /**
     * Method fetches the number of events
     *      
     * @return int value
     */
    
    public int getNumberOfEvents() {
        return this.eventService.getEvents().size();
    }
    
    /**
     * Method uses the generated regression model for getting the total revenue
     *      
     * @return double value
     */
    
    public double getTotalRevenue() {            
        return this.createRegressionModelForPricesAndParticipants().getProductOfValuePairs();
    }
    
    /**
     * Method uses the generated regression model for getting total number of participants in events
     *      
     * @return int value
     */
    
    public int getTotalParticipants() {
        Statistics statistics = new Statistics();
        statistics.addValues(this.eventService.getParticipantsList());
        return (int) statistics.getSum();
    }
    
    /**
     * Method uses the generated regression model for getting the total number of opened accounts
     *      
     * @return int value
     */
    
    public int getTotalOpened() {
        Statistics statistics = new Statistics();
        statistics.addValues(this.eventService.getOpenedList());
        return (int) statistics.getSum();
    }        
    
    /**
     * Method uses the generated regression model for getting average number of participants per event
     *      
     * @return double value
     */
    
    public double getAverageParticipants() {
        Statistics statistics = new Statistics();
        statistics.addValues(this.eventService.getParticipantsList());
        return (double) Math.round(statistics.getMean());
    }        
    
    /**
     * Method uses the generated regression model for getting average price per event
     *      
     * @return double value
     */
    
    public double getAveragePrice() {
        Statistics statistics = new Statistics();
        statistics.addValues(this.eventService.getPricesList());
        return (double) Math.round(statistics.getMean());
    }        
    
    /**
     * Method uses the generated regression model for getting the rate of opened accounts and rounds it
     *      
     * @return double value
     */
    
    public double getOpenedRate() {                
        return Math.round(createRegressionModelForOpenedAndNotOpened().getXRate() * 100);
    }        
    
    /**
     * Method uses the generated regression model for getting the rate of not opened accounts and rounds it
     *      
     * @return double value
     */
    
    public double getNotOpenedRate() {
        return Math.round(createRegressionModelForOpenedAndNotOpened().getYRate() * 100);
    }        
    
    /**
     * Method uses the generated regression model for getting the rate of males and rounds it
     *      
     * @return double value
     */
    
    public double getMalesRate() {            
        return Math.round(createRegressionModelForMalesAndFemales().getXRate() * 100);
    }
    
    /**
     * Method uses the generated regression model for getting average revenue of events
     *      
     * @return double value
     */
    
    public double getAverageRevenue() {                
        return Math.round(this.createRegressionModelForPricesAndParticipants().getMeanOfProductOfValuePairs());
    }
    
    /**
     * Method uses the generated regression model for getting median revenue of events
     *      
     * @return double value
     */
    
    public double getMedianRevenue() {        
        return Math.round(this.createRegressionModelForPricesAndParticipants().getMedianOfProductOfValuePairs());        
    }        
    
    /**
     * Method uses the generated regression model for getting the rate of females and rounds it
     *      
     * @return double value
     */
    
    public double getFemalesRate() {        
        return Math.round(createRegressionModelForMalesAndFemales().getYRate() * 100);
    }            
    
    /**
     * Method uses the generated regression model for getting the correlation between participants and opened accounts
     *      
     * @return double value
     */
    
    public double getCorrelationBetweenParticipantsAndOpenedAccounts() {
        double correlation = this.createRegressionModelForParticipantsAndOpenedAccounts().getCorrelation() * 10000;
        correlation = Math.round(correlation);
        return correlation / 10000;
    }        
    
    /**
     * Method uses the generated regression model for getting the predicted price in terms of number of participants
     *      
     * @param x is a double value
     * @return double value
     */
    
    public double predictPriceByParticipants(Double x) {
        return Math.round(this.createRegressionModelForParticipantsAndPrices().predict(x));
    }
    
    /**
     * Method uses the generated regression model for getting the predicted number of participantsin terms of price
     *      
     * @param x is a double value
     * @return double value
     */
    
    public double predictParticipantsByPrice(Double x) {
        return Math.round(this.createRegressionModelForPricesAndParticipants().predict(x));        
    }
    
    /**
     * Method uses the generated regression model for getting the predicted revenue in terms of number of participants
     *      
     * @param x is a double value
     * @return double value
     */
    
    public double predictRevenueByParticipants(Double x) {        
        return Math.round(this.createRegressionModelForParticipantsAndPrices().predict(x) * x);
    }
    
    /**
     * Method uses the generated regression model for getting the predicted revenue in terms of price
     *     
     * @param x is a double value
     * @return double value
     */
    
    public double predictRevenueByPrice(Double x) {        
        return Math.round(this.createRegressionModelForPricesAndParticipants().predict(x) * x);        
    }
}
