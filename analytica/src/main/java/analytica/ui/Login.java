package analytica.ui;

/**
 * Login class is responsible for creating login component for the applicaton UI
 * 
 * @author Mikael Törnwall
 */

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
    
    /**
     * Method returns login button so that event handling can be performed
     * 
     * @return JavaFX Button component
     */
    
    public Button getLoginButton() {
        return this.loginButton;
    }
    
    /**
     * Method returns create account button so that event handling can be performed
     * 
     * @return JavaFX Button component
     */
    
    public Button getCreateButton() {
        return this.createButton;
    }
    
    /**
     * Method returns input data from the username text field
     * 
     * @return input data string
     */
    
    public String getUsernameInput() {
        return this.usernameInput.getText();
    }
    
    /**
     * Method allows setting username input string
     * 
     * @param text that will be set as the input value
     */
    
    public void setUsernameInput(String text) {
        this.usernameInput.setText(text);
    }
     
    /**
     * Method returns input data from the password text field
     * 
     * @return input data string
     */
    
    public String getPasswordInput() {
        return this.passwordInput.getText();
    }
    
    /**
     * Method allows setting password input string
     * 
     * @param text that will be set as the input value
     */
    
    public void setPasswordInput(String text) {
        this.passwordInput.setText(text);
    }
    
    /**
     * Method allows setting unsuccessful login label string
     * 
     * @param text that will be set as the input value
     */
    
    public void setUnsuccessfulLoginLabel(String text) {
        this.unsuccessfulLoginLabel.setText(text);
    }
            
    /**
     * Method creates a login component
     * 
     * @return login component as a Parent object
     */
    
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
