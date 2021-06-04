package com.nico5310.PayMyBuddy.PayMyBuddy.Config;

import com.nico5310.PayMyBuddy.model.Account;
import com.nico5310.PayMyBuddy.model.User;
import com.nico5310.PayMyBuddy.repository.AccountRepository;
import com.nico5310.PayMyBuddy.repository.TransactionRepository;
import com.nico5310.PayMyBuddy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
public class DataBasePreparation {

    @Autowired
    private UserRepository  userRepository;

    @Autowired
    private AccountRepository accountRepository;

     public void setUp () {

        //create user1
        User user1 = new User();
        user1.setId(1);
        user1.setFirstName("Nicolas");
        user1.setLastName("Biancucci");
        user1.setEmail("nico@gmail.com");
        user1.setPassword("azerty");
        user1.setBalance(10000.0);
        userRepository.save(user1);

        // create account
        Account account1 = new Account();
        account1.setId(1);
        account1.setIban("FR4412345678912345678912");
        account1.setUser(user1);

        accountRepository.save(account1);

        //create user2
        User user2 = new User();
        user2.setId(2);
        user2.setFirstName("James");
        user2.setLastName("Bond");
        user2.setEmail("james@007.com");
        user2.setPassword("aston");
        user2.setBalance(0.0);
        userRepository.save(user2);

        // create account
        Account account2 = new Account();
        account2.setId(2);
        account2.setIban("FR44123456789123456789100");
        account2.setUser(user2);
        accountRepository.save(account2);


        //create user3
        User user3 = new User();
        user3.setId(3);
        user3.setFirstName("Bibendum");
        user3.setLastName("Michelin");
        user3.setEmail("bibendum@michelin.com");
        user3.setPassword("primacy");
        user3.setBalance(0.0);
        userRepository.save(user3);

        // create account
        Account account3 = new Account();
        account3.setId(3);
        account3.setIban("FR44123456789123456789777");
        account3.setUser(user3);
        accountRepository.save(account3);
    }




}
