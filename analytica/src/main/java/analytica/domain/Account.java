package analytica.domain;

import java.util.Objects;

/**
 * Account class
 * 
 * @author Mikael Törnwall
 */

public class Account {
    private int id;
    private String username;
    private String password;
    
    public Account(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
    
    public Account(String username, String password) {
        this(-1, username, password);
    }
    
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getUsername() {
        return this.username;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * Method checks if a given password matches with the account password
     * 
     * @param password string as a parameter
     * 
     * @return method return true if password matches account password, false otherwise
     */
    
    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.username);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Account other = (Account) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return true;
    }      
}
