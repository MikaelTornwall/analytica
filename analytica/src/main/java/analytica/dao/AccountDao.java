package analytica.dao;

import analytica.domain.Account;
import java.util.List;

/**
 * Interface for Account dao 
 * 
 * @author Mikael Törnwall
 */

public interface AccountDao {
    void create(Account account);
    Account getByUsername(String username);
    List<Account> getAll();
}
