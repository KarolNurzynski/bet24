package pl.coderslab.service;

import org.springframework.stereotype.Service;
import pl.coderslab.entity.Account;

import java.util.List;

@Service
public interface AccountService {

    public List<Account> findAllAccounts();

    public Account findAccountById(Long id);

    public Account saveAccount(Account account);

    public Account editAccount(Account account);

    public void deleteAccount(Long id);

}
