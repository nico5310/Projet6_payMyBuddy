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
    @Autowired
    private UserRepository userRepository;


    public List<Account> findAllAccounts() {
        return accountRepository.findAll();
    }

    public Account findAccountByEmail(String email) {
        return accountRepository.findAccountByUserEmail(email);
    }

    public Optional<Account> findAccountById(Integer id) {
        return accountRepository.findById(id);
    }

    public void  saveAccount(Integer id, Account account) {
        account.setUser(userRepository.findById(id).get());
        accountRepository.save(account);
    }

    public void deleteAccountById(Integer id) {
        accountRepository.deleteById(id);
    }


}
