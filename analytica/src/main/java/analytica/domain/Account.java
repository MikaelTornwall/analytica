package analytica.domain;

import java.util.Objects;

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
    
    public String getUsername() {
        return this.username;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
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
