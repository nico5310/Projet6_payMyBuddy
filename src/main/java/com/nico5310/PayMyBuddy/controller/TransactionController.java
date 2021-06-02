package com.nico5310.PayMyBuddy.controller;

import com.nico5310.PayMyBuddy.model.Transaction;
import com.nico5310.PayMyBuddy.service.TransactionService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@Log4j2
@RestController
@Transactional
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping(value = "/transactions")
    public List<Transaction> findAll() {
        log.info("Get all transactions");
        return transactionService.findAllTransactions();
    }

    @GetMapping(value = "/transactions/{idTransaction}")
    public Transaction findTransactionById (@PathVariable (value = "idTransaction") Integer idTransaction) {
        log.info("Get transaction by idTransaction");
        return transactionService.findTransactionById(idTransaction);
    }

    @PostMapping(value = "/transactions")
    public Transaction createTransaction (@Valid @RequestBody Transaction transaction) {
        log.info("Create a new Transaction");
        return transactionService.createTransaction(transaction);
    }


}
