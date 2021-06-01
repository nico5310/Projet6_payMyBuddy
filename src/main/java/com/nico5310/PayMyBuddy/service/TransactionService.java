package com.nico5310.PayMyBuddy.service;

import com.nico5310.PayMyBuddy.model.Transaction;
import com.nico5310.PayMyBuddy.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;


    public List<Transaction> findAllTransactions() {

        return transactionRepository.findAll();
    }

}
