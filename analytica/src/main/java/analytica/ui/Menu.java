package analytica.ui;

import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;

public class Menu {
    private Button dashboardButton;
    private Button addDataButton;
    private Button logoutButton;
    private Parent menu;
    
    public Menu() {
        this.dashboardButton = new Button("Dashboard");
        this.addDataButton = new Button("Add data");
        this.logoutButton = new Button("Logout");
        this.menu = this.createMenu();
    }
    
    public Button getAddDataButton() {
        return this.addDataButton;
    }
    
    public Button getDashboardButton() {
        return this.dashboardButton;
    }
    
    public Button getLogoutButton() {
        return this.logoutButton;
    }
    
    public Parent getMenu() {
        return this.menu;
    }
    
    public Parent createMenu() {
        HBox menuPane = new HBox();
        menuPane.getChildren().addAll(dashboardButton, addDataButton, logoutButton);
        
        return menuPane;
    }
}
