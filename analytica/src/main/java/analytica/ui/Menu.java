package analytica.ui;

import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;

public class Menu {
    Button dashboardButton = new Button("Dashboard");
    Button addDataButton = new Button("Add data");    
    Button logoutButton = new Button("Logout");        
    
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
        HBox menuPane = new HBox();
        menuPane.getChildren().addAll(dashboardButton, addDataButton, logoutButton);
        
        return menuPane;
    }
}
