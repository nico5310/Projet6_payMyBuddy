package com.nico5310.PayMyBuddy.controller;

import com.nico5310.PayMyBuddy.model.Transaction;
import com.nico5310.PayMyBuddy.model.User;
import com.nico5310.PayMyBuddy.service.TransactionService;
import com.nico5310.PayMyBuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value= "/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserService userService;

    /**
     * List of Transaction object
     */
    private List<Transaction> transactions;


    @GetMapping(value = "")
    public List<Transaction> findAll() {
        return transactionService.findAllTransactions();
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

        transactions = transactionService.findTransactionsOfUserPrincipal(user);
        model.addAttribute("transfer", transactions);
        return "transfer";
    }

    @PostMapping(value = "/transfer")
    public void transfer(@RequestParam(name = "emailSender") String emailSender, @RequestParam(name = "emailReceiver") String emailRecipient, @RequestParam(name = "date")
    LocalDate date, @RequestParam(name = "amount") Double amountTransaction, @RequestParam(name = "description") String description) {

        transactionService.transfer(emailSender, emailRecipient, date, amountTransaction, description);
    }


}
