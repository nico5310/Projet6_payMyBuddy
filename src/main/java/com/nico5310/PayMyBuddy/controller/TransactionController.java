package com.nico5310.PayMyBuddy.controller;

import com.nico5310.PayMyBuddy.model.Transaction;
import com.nico5310.PayMyBuddy.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping(value = "/transactions")
    public List<Transaction> findAll() {

        return transactionService.findAllTransactions();
    }

}
