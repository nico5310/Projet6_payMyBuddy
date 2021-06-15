package com.nico5310.PayMyBuddy.repository;

import com.nico5310.PayMyBuddy.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {


    List<Transaction> findTransactionsBySenderUserEmail(String email);

    List<Transaction> findTransactionsByRecipientUserEmail(String email);
}
