package analytica.ui;

import analytica.domain.Account;
import analytica.service.AccountService;
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
import javafx.geometry.Insets;

/**
 * Register class is responsible for generating and managing register component
 * 
 * @return true if input data is ok, otherwise false
 */

public class Register {
    
    final int SPACING = 10;
    
    private final AccountService accountService;
    private final Label errorLabel;    
    private final TextField usernameInput;
    private final TextField passwordInput;   
    private final Button createUserButton;
    private final Button backToLoginButton;
    
    public Register(AccountService accountService) {
        this.accountService = accountService;
        this.errorLabel = new Label("");        
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
    
    public void setErrorLabel(String text) {
        this.errorLabel.setText(text);
    }
    
    private boolean checkFormat(String username, String password) {
        if (username.length() < 6 || username.length() > 15) {
            setErrorLabel("Username should be 6-15 characters long!");
            return false;
        } else if (password.length() < 8 || password.length() > 15) {
            setErrorLabel("Password should be 8-15 characters long!");
            return false;
        }
        
        return true;
    }
    
    /**
     * Method creates a new Account object and delivers it for account service for following actions
     * 
     * @return true if account was created successfully, otherwise false
     */
    
    public boolean createAccount() {        
        String username = getUsernameInput();
        String password = getPasswordInput();
        
        if (!checkFormat(username, password)) {
            return false;
        }
        
        if (accountService.createAccount(new Account(username, password))) {
            setUsernameInput("");
            setPasswordInput("");                        
            return true;
        }
        
        setErrorLabel("This username is already in use. Choose another one.");
        return false;
    }
    
    public Parent getRegister() {        
        Text registerText = new Text("Create new user");                        
        registerText.setFont(Font.font(20));
        Label usernameLabel = new Label("Username");
        Label passwordLabel = new Label("Password");
                
        VBox registerPane = new VBox(SPACING);
        HBox inputPaneNewUsername = new HBox(SPACING);
        HBox inputPaneNewPassword = new HBox(SPACING);
        
        inputPaneNewUsername.getChildren().addAll(usernameLabel, usernameInput);        
        inputPaneNewPassword.getChildren().addAll(passwordLabel, passwordInput);                                               
        registerPane.getChildren().addAll(inputPaneNewUsername, inputPaneNewPassword, createUserButton, backToLoginButton, errorLabel);
        
        BorderPane layout = new BorderPane();
        layout.setTop(registerText);
        layout.setCenter(registerPane);
        layout.setPadding(new Insets(SPACING, 0, 0, SPACING));
        layout.setMargin(registerText, new Insets(0, 0, SPACING, 0));
        
        return layout;
    }
}
