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
    public void userCanBeSet() {
        Account user = new Account("Test", "User");
        this.service.setUser(user);
        assertEquals(user, this.service.getUser());
    }
    
    @Test
    public void ifUserExistsUserCanBeFound() {
        Account user = new Account("Test", "User");
        this.service.createUser(user);
        assertTrue(this.service.userExists(user));        
    }
    
    @Test
    public void loginReturnsTrueWithCorrectUsernameAndPassword() {
        Account user = new Account("Test", "User");
        this.service.createUser(user);
        assertTrue(this.service.login("Test", "User"));
    }       
    
    @Test
    public void newUserCanBeCreated() {
        assertTrue(this.service.createUser(new Account("Test", "User")));
    }
    
    @Test
    public void duplicateUsernameIsNotAllowed() {
        this.service.createUser(new Account("Test", "User"));
        assertFalse(this.service.createUser(new Account("Test", "Password")));
    }
    
    
}
