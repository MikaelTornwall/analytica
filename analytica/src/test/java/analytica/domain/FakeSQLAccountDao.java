/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analytica.domain;

/**
 * Replaces AccountDao class for AnalyticaService tests
 * 
 * @author Mikael Törnwall
 */

import analytica.domain.Account;
import analytica.dao.AccountDao;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class FakeSQLAccountDao implements AccountDao {
    
    private List<Account> accounts;
    private int id;
    
    public FakeSQLAccountDao() {
        this.accounts = new ArrayList<>();
        this.id = 0;
    }
    
    public void create(Account account) throws SQLException {
        account.setId(id++);
        this.accounts.add(account);
    };
    
    public Account getAccountByUsername(String username) throws SQLException {
        for (Account account : this.accounts) {
            if (account.getUsername().equals(username)) {
                return account;
            }        
        }
        
        return null;
    };
    
    public List<Account> getUsers() throws SQLException {
        return this.accounts;
    };
}