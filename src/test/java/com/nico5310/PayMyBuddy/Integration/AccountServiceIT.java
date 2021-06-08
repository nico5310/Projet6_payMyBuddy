package com.nico5310.PayMyBuddy.Integration;

import com.nico5310.PayMyBuddy.model.Account;
import com.nico5310.PayMyBuddy.model.User;
import com.nico5310.PayMyBuddy.repository.AccountRepository;
import com.nico5310.PayMyBuddy.repository.UserRepository;
import com.nico5310.PayMyBuddy.service.AccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
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

    @Test
    @DisplayName("Test findAllAccounts to AccountService")
    public void findAllAccountsTest() {
        //GIVEN
        Account account = new Account();
        account.setId(1);
        account.setIban("FR4401234567890123456780000");

        accountRepository.save(account);
        //WHEN

        //THEN
        Assertions.assertTrue(accountService.findAllAccounts().get(0).getIban().contains("FR4401234567890123456780000"));

    }

    @Test
    @DisplayName("Test findAccountByEmail to AccountService")
    public void findAccountByEmailTest() {
        //GIVEN
        User user = new User();
        user.setEmail("nico@gmail.com");
        userRepository.save(user);
        Account account = new Account();
        account.setId(1);
        account.setIban("FR4401234567890123456780000");
        account.setUser(user);
        accountRepository.save(account);
        //WHEN

        //THEN
        Assertions.assertTrue(accountService.findAccountByEmail(user.getEmail()).getIban().contains("FR4401234567890123456780000"));

    }

    @Test
    @DisplayName("Test findAccountById to AccountService")
    public void findAccountByIdTest() {
        //GIVEN
        Account account = new Account();
        account.setId(1);
        account.setIban("FR4401234567890123456780000");
        accountRepository.save(account);
        //WHEN

        //THEN
        Assertions.assertTrue(accountService.findAccountById(1).getIban().contains("FR4401234567890123456780000"));

    }


    @Test
    @DisplayName("Test saveAccountTest to AccountService")
    public void saveAccountTest() {

        Account account = new Account();
        account.setIban("FR4401234567890123456780000");
        account.setUser(userRepository.getById(1));

        assertThat(account.getIban().equals("FR4401234567890123456780000"));

    }

    @Test
    @DisplayName("Test deleteAccountById to AccountService")
    public void deleteAccountByIdTest() {

        //GIVEN
        User user = new User();
        user.setEmail("nico@gmail.com");
        userRepository.save(user);
        Account account = new Account();
        account.setId(1);
        account.setIban("FR4401234567890123456780000");
        account.setUser(user);
        accountService.saveAccount(user.getId(),account);
        //WHEN
        Assertions.assertTrue(accountService.findAccountByEmail(user.getEmail()).getIban().contains("FR4401234567890123456780000"));
        accountService.deleteAccountById(account.getId());
        //THEN
        Assertions.assertFalse(accountRepository.existsById(1));
    }

}
