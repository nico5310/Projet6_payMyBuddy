package com.nico5310.PayMyBuddy.controller;

import com.nico5310.PayMyBuddy.model.Account;
import com.nico5310.PayMyBuddy.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Account Controller
 * @author Nicolas
 */
@RestController
@RequestMapping("/accounts")
public class AccountController {

    /**
     * @see AccountService
     */
    @Autowired
    private AccountService accountService;

    /**
     *Find all acounts
     * @return all accounts
     */
    @GetMapping
    public List<Account> findAll() {

        return accountService.findAllAccounts();
    }

    /**
     * find account by email
     * @param email of account to find
     * @return an account corresponding to the email
     */
    @GetMapping(value = "/{email}")
    public Account findByEmail(@PathVariable String email) {

        return accountService.findAccountByEmail(email);
    }

    /**
     * save new account
     * @param id of account
     * @param account the account
     * @return a save account
     */
    @PostMapping(value = "/{id}")
    public Account saveAccount(@PathVariable Integer id, @RequestBody Account account) {

        return accountService.saveAccount(id, account);
    }

    /**
     * delete account
     * @param id of account delete
     */
    @DeleteMapping(value = "/{id}")
    public void deleteAccount(@PathVariable Integer id) {

        accountService.deleteAccountById(id);
    }


}
