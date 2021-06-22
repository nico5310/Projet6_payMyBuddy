package com.nico5310.PayMyBuddy.controller;

import com.nico5310.PayMyBuddy.model.Account;
import com.nico5310.PayMyBuddy.model.User;
import com.nico5310.PayMyBuddy.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/accounts")
public class AccountController {


    @Autowired
    private AccountService accountService;


    @GetMapping
    public List<Account> findAll() {

        return accountService.findAllAccounts();
    }

    @GetMapping(value = "/{email}")
    public Account findByEmail(@PathVariable String email) {

        return accountService.findAccountByEmail(email);
    }

    //////////////////////////////////
    @GetMapping(value = "/addAccount")
    public String addAccount(Model model) {

        Account account = new Account();
        model.addAttribute(account);
        return "accountregistration";
    }

    @GetMapping(value = "/save")
    public String saveAccount(@AuthenticationPrincipal User user, @ModelAttribute(value = "account") Account account) {

        account.setUser(user);
        accountService.saveAccount(user.getId(), account);
        return "redirect:/profile";
    }

    @GetMapping(value = "/deleteAccount")
    public String deleteAccount(@RequestParam("accountId") Integer id) {

        accountService.deleteAccountById(id);
        return "redirect:/profile";
    }

}
