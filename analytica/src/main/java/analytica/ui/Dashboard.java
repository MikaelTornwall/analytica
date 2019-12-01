package analytica.ui;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;


public class Dashboard {
    private final int WIDTH;
    private final int HEIGHT;    
    private Parent dashboard;
    
    public Dashboard() {
        this.WIDTH = 700;
        this.HEIGHT = 500;                       
        this.dashboard = this.createDashboard();
    }        
    
    public Parent getDashboard() {
        return this.dashboard;
    }        
    
    public Parent createDashboard() {
        // Dashboard screens                        
        // Create components
        Label label = new Label("Dashboard");
        
        // Create panes        
        FlowPane flowpane = new FlowPane();
        
        // Add components
        flowpane.getChildren().add(label);
        
        BorderPane layout = new BorderPane();          
        layout.setCenter(flowpane);       
        
        return layout;
    }
    
}
