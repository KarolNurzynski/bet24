package pl.coderslab.service;

import org.springframework.stereotype.Service;
import pl.coderslab.entity.Account;

import java.util.List;

/**
 * Interface with CRUD methods for actions on database table accounts. All methods names are very descriptive.
 */
@Service
public interface AccountService {


    public Account findAccountById(Long id);

    public List<Account> findAllAccounts();

    public Account saveAccount(Account account);

    public Account editAccount(Account account);

    public void deleteAccount(Long id);

    public List<Account> findAllAccountsByUserId(Long id);

    public Account findActiveUserAccount(Long id);

    public void activateUserAccount(Long user_id, Account account);

}
