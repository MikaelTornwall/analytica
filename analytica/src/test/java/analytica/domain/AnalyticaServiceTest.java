package analytica.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class AnalyticaServiceTest {
        
    private AnalyticaService service;
    
    /*
    @Before
    public void setUp() {
        this.service = new AnalyticaService();
    }       
    
    @Test
    public void userIsNullByDefault() {
        assertEquals(null, this.service.getUser());
    }
    
    @Test
    public void ifNoUsersUsersIsEmpty() {
        assertTrue(this.service.usersIsEmpty());
    }
    
    @Test
    public void ifUsersThenUsersNotEmpty() {
        Account user = new Account("Test", "User");
        this.service.addUser(user);
        assertFalse(this.service.usersIsEmpty());
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
        this.service.addUser(user);
        assertTrue(this.service.userExists(user));        
    }
    
    @Test
    public void loginReturnsTrueWithCorrectUsernameAndPassword() {
        Account user = new Account("Test", "User");
        this.service.addUser(user);
        assertTrue(this.service.login("Test", "User"));
    }       
    
    @Test
    public void newUserCanBeCreated() {
        assertTrue(this.service.createUser("Test", "User"));
    }
    
    @Test
    public void duplicateUsernameIsNotAllowed() {
        this.service.createUser("Test", "User");
        assertFalse(this.service.createUser("Test", "Password"));
    }
    
    */
}
