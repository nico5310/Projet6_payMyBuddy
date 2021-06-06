package com.nico5310.PayMyBuddy.Integration;

import com.nico5310.PayMyBuddy.Config.DataBasePreparation;
import com.nico5310.PayMyBuddy.model.Account;
import com.nico5310.PayMyBuddy.repository.*;
import com.nico5310.PayMyBuddy.service.MovementService;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class AccountIT {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveAccountTest() {

        Account account = new Account();
        account.setIban("FR4401234567890123456780000");
        account.setUser(userRepository.getById(1));

        assertThat(account.getIban().equals("FR4401234567890123456780000"));

    }



}
