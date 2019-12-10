package analytica.dao;

/**
 * Interface for account dao 
 * @author Mikael Törnwall
 */

import analytica.domain.Account;
import java.util.List;
import java.sql.SQLException;

public interface AccountDao {
    void create(Account account) throws SQLException;
    Account getAccountByUsername(String username) throws SQLException;
    List<Account> getUsers() throws SQLException;
}
