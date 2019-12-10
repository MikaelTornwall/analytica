/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    
    private String path;
    
    public SQLDatabase(String path) {
        this.path = path;
        this.createTables();
    }
    
    private void createAccountTable() {
        try (Connection connection = this.createAndConnectDatabase()) {
            connection.prepareStatement("CREATE TABLE Account (id int auto_increment primary key, username varchar(255), password varchar(255))").execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private void createTables() {
        this.createAccountTable();
        
    }        
    
    public Connection getConnection() {
        return this.createAndConnectDatabase();
    }
    
    private Connection createAndConnectDatabase() {
        Connection connection = null;
        
        try {
            connection = DriverManager.getConnection(this.path, "sa", "");
            connection.prepareStatement("CREATE TABLE Account (id int auto_increment primary key, username varchar(255), password varchar(255))").execute();
        } catch (SQLException e) {            
        }

        return connection;
    }
}
