package analytica.dao;

import java.util.List;
import analytica.domain.Account;
import analytica.db.SQLDatabase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * SQLAccountDAO class is responsible for the account related operations and database queries
 * 
 * @author Mikael Törnwall
 */

public class SQLAccountDao implements AccountDao {    
    private SQLDatabase database;    
    
    public SQLAccountDao(SQLDatabase database) {
        this.database = database;        
    }
    
    /**
     * Method creates a new account instance in the database    
     * 
     * @param account class as a parameter
     */
    
    public void create(Account account) {
        try (Connection connection = database.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO Account (username, password) VALUES (?, ?)");
            stmt.setString(1, account.getUsername());
            stmt.setString(2, account.getPassword());            
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Method fetches an account from the database if it matches the username
     * 
     * @param username string as a parameter     
     * @return method returns an account object if username finds database match, null otherwise
     */
    
    public Account getAccountByUsername(String username) {        
        Account account = null;
        
        try (Connection connection = this.database.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Account WHERE username = ?");
            stmt.setString(1, username);
            ResultSet res = stmt.executeQuery();            
            
            if (res.next()) {
                account = new Account(res.getString("username"), res.getString("password"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        if (account == null) {
            return null;
        }
        
        return account;
    }
    
    /**
     * Method fetches all the accounts from the database
     *      
     * @return list of account objects
     */
    
    public List<Account> getUsers() {
        List<Account> accounts = new ArrayList<>();
        try (Connection yhteys = this.database.getConnection();
                ResultSet res = yhteys.prepareStatement("SELECT * FROM Account").executeQuery()) {
            while (res.next()) {
                accounts.add(new Account(res.getInt("id"), res.getString("username"), res.getString("password")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return accounts;
    }        
}
