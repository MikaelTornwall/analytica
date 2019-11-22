package analytica.ui;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Register {
    
    final int SPACING = 10;
    
    private Label reservedUsername;    
    private TextField usernameInput;
    private TextField passwordInput;   
    private Button createUserButton;
    private Button backToLoginButton;
    
    public Register() {
        this.reservedUsername = new Label("");        
        this.usernameInput = new TextField();   
        this.passwordInput = new PasswordField();
        this.createUserButton = new Button("Create");
        this.backToLoginButton = new Button("Back");
    }
    
    public Button getBackButton() {
        return this.backToLoginButton;
    }
    
    public Button getCreateButton() {
        return this.createUserButton;
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
    
    public void setReservedUsernameLabel(String text) {
        this.reservedUsername.setText(text);
    }
    
    public Parent getRegister() {
        // Create new user screen
        // Create components
        Label registerLabel = new Label("Create new user");                        
        Label usernameLabel = new Label("Username");
        Label passwordLabel = new Label("Password");
        
        // Create panes
        VBox registerPane = new VBox(SPACING);
        HBox inputPaneNewUsername = new HBox(SPACING);
        HBox inputPaneNewPassword = new HBox(SPACING);
        
        inputPaneNewUsername.getChildren().addAll(usernameLabel, usernameInput);        
        inputPaneNewPassword.getChildren().addAll(passwordLabel, passwordInput);                                               
        registerPane.getChildren().addAll(inputPaneNewUsername, inputPaneNewPassword, createUserButton, backToLoginButton);
        
        BorderPane layout = new BorderPane();
        layout.setTop(registerLabel);
        layout.setCenter(registerPane);
        
        return layout;
    }
}
