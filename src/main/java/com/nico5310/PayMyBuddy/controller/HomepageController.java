package com.nico5310.PayMyBuddy.controller;

import com.nico5310.PayMyBuddy.model.*;
import com.nico5310.PayMyBuddy.service.AccountService;
import com.nico5310.PayMyBuddy.service.TransactionService;
import com.nico5310.PayMyBuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * HomePage controller
 *
 * @author Nicolas
 */
@Controller
public class HomepageController {

    /**
     * @see UserService
     */
    @Autowired
    UserService        userService;

    /**
     * @see TransactionService
     */
    @Autowired
    TransactionService transactionService;

    @Autowired
    AccountService accountService;

    /**
     * Open homepage view
     *
     * @param user
     *         the user
     * @param model
     *         the model
     *
     * @return the homepage form view
     */
    @GetMapping(value = "/homepage")
    public String homePage(@AuthenticationPrincipal User user, Model model) {

        List<Transaction> transactions = transactionService.findTransactionsOfUserPrincipal(user); // List Transactions

        model.addAttribute("user", user.getFirstName()); // Welcome + FirstName
        model.addAttribute("balance", user.getBalance()); // Amount of balance
        model.addAttribute("transactions", transactions); // List of transactions
        return "homepage";
    }

    /**
     * Open homepage view
     *
     * @param user
     *         the user
     * @param model
     *         the model
     *
     * @return the homepage form view
     */
    @GetMapping(value = "/transfer")
    public String transferPage(@AuthenticationPrincipal User user, Model model) {
        List<Transaction> transactions = transactionService.findTransactionsOfUserPrincipal(user);
        List<Contact> contactList = userService.findContactByUserEmail(user.getEmail());
        model.addAttribute("user", user.getFirstName());// Welcome + FirstName
        model.addAttribute("balance", user.getBalance());// Amount of balance
        model.addAttribute("userAddContact", userService.usersExceptFriends(user.getEmail())); // Add list contact
        model.addAttribute("contacts", contactList); // List contacts for send money
        model.addAttribute("transactions", transactions); // List of transactions
        return "transfer";
    }

    @GetMapping("/profile")
    public String getProfile(@AuthenticationPrincipal User user, Model model) {

        List<Account> userAccountList = accountService.findByUserId(user.getId()); // List account user
        List<Contact> contactList = userService.findContactByUserId(user.getId()); // List contact user

        model.addAttribute("contacts", contactList);
        model.addAttribute("accounts", userAccountList);
        model.addAttribute("movements", new Movement());

        return "profile";
    }



}
