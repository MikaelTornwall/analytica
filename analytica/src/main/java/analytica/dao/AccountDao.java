package analytica.dao;

/**
 * Interface for Account dao 
 * 
 * @author Mikael Törnwall
 */

import analytica.domain.Account;
import java.util.List;

public interface AccountDao {
    void create(Account account);
    Account getByUsername(String username);
    List<Account> getAll();
}
