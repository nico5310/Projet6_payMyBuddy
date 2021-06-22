package com.nico5310.PayMyBuddy.Integration;

import com.nico5310.PayMyBuddy.model.Account;
import com.nico5310.PayMyBuddy.model.Transaction;
import com.nico5310.PayMyBuddy.model.User;
import com.nico5310.PayMyBuddy.repository.TransactionRepository;
import com.nico5310.PayMyBuddy.repository.UserRepository;
import com.nico5310.PayMyBuddy.service.TransactionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class TransactionIT {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionService transactionService;


    @Test
    @DisplayName("Test findTransactionsOfUserPrincipal to TransactionServiceIT ")
    public void findTransactionsOfUserPrincipalTest() {
        //GIVEN
        User user = new User();
        user.setId(1);
        user.setFirstName("Nicolas");
        user.setLastName("Biancucci");
        user.setEmail("nico@gmail.com");
        user.setPassword("azerty");
        user.setBalance(10000.0);
        user.setAccount(new Account());
        user.setContactList(null);
        user.setEnabled(true);
        user.setRole("USER");
        userRepository.save(user);

        User user2 = new User();
        user2.setId(2);
        user2.setFirstName("James");
        user2.setLastName("Bond");
        user2.setEmail("james@007.com");
        user2.setPassword("spectre");
        user2.setBalance(10000.0);
        user2.setAccount(new Account());
        user2.setContactList(null);
        user2.setEnabled(true);
        user2.setRole("USER");
        userRepository.save(user2);

        Transaction transaction = new Transaction();
        transaction.setId(1);
        transaction.setDate(LocalDate.now());
        transaction.setAmountTransaction(500.0);
        transaction.setSenderUser(user);
        transaction.setRecipientUser(user2);
        transactionRepository.save(transaction);

        //WHEN

        //THEN
        Assertions.assertEquals(transactionService.findTransactionsOfUserPrincipal(user).get(0).getAmountTransaction(), 500.0);

    }


    @Test
    @DisplayName("Test TransferController to TransactionServiceIT ")
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
