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
    
    /**
     * Method creates an account table into database if it does not exist
     *   
     */
    
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
    
    /**
     * Method creates an event table into database if it does not exist
     *   
     */
    
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
    
    /**
     * Method drops an account table from the database if one exists
     *   
     */
    
    private void dropAccountTable() {
        try (Connection connection = this.createAndConnectDatabase()) {
            connection.prepareStatement("DROP TABLE Account").execute();            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }    
    }
    
    /**
     * Method drops an event table from the database if one exists
     *   
     */
    
    private void dropEventTable() {
        try (Connection connection = this.createAndConnectDatabase()) {
            connection.prepareStatement("DROP TABLE Event").execute();            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }    
    }
    
    /**
     * Method clears the database by dropping existing tables and creating new ones
     *   
     */
    
    public void clearDatabase() {
        this.dropAccountTable();
        this.dropEventTable();
        this.createTables();
    }
    
    /**
     * Method creates new database tables
     *   
     */
    
    private void createTables() {
        this.createAccountTable();
        this.createEventTable();
        
    }        
    
    /**
     * Method provides the database connection
     *   
     * @return Connection object
     */
    
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
