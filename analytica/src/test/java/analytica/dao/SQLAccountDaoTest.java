/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analytica.dao;

import analytica.domain.Account;
import analytica.db.SQLDatabase;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mikael Törnwall
 */

public class SQLAccountDaoTest {
    
    private SQLDatabase database;
    private SQLAccountDao accountDao;
    
    public SQLAccountDaoTest() {
        this.database = new SQLDatabase("jdbc:h2:./analytica_test_db");
        this.accountDao = new SQLAccountDao(database);
    }        
    
    @Before
    public void setUp() {
        this.database.clearDatabase();
    }       
    
    @Test
    public void accountCanBeCreated() {
        Account account = new Account("Test", "User");
        this.accountDao.create(account);
        assertEquals(1, this.accountDao.getAll().size());
    }
    
    @Test
    public void accountCanBeFetchedWithUseranem() {
        Account account = new Account("Test", "User");
        this.accountDao.create(account);
        assertEquals(account, this.accountDao.getByUsername(account.getUsername()));
    }
    
    @Test
    public void getAllReturnsListOfAccounts() {
        Account account1 = new Account("Test1", "User");
        Account account2 = new Account("Test2", "User");
        Account account3 = new Account("Test3", "User");
        
        this.accountDao.create(account1);
        this.accountDao.create(account2);
        this.accountDao.create(account3);
        
        assertEquals(3, this.accountDao.getAll().size());
    }
       
    
}
