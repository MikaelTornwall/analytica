package analytica.ui;

import analytica.service.AccountService;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.Font;

/**
 * Login class is responsible for creating login component for the applicaton UI
 * 
 * @author Mikael Törnwall
 */

public class Login {
    
    private final int SPACING = 10;
    private final AccountService accountService;
    private final Button loginButton;
    private final Button createButton;
    private final TextField usernameInput;
    private final TextField passwordInput;
    private final Label unsuccessfulLoginLabel;        
    
    public Login(AccountService accountService) {
        this.accountService = accountService;
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
     * Method takes login input information and delivers it to AccountService for user authentication
     * 
     * @return true if username and password match with the one found from the database, false otherwise
     */
    
    public boolean login() {
        String username = getUsernameInput();
        String password = getPasswordInput();                    
                
        if (accountService.login(username, password)) {
            setUsernameInput("");
            setPasswordInput("");
            return true;
        }
        
        setUnsuccessfulLoginLabel("Invalid username or password.");
        
        return false;
    }
            
    /**
     * Method creates a login component
     * 
     * @return login component as a Parent object
     */
    
    public Parent getLogin() {        
        Text loginText = new Text("Login");   
        loginText.setFont(Font.font(20));
        Label usernameLabel = new Label("Username");
        Label passwordLabel = new Label("Password");                        
                       
        VBox loginPane = new VBox(SPACING);        
        HBox inputPaneUsername = new HBox(SPACING);
        HBox inputPanePassword = new HBox(SPACING);                                
        
        inputPaneUsername.getChildren().addAll(usernameLabel, usernameInput);        
        inputPanePassword.getChildren().addAll(passwordLabel, passwordInput);                                               
        loginPane.getChildren().addAll(inputPaneUsername, inputPanePassword, loginButton, createButton, unsuccessfulLoginLabel);                                
        
        BorderPane layout = new BorderPane();
        layout.setTop(loginText);
        layout.setCenter(loginPane);
        layout.setPadding(new Insets(SPACING));     
        layout.setMargin(loginText, new Insets(0, 0, SPACING, 0));        
                        
        return layout;
    }
}
