package pl.coderslab.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Account;
import pl.coderslab.entity.Operation;
import pl.coderslab.repository.AccountRepository;
import pl.coderslab.repository.UserRepository;
import pl.coderslab.service.AccountService;

import java.util.List;


/**
 * Service with CRUD methods for actions on database table accounts. All methods names are very descriptive.
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    UserRepository userRepository;


    @Autowired
    AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository=accountRepository;
    }

    public List<Account> findAllAccounts() {
        return accountRepository.findAll();
    }

    public Account findAccountById(Long id) {
        return accountRepository.findById(id).orElseGet(null);
    }

    public List<Account> findAllAccountsByUserId(Long id) {
        return accountRepository.findAllByUserId(id);
    }

    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

    public Account editAccount(Account account) {
        return accountRepository.save(account);
    }

    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

    public Account findActiveUserAccount(Long id) {
        return accountRepository.findOneByUserIdAndActiveStatusIsTrue(id);
    }

    @Override
    public void activateUserAccount(Long user_id, Account account) {

        Account activeAccount = accountRepository.findOneByUserIdAndActiveStatusIsTrue(user_id);

        if (activeAccount!=null) {
            activeAccount.setActiveStatus(false);
            accountRepository.save(activeAccount);
        }

        account.setUser(userRepository.findById(user_id).orElse(null));
        account.setActiveStatus(true);
        accountRepository.save(account);
    }

}
