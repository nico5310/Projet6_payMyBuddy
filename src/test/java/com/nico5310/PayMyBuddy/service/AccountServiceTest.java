package com.nico5310.PayMyBuddy.service;


import com.nico5310.PayMyBuddy.model.Account;
import com.nico5310.PayMyBuddy.model.User;
import com.nico5310.PayMyBuddy.repository.AccountRepository;
import com.nico5310.PayMyBuddy.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AccountService accountService;

    @BeforeEach
    void setup() {

        accountService = new AccountService(accountRepository, userRepository);

    }


    @Test
    @DisplayName("Test findAllAccounts to AccountService")
    public void findAllAccountsTest() {
        //GIVEN
        List<Account> accountList = new ArrayList<>();
        Account       account1    = new Account();
        account1.setId(1);
        account1.setIban("FR4401234567890123456780000");
        account1.setUser(userRepository.getById(1));
        accountList.add(account1);
        Account account2 = new Account();
        account2.setId(2);
        account2.setIban("FR4401234567890123456780001");
        account2.setUser(userRepository.getById(2));
        accountList.add(account2);
        //WHEN
        when(accountRepository.findAll()).thenReturn(accountList);
        //THEN
        Assertions.assertThat(accountService.findAllAccounts().size() == 2);
    }

    @Test
    @DisplayName("Test findAccountById to AccountService")
    public void findAccountByIdTest() {
        //GIVEN
        Account account1 = new Account();
        account1.setId(1);
        account1.setIban("FR4401234567890123456780000");
        account1.setUser(userRepository.getById(1));
        //WHEN
        when(accountRepository.findById(1)).thenReturn(java.util.Optional.of(account1));
        //THEN
        assertEquals(accountService.findAccountById(1).getId(), 1);
    }

    @Test
    @DisplayName("Test findAccountByEmail to AccountService")
    public void findAccountByEmailTest() {
        //GIVEN
        Account account1 = new Account();
        account1.setId(1);
        account1.setIban("FR4401234567890123456780000");
        account1.setUser(userRepository.getById(1));
        //WHEN
        when(accountRepository.findAccountByUserEmail(any(String.class))).thenReturn(account1);
        //THEN
        assertEquals(accountService.findAccountByEmail("").getIban(), "FR4401234567890123456780000");
    }

    @Test
    @DisplayName("Test saveAccount to AccountService")
    public void saveAccountTest() {
        //GIVEN
        User    user     = new User();
        user.setId(1);
        Account account1 = new Account();
        account1.setId(1);
        account1.setIban("FR4401234567890123456780000");
        account1.setUser(user);
        //WHEN
        when(accountRepository.save(account1)).thenReturn(account1);
        when(userRepository.findById(1)).thenReturn(java.util.Optional.of(user));
        accountService.saveAccount(1, account1);
        //THEN
        verify(userRepository, times(1)).findById(1);
        verify(accountRepository, times(1)).save(account1);
    }

    @Test
    @DisplayName("Test deleteAccountById to AccountService")
    public void deleteAccountByIdTest() {
        //GIVEN
        //GIVEN
        User    user     = new User();
        user.setId(1);
        Account account1 = new Account();
        account1.setId(1);
        account1.setIban("FR4401234567890123456780000");
        account1.setUser(user);
        //WHEN
        accountService.deleteAccountById(1);
        //THEN
        verify(accountRepository).deleteById(1);
    }

}
