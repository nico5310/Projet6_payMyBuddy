package com.nico5310.PayMyBuddy.service;

import com.nico5310.PayMyBuddy.exception.NoFoundException;
import com.nico5310.PayMyBuddy.model.Transaction;
import com.nico5310.PayMyBuddy.model.User;
import com.nico5310.PayMyBuddy.repository.TransactionRepository;
import com.nico5310.PayMyBuddy.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Log4j2
@Service
@Transactional
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;


    public List<Transaction> findAllTransactions() {

        log.info("Get all transactions with contacts");
        return transactionRepository.findAll();
    }

    public Transaction findTransactionById(Integer idTransaction) {

        log.info("Get transaction by id");
        return transactionRepository.findById(idTransaction).orElseThrow(() -> new NoFoundException("Transaction doesn't exist"));
    }

    public Transaction createTransaction(Transaction transaction) {

        log.info("Create a new transaction");
        return transactionRepository.save(transaction);
    }


    // METHOD TRANSFER

    public void transfer(String emailSender, String emailRecipient, LocalDate date, Double amountTransaction, Double fee, String description) {
        log.info("Make a bank transfer of :" + amountTransaction);
        User userSender = userRepository.findByEmail(emailSender);
        User userRecipient = userRepository.findByEmail(emailRecipient);

        if (userRecipient == null) {
            throw new RuntimeException("Impossible to make a transfer, user is not one of the contacts");
        }
        else if (userSender.getBalance() - ((amountTransaction)+(fee)) < 0) {
            throw new RuntimeException("balance lower for transaction");
        }
        else {
            userSender.setBalance(userSender.getBalance() - ((amountTransaction) + (fee)));
            userRepository.save(userSender);

            userRecipient.setBalance(userRecipient.getBalance() + amountTransaction);
            userRepository.save(userRecipient);

            Transaction transaction = new Transaction();
            transaction.setSenderUser(userSender);
            transaction.setRecipientUser(userRecipient);
            transaction.setDate(LocalDate.now());
            transaction.setAmountTransaction(amountTransaction);
            transaction.setDescription(description);
            transactionRepository.save(transaction);

        }

    }


}
