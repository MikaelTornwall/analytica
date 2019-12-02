package analytica.ui;

import analytica.domain.User;
import analytica.domain.AnalyticaService;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.geometry.Insets;

public class AnalyticaUI extends Application {
    private final int SPACING = 10;    
    private final int WIDTH = 1000;
    private final int HEIGHT = 700;
    
    private Menu menu;
    
    private Scene loginScene;
    private Scene registerScene;
    private Scene loggedInScene;      
    
    // Application logic
    AnalyticaService service;
    // Currently logged user    
    
    public void init() {
        this.service = new AnalyticaService();                          
        this.menu = new Menu();        
    }
    
    @Override
    public void start(Stage stage) {
        
        // 1. Initialization
        this.init();                                     
                                     
        // 2. Create layouts        
        // 2.1 Login layout
        Login login = new Login();        
        BorderPane loginLayout = (BorderPane) login.getLogin();
                        
        // 2.2 New user layout
        Register register = new Register();
        BorderPane registerLayout = (BorderPane) register.getRegister();
        
        // 2.3 Dashboard layout
        Dashboard dashboard = new Dashboard();        
        
        // 2.4 AddData layout
        AddData addData = new AddData();        
        
        BorderPane loggedInLayout = new BorderPane();
        loggedInLayout.setTop(menu.getMenu());
        loggedInLayout.setCenter((BorderPane) dashboard.getDashboard());
        
        // 3. Create scenes
        loginScene = new Scene(loginLayout, 700, 500);
        registerScene = new Scene(registerLayout, 700, 500);                                                   
        loggedInScene = new Scene(loggedInLayout, 700, 500);
        
        //dashboardScene = new Scene(dashboardLayout, 700, 500);
        //addDataScene = new Scene(addDataLayout, 700, 500);
                
        // 4. Get components required for event handling
        // 4.1 Login
        Button loginButton = login.getLoginButton();
        Button createButton = login.getCreateButton();
        
        // 4.2 Register
        Button createUserButton = register.getCreateButton();
        Button backToLoginButton = register.getBackButton();
        
        // 5. Event handlers
        // 5.1 Login screen event handlers
        loginButton.setOnAction((event) -> {
            String username = login.getUsernameInput();
            String password = login.getPasswordInput();
            
            login.setUsernameInput("");
            login.setPasswordInput("");
            
            System.out.println("Username: " + username);
            System.out.println("Password: " + password);
            
            if (service.login(username, password)) {
                login.setUnsuccessfulLoginLabel(""); 
                stage.setScene(loggedInScene);                                                       
            } else {
                login.setUnsuccessfulLoginLabel("Invalid username or password.");
            }                                                                  
        });
                
        createButton.setOnAction((event) -> {            
            stage.setScene(registerScene);
        });
                
        // 5.2 Register screen event handlers
        createUserButton.setOnAction((event) -> {
            String username = register.getUsernameInput();
            String password = register.getPasswordInput();                        
            
            if (service.createUser(username, password)) {
                register.setUsernameInput("");
                register.setPasswordInput("");                
                stage.setScene(loginScene);
            } else {
                register.setReservedUsernameLabel("This username is already in use. Choose another one.");
            }                       
        });
        
        backToLoginButton.setOnAction((event) -> {
            register.setReservedUsernameLabel("");
            stage.setScene(loginScene); 
        });
        
        // Menu button event handlers        
        Button dashboardButton = menu.getDashboardButton();
        
        // Dashboard view
        dashboardButton.setOnAction((event) -> {
            loggedInLayout.setCenter(dashboard.getDashboard());            
        });
        
        // Add data view
        Button addDataButton = menu.getAddDataButton();
        
        addDataButton.setOnAction((event) -> {
            loggedInLayout.setCenter(addData.getAddData());            
        });
                
        // Logout        
        Button logoutButton = this.menu.getLogoutButton();
        
        logoutButton.setOnAction((event) -> {
           stage.setScene(loginScene); 
           service.setUser(null);
        });
                 
        // 6. Set initial stage
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);        
        stage.setScene(loginScene);
        stage.setTitle("Analytica");
        
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
