package com.nico5310.PayMyBuddy.controller;

import com.nico5310.PayMyBuddy.model.Transaction;
import com.nico5310.PayMyBuddy.model.User;
import com.nico5310.PayMyBuddy.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value= "/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;


    private List<Transaction> transactions;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

     @GetMapping(value = "/transfer")
    public String transferPage(@AuthenticationPrincipal User user, Model model) {

        transactions = transactionService.findTransactionsOfUserPrincipal(user);
        model.addAttribute("transfer", transactions);
        return "transfer";
    }

    @PostMapping(value = "/transfer")
    public void transfer(@RequestParam(name = "emailSender") String emailSender, @RequestParam(name = "emailRecipient") String emailRecipient, @RequestParam(name = "date")
    LocalDate date, @RequestParam(name = "amount") Double amountTransaction, @RequestParam(name = "description") String description) {

        transactionService.transfer(emailSender, emailRecipient, date, amountTransaction, description);
    }

    @PostMapping("/save")
    public String saveTransaction(@AuthenticationPrincipal User user, @ModelAttribute("transactions") Transaction transaction, Model model) {
        transaction.setSenderUser(user);
        transactionService.saveTransaction(transaction);

        return "redirect:/homepage";
    }

}
