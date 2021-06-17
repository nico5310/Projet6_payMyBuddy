package com.nico5310.PayMyBuddy.service;


import com.nico5310.PayMyBuddy.model.Transaction;

import com.nico5310.PayMyBuddy.model.User;
import com.nico5310.PayMyBuddy.repository.TransactionRepository;
import com.nico5310.PayMyBuddy.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDate;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Log4j2
@Service
@Transactional
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private FeeRepository feeRepository;
//
//
//    private static  final double FEE = 0.5;
//
//
//    private double calculateFee(double amountFee) {
//        return amountFee * (FEE/100);
//    }


    public List<Transaction> findTransactionsOfUserPrincipal(User user) {
        List<Transaction> senderUserList = transactionRepository.findTransactionsBySenderUserEmail(user.getEmail());
        List<Transaction> receiverUserList = transactionRepository.findTransactionsByRecipientUserEmail(user.getEmail());
        List<Transaction> fullUserList = Stream.of(senderUserList, receiverUserList).flatMap((x->x.stream())).collect(Collectors
                .toList());
        return fullUserList;
    }

    //
    //    public Fee saveFee(Transaction transaction) {
    //        Fee fee = new Fee();
    //        fee.setTransaction(transaction);
    //        fee.setAmountFee(calculateFee(transaction.getAmountTransaction()));
    //        fee.setPercentFee(FEE);
    //        fee.setDate(LocalDate.now());
    //        fee.setUser(transaction.getSenderUser());
    //        return feeRepository.save(fee);
    //    }

    public Transaction saveTransaction(Transaction transaction) {

        log.info("Create a new transaction");
        transaction.setDate(LocalDate.now());
        return transactionRepository.save(transaction);
    }

//
//    @Transactional(rollbackOn = InsufficientFundsException.class)
//    public void saveTransactionWithFee(Transaction transaction) throws InsufficientFundsException {
//        saveTransaction(transaction);
//        Fee fee = saveFee(transaction);
//
//        if (transaction.getSenderUser().getBalance() - transaction.getAmountTransaction() - fee.getAmountFee() <0) {
//            throw new InsufficientFundsException("balance lower for transaction");
//        }
//        transaction.getSenderUser().setBalance(transaction.getSenderUser().getBalance()-transaction.getAmountTransaction()-fee.getAmountFee());
//        userRepository.save(transaction.getSenderUser());
//
//        transaction.getRecipientUser().setBalance(transaction.getRecipientUser().getBalance()+ transaction.getAmountTransaction());
//        userRepository.save(transaction.getRecipientUser());
//
//    }



    // METHOD TRANSFER

    public void transfer(String emailSender, String emailRecipient, LocalDate date, Double amountTransaction, String description) {
        log.info("Make a bank TransferController of :" + amountTransaction);
        User userSender    = userRepository.findByEmail(emailSender);
        User userRecipient = userRepository.findByEmail(emailRecipient);
        LocalDate localDate = LocalDate.now();

        if (userRecipient == null) {
            throw new RuntimeException("Impossible to make a TransferController, user is not one of the contacts");
        }
        else if (userSender.getBalance() - ((amountTransaction)+(amountTransaction*0.005)) < 0) {
            throw new RuntimeException("balance lower for transaction");
        }
        else {
            userSender.setBalance(userSender.getBalance() - ((amountTransaction) + (amountTransaction*0.005)));
            userRepository.save(userSender);

            userRecipient.setBalance(userRecipient.getBalance() + amountTransaction);
            userRepository.save(userRecipient);

            Transaction transaction = new Transaction();
            transaction.setSenderUser(userSender);
            transaction.setRecipientUser(userRecipient);
            transaction.setDate(localDate);
            transaction.setAmountTransaction(amountTransaction);
            transaction.setDescription(description);
            transactionRepository.save(transaction);

        }

    }


}
