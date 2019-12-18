package analytica.domain;

import analytica.domain.FakeSQLAccountDao;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AnalyticaServiceTest {
     
    private AccountService service;    
    
    @Before
    public void setUp() {
        this.service = new AccountService(new FakeSQLAccountDao());
    }       
    
    @Test
    public void userIsNullByDefault() {
        assertEquals(null, this.service.getUser());
    }        
    
    @Test
    public void currentUserCanBeSet() {
        Account user = new Account("Test", "User");
        this.service.setUser(user);
        assertEquals(user, this.service.getUser());
    }
    
    @Test
    public void ifAccountExistsAccountCanBeFound() {
        Account user = new Account("Test", "User");
        this.service.createAccount(user);
        assertTrue(this.service.accountExists(user));        
    }
    
    @Test
    public void loginReturnsTrueWithCorrectUsernameAndPassword() {
        Account user = new Account("Test", "User");
        this.service.createAccount(user);
        assertTrue(this.service.login("Test", "User"));
    }       
    
    @Test
    public void newAccountCanBeCreated() {
        assertTrue(this.service.createAccount(new Account("Test", "User")));
    }
    
    @Test
    public void duplicateUsernameIsNotAllowed() {
        this.service.createAccount(new Account("Test", "User"));
        assertFalse(this.service.createAccount(new Account("Test", "Password")));
    }
    
    @Test
    public void emptyListIsReturnedWhenNoAccountsExist() {        
        assertEquals(0, this.service.getAccounts().size());
    }
    
    @Test
    public void listOfOneAccountIsReturnedWhenOneAccountIsCreated() {
        this.service.createAccount(new Account("Test", "User"));
        assertEquals(1, this.service.getAccounts().size());
    }
    
    @Test
    public void listOfMultipleAccountsIsReturnedWhenMultipleAccountsAreCreated() {
        this.service.createAccount(new Account("Test", "User"));
        this.service.createAccount(new Account("Test2", "User2"));
        this.service.createAccount(new Account("Test3", "User3"));
        assertEquals(3, this.service.getAccounts().size());
    }
    
    @Test
    public void createdAccountIsOnTheListOfAllAccounts() {
        Account account = new Account("Test", "User");
        this.service.createAccount(account);
        assertEquals(this.service.getAccounts().get(0), account);
    }
    
}
