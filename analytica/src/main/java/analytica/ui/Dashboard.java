package analytica.ui;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;


public class Dashboard {
    private final int WIDTH;
    private final int HEIGHT;
    private Menu menu;
    
    public Dashboard() {
        this.WIDTH = 700;
        this.HEIGHT = 500;
        this.menu = new Menu();
    }
    
    public Menu getMenu() {
        return this.menu;
    }
    
    public Parent getDashboard() {
        // Dashboard screen
        
        // Get menu        
        HBox menuPane = (HBox) menu.getMenu();
        
        // Create components
        Label label = new Label("Dashboard");
        
        // Create panes        
        FlowPane flowpane = new FlowPane();
        
        // Add components
        flowpane.getChildren().add(label);
        
        BorderPane layout = new BorderPane();
        layout.setTop(menuPane);
        layout.setCenter(flowpane);
        
        return layout;
    }
    
}
