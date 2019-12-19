package analytica.ui;

import analytica.domain.AnalyticsService;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class Dashboard {
    private final int WIDTH;
    private final int HEIGHT;    
    private Parent dashboard;
    private AnalyticsService analyticsService;
    private Button predictByParticipantsButton;
    private Button predictByPriceButton;
    private TextField predictByParticipantsField;
    private TextField predictByPriceField;
    private Text predictionByParticipantsText;
    private Text predictionByPriceText;
    
    public Dashboard(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
        this.WIDTH = 700;
        this.HEIGHT = 500;                      
        this.predictByParticipantsButton = new Button("Predict");
        this.predictByPriceButton = new Button("Predict");
        this.predictByParticipantsField = new TextField();
        this.predictByPriceField = new TextField();
        this.predictionByParticipantsText = new Text("-");
        this.predictionByPriceText = new Text("-");
        this.dashboard = this.createDashboard();        
    }        
    
    public Parent getDashboard() {
        return this.dashboard;
    }        
    
    public Button getPredictParticipantsButton() {
        return this.predictByParticipantsButton;
    }
    
    public Button getPredictPriceButton() {
        return this.predictByPriceButton;
    }
    
    private Parent createDashboard() {        
        Text title = new Text("Dashboard");             
        title.setFont(Font.font(20));
        
        BorderPane layout = new BorderPane();
        layout.setTop(title);
        
        Text generalText = new Text("General");
        Text ratesText = new Text("Rates");
        Text revenueText = new Text("Revenue KPIs");
        Text correlationText = new Text("Correlation between participants and opened accounts");        
        Text predictText = new Text("Below you can predict future revenues by no. of participants or by price");
        
        GridPane pane = new GridPane();
        pane.setVgap(20);
        pane.setHgap(40);
        GridPane generalPane = new GridPane();
        generalPane.setVgap(5);
        generalPane.setHgap(5);
        GridPane ratesPane = new GridPane();
        ratesPane.setVgap(5);
        ratesPane.setHgap(5);
        GridPane revenuePane = new GridPane();
        revenuePane.setVgap(5);
        revenuePane.setHgap(5);
        GridPane correlationPane = new GridPane();
        correlationPane.setVgap(5);
        correlationPane.setHgap(5);
        GridPane predictPane = new GridPane();
        predictPane.setVgap(5);
        predictPane.setHgap(5);
                
        // General pane
        Text generalEvents = new Text("Number of events: ");
        Text generalRevenue = new Text("Total revenue (€): ");
        Text generalParticipants = new Text("Total no. of participants: ");
        Text generalOpened = new Text("Total no. of opened accounts: ");
        Text generalAverageParticipants = new Text("Average no. of participants: ");
        Text generalPrice = new Text("Average price per event: ");
        
        Text events = new Text(Integer.toString(analyticsService.getNumberOfEvents()));
        Text revenue = new Text(Double.toString(analyticsService.getTotalRevenue()));
        Text participants = new Text(Integer.toString(analyticsService.getTotalParticipants()));
        Text opened = new Text(Integer.toString(analyticsService.getTotalOpened()));
        Text averageParticipants = new Text(Double.toString(analyticsService.getAverageParticipants()));
        Text averagePrice = new Text(Double.toString(analyticsService.getAveragePrice()));
        
        generalPane.add(generalText, 0, 0, 2, 1);  
        
        generalPane.add(generalEvents, 0, 1);        
        generalPane.add(events, 1, 1);
        generalPane.add(generalRevenue, 0, 2);
        generalPane.add(revenue, 1, 2);
        generalPane.add(generalParticipants, 0, 3);
        generalPane.add(participants, 1, 3);
        generalPane.add(generalOpened, 0, 4);
        generalPane.add(opened, 1, 4);
        generalPane.add(generalAverageParticipants, 0, 5);
        generalPane.add(averageParticipants, 1, 5);
        generalPane.add(generalPrice, 0, 6);
        generalPane.add(averagePrice, 1, 6);
        
        // Rates pane
        Text ratesOpened = new Text("Opened account (%): ");
        Text ratesNotOpened = new Text("Didn't open account (%): ");
        Text ratesMales = new Text("Male participants (%): ");
        Text ratesFemales = new Text("Female participants (%): ");
        
        Text openedRate = new Text(Double.toString(analyticsService.getOpenedRate()));
        Text notOpenedRate = new Text(Double.toString(analyticsService.getNotOpenedRate()));
        Text malesRate = new Text(Double.toString(analyticsService.getMalesRate()));
        Text femalesRate = new Text(Double.toString(analyticsService.getFemalesRate()));
        
        ratesPane.add(ratesText, 0, 0, 2, 1);        
        ratesPane.add(ratesOpened, 0, 1);
        ratesPane.add(openedRate, 1, 1);
        ratesPane.add(ratesNotOpened, 0, 2);
        ratesPane.add(notOpenedRate, 1, 2);
        ratesPane.add(ratesMales, 0, 3);
        ratesPane.add(malesRate, 1, 3);
        ratesPane.add(ratesFemales, 0, 4);
        ratesPane.add(femalesRate, 1, 4);
        
        // Revenue pane                        
        Text revenueAverage = new Text("Average revenue per event (€): ");
        Text revenueMedian = new Text("Median revenue per event (€): ");        
        
        Text averageRevenue = new Text(Double.toString(analyticsService.getAverageRevenue()));
        Text medianRevenue = new Text(Double.toString(analyticsService.getMedianRevenue()));
        
        revenuePane.add(revenueText, 0, 0, 2, 1);
        revenuePane.add(revenueAverage, 0, 1);
        revenuePane.add(averageRevenue, 1, 1);
        revenuePane.add(revenueMedian, 0, 2);
        revenuePane.add(medianRevenue, 1, 2);
        
        // Correlation pane
        Text correlation = new Text(Double.toString(analyticsService.getCorrelationBetweenParticipantsAndOpenedAccounts()));
        
        // PredictPane                
        Label predictByParticipantsLabel = new Label("Revenue by participants: "); 
        Label predictByPriceLabel = new Label("Revenue by price: ");
        
        predictPane.add(predictText, 0, 0, 2, 1);
        
        predictPane.add(predictByParticipantsLabel, 0, 1);
        predictPane.add(predictByParticipantsField, 1, 1);
        
        predictPane.add(predictionByParticipantsText, 0, 2);
        
        predictPane.add(predictByPriceLabel, 0, 3);
        predictPane.add(predictByPriceField, 1, 3);
        
        predictPane.add(predictionByPriceText, 0, 4);
        
        pane.add(generalPane, 0, 0);
        pane.add(ratesPane, 0, 1);
        pane.add(revenuePane, 1, 0);
        pane.add(correlationPane, 1, 1);
        pane.add(predictPane, 1, 2);
        
        layout.setCenter(pane);        
        
        return layout;
    }
    
}
