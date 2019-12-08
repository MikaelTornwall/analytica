package analytica.domain;

import java.util.List;

import analytica.dao.AccountDAO;
import java.sql.SQLException;

public class AnalyticaService {
    
    private Account user;
    private AccountDAO accountDAO;
    
    // Temporary solution before DB
    // private Set<Account> users;
    
    public AnalyticaService(AccountDAO accountDAO) {
        this.user = null;
        // this.users = new HashSet<>();
        this.accountDAO = accountDAO;
        // this.users.add(new Account("Account", "Test"));
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
    
    public void addUser(Account user) {
//        if (!this.users.contains(user)) {
//            this.users.add(user);
//        }        
    }
    
    public boolean usersIsEmpty() {
//        return this.users.isEmpty();
          return false;
    }
    
    public boolean userExists(Account user) {
        // return this.users.contains(user);
        return false;
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
        
        this.accountDAO.create(account);                                                
        
        return true;
    }
}
