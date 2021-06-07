package com.nico5310.PayMyBuddy.controller;

import com.nico5310.PayMyBuddy.model.Account;
import com.nico5310.PayMyBuddy.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping(value = "/accounts")
    public List<Account> findAll() {

        return accountService.findAllAccounts();
    }

    @GetMapping(value = "/accounts/{email}")
    public Account findByEmail(@PathParam("email") String email) {

        return accountService.findAccountByEmail(email);
    }

    @PostMapping(value = "/accounts/{id}")
    public void saveAccount(@PathParam("id") Integer id, @RequestBody Account account) {

        accountService.saveAccount(id, account);
    }

    @DeleteMapping(value = "/accounts/{id}")
    public void deleteAccount(@PathParam("id") Integer id) {

        accountService.deleteAccountById(id);
    }


}
