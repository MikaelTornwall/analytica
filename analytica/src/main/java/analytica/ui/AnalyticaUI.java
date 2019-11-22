package analytica.ui;

import analytica.domain.User;
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
    
    private Scene loginScene;
    private Scene registerScene;
    private Scene dashboardScene;        
    
    // Currently logged in user
    private User user;
    
    // Temporary solution before DB
    private Set<User> users;
    
    public void init() {
        this.user = null;
        this.users = new HashSet<>();
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
        BorderPane dashboardLayout = (BorderPane) dashboard.getDashboard();        
        
        // 3. Create scenes
        loginScene = new Scene(loginLayout, 700, 500);
        registerScene = new Scene(registerLayout, 700, 500);            
        dashboardScene = new Scene(dashboardLayout, 700, 500);
                
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
                                    
            if (!this.users.isEmpty()) {
                users.stream().forEach(user -> {
                    if (user.getUsername().equals(username)) {
                        if (user.checkPassword(password)) {
                            this.user = user;
                            login.setUnsuccessfulLoginLabel(""); 
                            stage.setScene(dashboardScene);                                                       
                        }
                    }
                });
            }            
                        
            if (this.user == null) {
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
            
            register.setUsernameInput("");
            register.setPasswordInput("");
            
            User newUser = new User(username, password);
            
            if (!this.users.contains(newUser)) {
                this.users.add(newUser);
                stage.setScene(loginScene);
            } else {
                register.setReservedUsernameLabel("This username is already in use. Choose another one.");
            }                                    
        });
        
        backToLoginButton.setOnAction((event) -> {
            register.setReservedUsernameLabel("");
            stage.setScene(loginScene); 
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
