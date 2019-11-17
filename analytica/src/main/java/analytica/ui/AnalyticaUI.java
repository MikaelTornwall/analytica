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
        
        this.init();
        
        // Login screen       
        // Create login components
        Label loginLabel = new Label("Login");
        Label unsuccessfulLoginLabel = new Label("");
        Label usernameLabel = new Label("Username");
        Label passwordLabel = new Label("Password");        
        TextField usernameInput = new TextField();       
        TextField passwordInput = new PasswordField();             
        Button loginButton = new Button("Login");
        Button createButton = new Button("Create new user");         
                
        // Create panes
        VBox loginPane = new VBox(SPACING);        
        HBox inputPaneUsername = new HBox(SPACING);
        HBox inputPanePassword = new HBox(SPACING);                                
        
        // Add login components        
        inputPaneUsername.getChildren().addAll(usernameLabel, usernameInput);        
        inputPanePassword.getChildren().addAll(passwordLabel, passwordInput);                                               
        loginPane.getChildren().addAll(unsuccessfulLoginLabel, inputPaneUsername, inputPanePassword, loginButton, createButton);
        
        // Styling and spacing
        loginLabel.setPadding(new Insets(SPACING));
        loginPane.setPadding(new Insets(SPACING));                          
        
        // Create new user screen
        // Create components
        Label registerLabel = new Label("Create new user");                
        Label reservedUsername = new Label("");        
        Label newUsernameLabel = new Label("Username");
        Label newPasswordLabel = new Label("Password");        
        TextField newUsernameInput = new TextField();       
        TextField newPasswordInput = new PasswordField();   
        Button createUserButton = new Button("Create");
        Button backToLoginButton = new Button("Back");
        
        // Create panes
        VBox registerPane = new VBox(SPACING);
        HBox inputPaneNewUsername = new HBox(SPACING);
        HBox inputPaneNewPassword = new HBox(SPACING);
        
        inputPaneNewUsername.getChildren().addAll(newUsernameLabel, newUsernameInput);        
        inputPaneNewPassword.getChildren().addAll(newPasswordLabel, newPasswordInput);                                               
        registerPane.getChildren().addAll(inputPaneNewUsername, inputPaneNewPassword, createUserButton, backToLoginButton);
        
        // Dashboard screen
        // Create components
        Label dashboardLabel = new Label("Dashboard");
        
        // Create panes        
        FlowPane dashboardPane = new FlowPane();
        
        // Add components
        dashboardPane.getChildren().add(dashboardLabel);
        
        // Create layouts
        // Login layout
        BorderPane loginLayout = new BorderPane();
        loginLayout.setTop(loginLabel);
        loginLayout.setCenter(loginPane);        
        
        // Create new user layout
        BorderPane registerLayout = new BorderPane();
        registerLayout.setTop(registerLabel);
        registerLayout.setCenter(registerPane);
        
        // Dashboard layout
        BorderPane dashboardLayout = new BorderPane();
        dashboardLayout.setCenter(dashboardPane);
        
        // Create scenes
        registerScene = new Scene(registerLayout, 700, 500);            
        dashboardScene = new Scene(dashboardLayout, 700, 500);
        // Login screen event handlers
        loginButton.setOnAction((event) -> {
            String username = usernameInput.getText();
            String password = passwordInput.getText();
            
            usernameInput.setText("");
            passwordInput.setText("");
            
            System.out.println("Username: " + username);
            System.out.println("Password: " + password);
                        
            
            if (!this.users.isEmpty()) {
                users.stream().forEach(user -> {
                    if (user.getUsername().equals(username)) {
                        if (user.checkPassword(password)) {
                            this.user = user;
                            unsuccessfulLoginLabel.setText(""); 
                            stage.setScene(dashboardScene);                                                       
                        }
                    }
                });
            }            
                        
            if (this.user == null) {
                unsuccessfulLoginLabel.setText("Invalid username or password.");
            }
        });
                
        createButton.setOnAction((event) -> {            
            stage.setScene(registerScene);
        });
        
        // Register screen event handlers
        createUserButton.setOnAction((event) -> {
            String username = newUsernameInput.getText();
            String password = newPasswordInput.getText();
            
            newUsernameInput.setText("");
            newPasswordInput.setText("");
            
            User newUser = new User(username, password);
            
            if (!this.users.contains(newUser)) {
                this.users.add(newUser);
                stage.setScene(loginScene);
            } else {
                reservedUsername.setText("This username is already in use. Choose another one.");
            }                                    
        });
        
        backToLoginButton.setOnAction((event) -> {
           reservedUsername.setText("");
           stage.setScene(loginScene); 
        });
                                        
        loginScene = new Scene(loginLayout, 700, 500);
        
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
