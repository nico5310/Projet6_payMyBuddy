package com.nico5310.PayMyBuddy.service;

import com.nico5310.PayMyBuddy.model.Account;
import com.nico5310.PayMyBuddy.model.User;
import com.nico5310.PayMyBuddy.repository.AccountRepository;
import com.nico5310.PayMyBuddy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;


    public List<Account> findAllAccounts() {
        return accountRepository.findAll();
    }

    public Optional<Account> findAccountById(Integer id) {
        return accountRepository.findById(id);
    }

    public Account updateAccount(Account updateAccount) {
        return accountRepository.save(updateAccount);
    }

    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

    public void deleteAccountById(Integer id) {
        accountRepository.deleteById(id);
    }

}
