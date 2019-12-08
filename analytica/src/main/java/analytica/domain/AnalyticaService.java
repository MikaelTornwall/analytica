package analytica.domain;

import java.util.List;
import analytica.dao.AccountDAO;
import java.sql.SQLException;

public class AnalyticaService {
    
    private Account user;
    private AccountDAO accountDAO;        
    
    public AnalyticaService(AccountDAO accountDAO) {
        this.user = null;        
        this.accountDAO = accountDAO;        
    }
    
    public Account getUser() {
        return this.user;
    }
    
    public void setUser(Account user) {
        this.user = user;
    }
    
    public List<Account> getUsers() throws SQLException {
        return this.accountDAO.getUsers();
    }  
    
    public boolean userExists(Account account) throws SQLException {
        if (accountDAO.getAccountByUsername(account.getUsername()) == null) {
            return false;
        }
        return true;
    }
    
    public boolean login(String username, String password) throws SQLException {
        
        Account account = accountDAO.getAccountByUsername(username);
        
        if (account == null) {
            return false;
        }
        
        if (!account.checkPassword(password)) {
            return false;
        }
        
        this.setUser(account);
        
        return true;
    }
    
    public boolean createUser(Account account) throws SQLException {        
        
        if (userExists(account)) {
            return false;
        }
        
        this.accountDAO.create(account);                                                
        
        return true;
    }
}
