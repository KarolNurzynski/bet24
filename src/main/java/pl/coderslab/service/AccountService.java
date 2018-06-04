package pl.coderslab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Account;
import pl.coderslab.repository.AccountRepository;
import pl.coderslab.serviceInterface.AccountServiceInterface;

import java.util.List;

@Service
public class AccountService implements AccountServiceInterface {

    AccountRepository accountRepository;

    @Autowired
    AccountService(AccountRepository accountRepository) {
        this.accountRepository=accountRepository;
    }

    public List<Account> findAllAccounts() {
        return accountRepository.findAll();
    }

    public Account findAccountById(Long id) {
        return accountRepository.findById(id).orElseGet(null);
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



}
