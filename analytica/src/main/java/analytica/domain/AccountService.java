package analytica.domain;

import java.util.List;
import analytica.dao.AccountDao;

/**
 * AccountService class
 * 
 * @author Mikael Törnwall
 */

public class AccountService {
    
    private Account user;
    private AccountDao accountDao;        
    
    public AccountService(AccountDao accountDAO) {
        this.user = null;        
        this.accountDao = accountDAO;        
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
     * Method returns the list of all users 
     * 
     * @return current users as a List object
     */
    
    public List<Account> getAccounts() {        
        return this.accountDao.getAll();
    }  
    
    /**
     * Method check is a user exists
     * 
     * @param account is an Account object
     * @return true if user exists in the database, false otherwise
     */
    
    public boolean accountExists(Account account) {        
        if (accountDao.getByUsername(account.getUsername()) == null) {
            return false;
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
        
        account = accountDao.getByUsername(username);        
        
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
    
    public boolean createAccount(Account account) {                
        if (accountExists(account)) {
            return false;
        }
        
        this.accountDao.create(account);                                                
                
        return true;
    }
}
