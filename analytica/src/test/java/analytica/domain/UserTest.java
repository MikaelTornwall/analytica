package analytica.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest {
    
    private Account user;
            
    @Before
    public void setUp() {
        String username = "username";
        String password = "password";
        
        this.user = new Account(username, password);
    }
    
    @Test
    public void validPasswordIsAccepted() {
        String password = "password";
        assertTrue(this.user.checkPassword(password));
    }
    
    @Test
    public void invalidPasswordIsNotAccepted() {
        String password = "secret";
        assertFalse(this.user.checkPassword(password));
    }
    
    @Test
    public void usersAreEqualWithSameUsername() {
        Account newUser = new Account("username", "secret");
        assertTrue(this.user.equals(newUser));
    }
    
    @Test
    public void usersAreNotEqualWithDifferentUsername() {
        Account notUser = new Account("notUser", "password");
        assertFalse(this.user.equals(notUser));
    }
}
