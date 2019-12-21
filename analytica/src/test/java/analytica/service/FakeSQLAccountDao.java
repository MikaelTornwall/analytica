package analytica.service;

import analytica.dao.AccountDao;
import analytica.domain.Account;
import java.util.List;
import java.util.ArrayList;

/**
 * Replaces AccountDao class for AnalyticaService tests
 * 
 * @author Mikael Törnwall
 */

public class FakeSQLAccountDao implements AccountDao {
    
    private List<Account> accounts;
    private int id;
    
    public FakeSQLAccountDao() {
        this.accounts = new ArrayList<>();
        this.id = 0;
    }
    
    public void create(Account account) {
        account.setId(id++);
        this.accounts.add(account);
    };
    
    public Account getByUsername(String username) {
        for (Account account : this.accounts) {
            if (account.getUsername().equals(username)) {
                return account;
            }        
        }
        
        return null;
    };
    
    public List<Account> getAll() {
        return this.accounts;
    };
}