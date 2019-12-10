package analytica.domain;

import java.util.List;
import analytica.dao.SQLAccountDao;
import analytica.dao.AccountDao;
import java.sql.SQLException;

/**
 * AccountService class
 * 
 * @author Mikael Törnwall
 */

public class AccountService {
    
    private Account user;
    private AccountDao accountDAO;        
    
    public AccountService(AccountDao accountDAO) {
        this.user = null;        
        this.accountDAO = accountDAO;        
    }
    
    /**
     * Method returns the current user
     * 
     * @return current user as an Account object
     */
    
    public Account getUser() {
        return this.user;
    }
    
    /**
     * Method set the current user
     * 
     * @param user is an Account object
     */
    
    public void setUser(Account user) {
        this.user = user;
    }
    
    /**
     * Method return the list of all users 
     * 
     * @return current users as a List object
     */
    
    public List<Account> getUsers() {        
        try {
            return this.accountDAO.getUsers();
        } catch (SQLException e) {
            return null;
        }        
    }  
    
    /**
     * Method check is a user exists
     * 
     * @param account is an Account object
     * @return true if user exists in the database, false otherwise
     */
    
    public boolean userExists(Account account) {
        try {
            if (accountDAO.getAccountByUsername(account.getUsername()) == null) {
            return false;
            } 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return true;
    }
    
    /**
     * Method handles login logic, as it checks if password matches
     * 
     * @param username as a String
     * @param password as a String
     * @return true if login is successful, false otherwise
     */
    
    public boolean login(String username, String password) {
        Account account = null;
        
        try {
            account = accountDAO.getAccountByUsername(username);
        } catch(SQLException e) {            
            
        }
        
        
        if (account == null) {
            return false;
        }
        
        if (!account.checkPassword(password)) {
            return false;
        }
        
        this.setUser(account);
        
        return true;
    }
    
    /**
     * Methods creates a new user
     * 
     * @param account as an Account object
     * @return true if username is not taken and operation is successful, false otherwise
     */
    
    public boolean create(Account account) {        
        
        if (userExists(account)) {
            return false;
        }
        
        try {
            this.accountDAO.create(account);                                                
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
                
        return true;
    }
}
