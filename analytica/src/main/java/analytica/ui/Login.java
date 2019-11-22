package analytica.ui;

import analytica.domain.User;
import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Login {
    
    private final int SPACING = 10;
    private Button loginButton;
    private Button createButton;
    private TextField usernameInput;
    private TextField passwordInput;
    private Label unsuccessfulLoginLabel;        
    
    public Login() {
        this.loginButton = new Button("Login");
        this.createButton = new Button("Create new user");   
        this.usernameInput = new TextField();      
        this.passwordInput = new PasswordField();             
        this.unsuccessfulLoginLabel = new Label("");        
    }
    
    public Button getLoginButton() {
        return this.loginButton;
    }
    
    public Button getCreateButton() {
        return this.createButton;
    }
    
    public String getUsernameInput() {
        return this.usernameInput.getText();
    }
    
    public void setUsernameInput(String text) {
        this.usernameInput.setText(text);
    }
           
    public String getPasswordInput() {
        return this.passwordInput.getText();
    }
    
    public void setPasswordInput(String text) {
        this.passwordInput.setText(text);
    }
    
    public void setUnsuccessfulLoginLabel(String text) {
        this.unsuccessfulLoginLabel.setText(text);
    }
            
    public Parent getLogin() {
        // Login screen       
        // Create login components
        Label loginLabel = new Label("Login");        
        Label usernameLabel = new Label("Username");
        Label passwordLabel = new Label("Password");                        
                
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
        
        BorderPane layout = new BorderPane();
        layout.setTop(loginLabel);
        layout.setCenter(loginPane);
                        
        return layout;
    }
}
