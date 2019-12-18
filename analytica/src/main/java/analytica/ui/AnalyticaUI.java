package analytica.ui;

import analytica.domain.Account;
import analytica.domain.AccountService;
import analytica.dao.SQLAccountDao;
import analytica.db.SQLDatabase;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;

public class AnalyticaUI extends Application {
    private final int SPACING = 10;    
    private final int WIDTH = 1000;
    private final int HEIGHT = 700;
        
    private Menu menu;
    
    private Scene loginScene;
    private Scene registerScene;
    private Scene loggedInScene;      
    
    // Application logic
    AccountService accountService;    
    
    public void init(String path) {
        this.accountService = new AccountService(new SQLAccountDao(new SQLDatabase(path)));                                  
        this.menu = new Menu();                
    }
    
    @Override
    public void start(Stage stage) throws SQLException {
        
        // Initialization
        // Database path
        String path = "jdbc:h2:./analytica_db";        
        
        this.init(path);                                     
                                     
        // Create layouts        
        // Login layout
        Login login = new Login();        
        BorderPane loginLayout = (BorderPane) login.getLogin();
                        
        // New user layout
        Register register = new Register();
        BorderPane registerLayout = (BorderPane) register.getRegister();
        
        // Dashboard layout
        Dashboard dashboard = new Dashboard();        
        
        // NewEvent layout
        NewEvent addData = new NewEvent();        
        
        BorderPane loggedInLayout = new BorderPane();
        loggedInLayout.setTop(menu.getMenu());
        loggedInLayout.setCenter((BorderPane) dashboard.getDashboard());
        
        // Create scenes
        loginScene = new Scene(loginLayout, 700, 500);
        registerScene = new Scene(registerLayout, 700, 500);                                                   
        loggedInScene = new Scene(loggedInLayout, 700, 500);
        
        //dashboardScene = new Scene(dashboardLayout, 700, 500);
        //addDataScene = new Scene(addDataLayout, 700, 500);
                
        // Get components required for event handling
        // Login
        Button loginButton = login.getLoginButton();
        Button createButton = login.getCreateButton();
        
        // Register
        Button createUserButton = register.getCreateButton();
        Button backToLoginButton = register.getBackButton();
        
        // Event handlers
        // Login screen event handlers
        loginButton.setOnAction((event) -> {
            String username = login.getUsernameInput();
            String password = login.getPasswordInput();
            
            login.setUsernameInput("");
            login.setPasswordInput("");
            
            System.out.println("Username: " + username);
            System.out.println("Password: " + password);
            
            if (accountService.login(username, password)) {
                    login.setUnsuccessfulLoginLabel(""); 
                    stage.setScene(loggedInScene);                                                       
                } else {
                    login.setUnsuccessfulLoginLabel("Invalid username or password.");
                }                    
        });
                
        createButton.setOnAction((event) -> {            
            stage.setScene(registerScene);
        });
                
        // Register screen event handlers
        createUserButton.setOnAction((event) -> {
            String username = register.getUsernameInput();
            String password = register.getPasswordInput();                        
            
            Account account = new Account(username, password);
            
            if (accountService.createAccount(account)) {
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
           accountService.setUser(null);
        });
                 
        // Set initial stage
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
