package analytica.domain;

import java.util.List;
import analytica.dao.SQLAccountDao;
import analytica.dao.AccountDao;
import java.sql.SQLException;

public class AccountService {
    
    private Account user;
    private AccountDao accountDAO;        
    
    public AccountService(AccountDao accountDAO) {
        this.user = null;        
        this.accountDAO = accountDAO;        
    }
    
    public Account getUser() {
        return this.user;
    }
    
    public void setUser(Account user) {
        this.user = user;
    }
    
    public List<Account> getUsers() {        
        try {
            return this.accountDAO.getUsers();
        } catch (SQLException e) {
            return null;
        }        
    }  
    
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
    
    public boolean createUser(Account account) {        
        
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
