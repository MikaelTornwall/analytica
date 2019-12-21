package analytica.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * SQLDatabase class is responsible for initializing database and sharing database connection
 * 
 * @author Mikael Törnwall
 */

public class SQLDatabase {    
    
    private final String path;
    
    public SQLDatabase(String path) {
        this.path = path;
        this.createTables();
    }
    
    private void createAccountTable() {
        try (Connection connection = this.createAndConnectDatabase()) {
            connection.prepareStatement("CREATE TABLE Account ("
                    + "id int auto_increment primary key, "
                    + "username varchar(255), "
                    + "password varchar(255))"
            ).execute();            
        } catch (SQLException e) {         
        }
    }
    
    private void createEventTable() {
        try (Connection connection = this.createAndConnectDatabase()) {            
            connection.prepareStatement("CREATE TABLE Event ("
                    + "id int auto_increment primary key, "
                    + "name varchar(255), "
                    + "price DECIMAL, "
                    + "participants INTEGER, "
                    + "opened INTEGER, "
                    + "notopened INTEGER, "
                    + "males INTEGER, "
                    + "females INTEGER)"
            ).execute();
        } catch (SQLException e) {            
        }
    }
    
    private void dropAccountTable() {
        try (Connection connection = this.createAndConnectDatabase()) {
            connection.prepareStatement("DROP TABLE Account").execute();            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }    
    }
    
    private void dropEventTable() {
        try (Connection connection = this.createAndConnectDatabase()) {
            connection.prepareStatement("DROP TABLE Event").execute();            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }    
    }
    
    public void clearDatabase() {
        this.dropAccountTable();
        this.dropEventTable();
        this.createTables();
    }
    
    private void createTables() {
        this.createAccountTable();
        this.createEventTable();
        
    }        
    
    public Connection getConnection() {
        return this.createAndConnectDatabase();
    }
    
    private Connection createAndConnectDatabase() {
        Connection connection = null;
        
        try {
            connection = DriverManager.getConnection(this.path, "sa", "");            
        } catch (SQLException e) {            
        }

        return connection;
    }
}
