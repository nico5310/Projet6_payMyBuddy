package com.nico5310.PayMyBuddy.service;

import com.nico5310.PayMyBuddy.exception.NoFoundException;
import com.nico5310.PayMyBuddy.model.Account;
import com.nico5310.PayMyBuddy.repository.AccountRepository;
import com.nico5310.PayMyBuddy.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Log4j2
@Service
@Transactional
public class AccountService {

    @Autowired
    private final AccountRepository accountRepository;
    @Autowired
    private final UserRepository    userRepository;

    public AccountService(AccountRepository accountRepository, UserRepository userRepository) {

        this.accountRepository = accountRepository;
        this.userRepository    = userRepository;
    }


    public List<Account> findAllAccounts() {

        log.info("Get all accounts");
        return accountRepository.findAll();
    }

    public Account findAccountByEmail(String email) {

        log.info("Get account by email");
        return accountRepository.findAccountByUserEmail(email);
    }

    public Account findAccountById(Integer id) {

        log.info("Get account by id");
        return accountRepository.findById(id).orElseThrow(() -> new NoFoundException("Account doesn't exist"));
    }

    public List<Account> findByUserId(Integer id) {

        log.info("Get account by id");
        return accountRepository.findByUserId(id);
    }

    public void saveAccount(Integer id, Account account) {

        if (accountRepository.findAccountByUserEmail(account.getIban()) != null) {
            throw new NoFoundException("Account does exist!");
        }
        log.info("Create a new account with ");
        account.setUser(userRepository.findById(id).get());
        accountRepository.save(account);

    }


    public void deleteAccountById(Integer id) {

        log.info("Delete account by id");
        accountRepository.deleteById(id);
    }


}
