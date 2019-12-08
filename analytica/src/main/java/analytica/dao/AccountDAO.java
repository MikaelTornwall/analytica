/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analytica.dao;

/**
 *
 * @author Duuni
 */

import java.util.List;
import analytica.domain.Account;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AccountDAO {
    private String path;
    
    public AccountDAO(String path) {
        this.path = path;
    }
    
    public void create(Account account) throws SQLException {
        try (Connection connection = createAndConnectDatabase()) {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO Account (username, password) VALUES (?, ?)");
            stmt.setString(1, account.getUsername());
            stmt.setString(2, account.getPassword());            
            stmt.executeUpdate();
        }
    }
    
    public Account getAccountByUsername(String username) throws SQLException {        
        Account account = null;
        
        try (Connection connection = createAndConnectDatabase()) {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Account WHERE username = ?");
            stmt.setString(1, username);
            ResultSet res = stmt.executeQuery();            
            
            if (res.next()) {
                account = new Account(res.getString("username"), res.getString("password"));
            }
        }                
        
        if (account == null) {
            return null;
        }
        
        return account;
    }
    
    public List<Account> getUsers() throws SQLException {
        List<Account> accounts = new ArrayList<>();
        try (Connection yhteys = createAndConnectDatabase();
                ResultSet res = yhteys.prepareStatement("SELECT * FROM Account").executeQuery()) {
            while (res.next()) {
                accounts.add(new Account(res.getInt("id"), res.getString("username"), res.getString("password")));
            }
        }
        return accounts;
    }
    
    private Connection createAndConnectDatabase() throws SQLException {
        Connection connection = DriverManager.getConnection(this.path, "sa", "");
        
        try {
            connection.prepareStatement("CREATE TABLE Account (id int auto_increment primary key, username varchar(255), password varchar(255))").execute();
        } catch (SQLException t) {            
        }

        return connection;
    }
}
