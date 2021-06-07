package com.nico5310.PayMyBuddy.Integration;

import com.nico5310.PayMyBuddy.model.Account;
import com.nico5310.PayMyBuddy.repository.AccountRepository;
import com.nico5310.PayMyBuddy.repository.UserRepository;
import com.nico5310.PayMyBuddy.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AccountServiceIT {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountService accountService;

    @BeforeEach
    void setup() {

        accountService = new AccountService(accountRepository, userRepository);

    }

//    @Test
//    @DisplayName("Test findAccountByEmail to AccountService")
//    public void findAccountByEmailTest() {
//
//        Account       account1    = new Account();
//        account1.setId(1);
//        account1.setIban("10101010");
//        account1.setUser(userRepository.getById(1));
//
//        when
//
//        assertThat(accountService.findAllAccounts().size() == 2);
//    }

    @Test
    @DisplayName("Test saveAccountTest to AccountService")
    public void saveAccountTest() {

        Account account = new Account();
        account.setIban("FR4401234567890123456780000");
        account.setUser(userRepository.getById(1));

        assertThat(account.getIban().equals("FR4401234567890123456780000"));

    }

}
