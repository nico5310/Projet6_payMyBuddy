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

/**
 * Account Controller
 * @author Nicolas
 */
@Controller
@RequestMapping("/accounts")
public class AccountController {

    /**
     * @see AccountService
     */
    @Autowired
    private AccountService accountService;

    /**
     *Find all accounts
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
    /**
     * delete account
     * @param id of account delete
     */
    @GetMapping(value = "/deleteAccount")
    public String deleteAccount(@RequestParam("accountId") Integer id) {

        accountService.deleteAccountById(id);
        return "profile";
    }

}
