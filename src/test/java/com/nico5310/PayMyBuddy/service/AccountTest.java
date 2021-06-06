package com.nico5310.PayMyBuddy.service;

import com.nico5310.PayMyBuddy.Config.DataBasePreparation;
import com.nico5310.PayMyBuddy.model.Account;
import com.nico5310.PayMyBuddy.model.User;
import com.nico5310.PayMyBuddy.repository.AccountRepository;
import com.nico5310.PayMyBuddy.repository.UserRepository;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
public class AccountTest {



    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;



    @Test
    @DisplayName("Test findAllAccounts to AccountService")
    public void findAllAccountsTest (){

        List<Account> accountList = this.accountRepository.findAll();

        assertEquals(2, accountList.size());
    }

    @Test
    @DisplayName("Test accountById to AccountService")
    void findAccountByIdTest()  {

        Account accountList = this.accountRepository.findAccountById(1);
        assertEquals(1, accountList);
    }

    @Test
    @DisplayName("Test findAccountByEmail to AccountService")
    void findAccountByEmailTest2() {

        Account account = this.accountRepository.findAccountByUserEmail("nico@gmail.com");
        assertThat(account.getIban()).isEqualTo("FR4401234567890123456780000");
    }


    @Test
    @DisplayName("Test saveAccount to UserService")
    void saveAccountTest() {

        Account account = new Account();
        account.setIban("FR4401234567890123456780000");
        account.setUser(userRepository.getById(1));

        assertThat(account.getIban().equals("FR4401234567890123456780000"));
    }

    @Test
    @DisplayName("Test updateUser to UserService")
    void updateUser() {

        Optional<User> user = userRepository.findById(2);
        user.get().setPassword("spectre");
        userRepository.save(user.get());

        Optional<User> userList = this.userRepository.findById(2);
        assertEquals("spectre", userList.get().getPassword());
    }


    @Test
    public void findAccountByEmailTest (){
        //GIVEN
        User user = new User();
        user.setId(1);
        user.setFirstName("Nicolas");
        user.setLastName("Biancucci");
        user.setEmail("nico@gmail.com");
        user.setPassword("azerty");
        user.setBalance(10000.0);
        userRepository.save(user);
        Account account = new Account();
        account.setId(1);
        account.setIban("FR4401234567890123456780000");
        account.setUser(user);
        accountRepository.save(account);
        //WHEN
        accountRepository.findAccountByUserEmail("nico@gmail.com").getUser().getAccount();
        //THEN
        assertThat(account.getIban()).isEqualTo("FR4401234567890123456780000");
    }


}
