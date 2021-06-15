package com.nico5310.PayMyBuddy.controller;

import com.nico5310.PayMyBuddy.model.Transaction;
import com.nico5310.PayMyBuddy.model.User;
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

        List<Transaction> transactions = transactionService.findTransactionsOfUserPrincipal(user);

        model.addAttribute("user", user.getFirstName());
        model.addAttribute("balance", user.getBalance());
        model.addAttribute("transactions", transactions);
        return "homepage";
    }




}
