package com.nico5310.PayMyBuddy.PayMyBuddy.Integration;

import com.nico5310.PayMyBuddy.PayMyBuddy.Config.DataBasePreparation;
import com.nico5310.PayMyBuddy.model.Account;
import com.nico5310.PayMyBuddy.repository.AccountRepository;
import com.nico5310.PayMyBuddy.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class AccountIT {

    @Autowired
    private DataBasePreparation dataBasePreparation;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;


    @Test
    public void saveAccountTest (){
        //GIVEN
        dataBasePreparation.setUp();
        //WHEN
        Account account = accountRepository.findAccountByUserEmail("nico@gmail.com").getUser().getAccount();
        //THEN
        assertThat(account.getIban()).isEqualTo("FR4412345678912345678912");
    }


}
