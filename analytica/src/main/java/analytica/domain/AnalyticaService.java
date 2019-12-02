package analytica.domain;

import java.util.Set;
import java.util.HashSet;

public class AnalyticaService {
    
    private User user;
    
    // Temporary solution before DB
    private Set<User> users;
    
    public AnalyticaService() {
        this.user = null;
        this.users = new HashSet<>();
        this.users.add(new User("Test", "User"));
    }
    
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public Set<User> getUsers() {
        return this.users;
    }
    
    public void addUser(User user) {
        if (!this.users.contains(user)) {
            this.users.add(user);
        }        
    }
    
    public boolean usersIsEmpty() {
        return this.users.isEmpty();
    }
    
    public boolean userExists(User user) {
        return this.users.contains(user);
    }
    
    public boolean login(String username, String password) {
        
        if (this.usersIsEmpty()) return false;
        
        this.users.stream().forEach(user -> {
                    if (user.getUsername().equals(username)) {
                        if (user.checkPassword(password)) {
                            this.setUser(user);
                        }
                    }
                });
        
        if (this.user == null) return false;
        
        return true;
    }
    
    public boolean createUser(String username, String password) {
        User newUser = new User(username, password);
        
        if (this.userExists(newUser)) return false;
                        
        this.addUser(newUser);              
        
        return true;
    }
}
