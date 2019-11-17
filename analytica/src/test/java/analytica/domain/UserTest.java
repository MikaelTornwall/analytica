package analytica.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest {
    
    private User user;
            
    @Before
    public void setUp() {
        String username = "username";
        String password = "password";
        
        this.user = new User(username, password);
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
        User newUser = new User("username", "secret");
        assertTrue(this.user.equals(newUser));
    }
    
    @Test
    public void usersAreNotEqualWithDifferentUsername() {
        User notUser = new User("notUser", "password");
        assertFalse(this.user.equals(notUser));
    }
}
