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
    private AccountRepository accountRepository;
    @Autowired
    private UserRepository    userRepository;


    public List<Account> findAllAccounts() {

        log.info("Get all accounts");
        return accountRepository.findAll();
    }

    public Account findAccountByEmail(String email) {

        log.info("Get account by email");
        return accountRepository.findAccountByUserEmail(email);
    }

    public Account findAccountById(Integer idAccount) {

        log.info("Get account by id");
        return accountRepository.findById(idAccount).orElseThrow(() -> new NoFoundException("Account doesn't exist"));
    }

    public void saveAccount(Integer idAccount, Account account) {

        if(accountRepository.findAccountByUserEmail(account.getIban())!=null) {
            throw new NoFoundException("Account does exist!");
        }
        log.info("Create a new account with ");
        account.setUser(userRepository.findById(idAccount).get());
        accountRepository.save(account);
    }

    public void deleteAccountById(Integer idAccount) {
        log.info("Delete account by id");
        accountRepository.deleteById(idAccount);
    }


}
