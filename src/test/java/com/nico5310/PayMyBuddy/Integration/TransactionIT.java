package com.nico5310.PayMyBuddy.Integration;

import com.nico5310.PayMyBuddy.model.Transaction;
import com.nico5310.PayMyBuddy.model.User;
import com.nico5310.PayMyBuddy.repository.AccountRepository;
import com.nico5310.PayMyBuddy.repository.TransactionRepository;
import com.nico5310.PayMyBuddy.repository.UserRepository;
import com.nico5310.PayMyBuddy.service.AccountService;
import com.nico5310.PayMyBuddy.service.TransactionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class TransactionIT {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;


    @Test
    @DisplayName("Test findAllTransactions to TransactionServiceIT ")
    public void findAllTransactionsTest() {
        //GIVEN
        Transaction transaction = new Transaction();
        transaction.setId(1);
        transaction.setAmountTransaction(500.0);
        transactionRepository.save(transaction);
        //WHEN

        //THEN
        Assertions.assertEquals(500.0, transactionService.findAllTransactions().get(0).getAmountTransaction());

    }

    @Test
    @DisplayName("Test findTransactionById to TransactionServiceIT ")
    public void findTransactionByIdTest() {
        //GIVEN
        Transaction transaction = new Transaction();
        transaction.setId(1);
        transaction.setAmountTransaction(500.0);
        transactionRepository.save(transaction);
        //WHEN

        //THEN
        Assertions.assertEquals(500.0, transactionService.findTransactionById(1).getAmountTransaction());

    }

    @Test
    @DisplayName("Test transfer to TransactionServiceIT ")
    public void transferTest() {
        //GIVEN
        User user1 = new User();
        user1.setId(1);
        user1.setFirstName("Nicolas");
        user1.setLastName("Biancucci");
        user1.setEmail("nico@gmail.com");
        user1.setPassword("azerty");
        user1.setBalance(10000.0);
        userRepository.save(user1);

        User user2 = new User();
        user2.setId(2);
        user2.setFirstName("James");
        user2.setLastName("Bond");
        user2.setEmail("james@007.com");
        user2.setPassword("spectre");
        user2.setBalance(1000.0);
        userRepository.save(user2);

        //WHEN
        transactionService.transfer(user1.getEmail(), user2.getEmail(), LocalDate.now(), 2000.0,  "virement");

    //THEN
        Assertions.assertEquals(7990.0, userRepository.findById(1).get().getBalance());
        Assertions.assertEquals(3000.0, userRepository.findById(2).get().getBalance());

    }

}
