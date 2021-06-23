package com.nico5310.PayMyBuddy.service;

import com.nico5310.PayMyBuddy.model.Account;
import com.nico5310.PayMyBuddy.model.Contact;
import com.nico5310.PayMyBuddy.model.Transaction;
import com.nico5310.PayMyBuddy.model.User;
import com.nico5310.PayMyBuddy.repository.AccountRepository;
import com.nico5310.PayMyBuddy.repository.TransactionRepository;
import com.nico5310.PayMyBuddy.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {TransactionService.class})
@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private AccountService accountService;

    @InjectMocks
    private TransactionService transactionService;

    @BeforeEach
    void setup() {

        accountService = new AccountService(accountRepository, userRepository);

    }

    @Test
    @DisplayName("Test FindAllTransactions to Transaction service")
    public void findTransactionsOfUserPrincipal() {
        //GIVEN
        User user = new User();
        user.setId(1);
        user.setFirstName("Nicolas");
        user.setLastName("Biancucci");
        user.setEmail("nico@gmail.com");
        user.setPassword("azerty");
        user.setBalance(10000.0);
        user.setAccount(new Account());
        user.setRole("USER");
        Transaction transaction = new Transaction();
        transaction.setId(1);
        transaction.setDate(LocalDate.now());
        transaction.setAmountTransaction(500.0);
        transaction.setDescription("aston martin");
        transaction.setSenderUser(new User());
        transaction.setRecipientUser(new User());
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(transaction);
        //WHEN
        when(transactionRepository.findTransactionsBySenderUserEmail(user.getEmail())).thenReturn(transactionList);
        //THEN
        Assertions.assertTrue(transactionService.findTransactionsOfUserPrincipal(user).size() > 0);
    }

    @Test
    @DisplayName("Test saveTransaction to Transaction service")
    public void saveTransactionTest() {
        //WHEN
        User    user    = new User();
        Account account = new Account();
        user.setId(1);
        user.setFirstName("Nicolas");
        user.setLastName("Biancucci");
        user.setEmail("nico@gmail.com");
        user.setPassword("azerty");
        user.setBalance(10000.0);
        user.setAccount(new Account());
        user.setRole("USER");
        user.setContactList(new ArrayList<Contact>());
        user.setEnabled(true);
        account.setId(1);
        account.setIban("FR4401234567890123456780000");
        account.setBalance(10.0);
        account.setUser(user);
        User    user1    = new User();
        Account account1 = new Account();
        user1.setId(2);
        user1.setFirstName("James");
        user1.setLastName("Bond");
        user1.setEmail("james@007.com");
        user1.setPassword("spectre");
        user1.setBalance(10000.0);
        user1.setAccount(account1);
        user1.setRole("USER");
        user1.setContactList(new ArrayList<Contact>());
        user1.setEnabled(true);
        account1.setId(2);
        account1.setIban("FR4401234567890123456780002");
        account1.setBalance(10000.0);
        account1.setUser(user1);
        Transaction transaction = new Transaction();
        transaction.setDate(LocalDate.now());
        transaction.setSenderUser(user);
        transaction.setRecipientUser(user1);
        transaction.setAmountTransaction(10.0);
        transaction.setId(1);
        transaction.setDescription("Aston martin");
        //WHEN
        when(transactionRepository.save((Transaction) any())).thenReturn(transaction);
        assertSame(transaction, transactionService.saveTransaction(new Transaction()));
        //THEN
        verify(transactionRepository).save((Transaction) any());
    }

    @Test
    @DisplayName("Test transferTest to Transaction service")
    public void transfer() {

        User    user    = new User();
        Account account = new Account();
        user.setId(1);
        user.setFirstName("Nicolas");
        user.setLastName("Biancucci");
        user.setEmail("nico@gmail.com");
        user.setPassword("azerty");
        user.setBalance(10000.0);
        user.setAccount(new Account());
        user.setRole("USER");
        user.setContactList(new ArrayList<Contact>());
        user.setEnabled(true);
        account.setId(1);
        account.setIban("FR4401234567890123456780000");
        account.setBalance(10.0);
        account.setUser(user);
        User    user1    = new User();
        Account account1 = new Account();
        user1.setId(2);
        user1.setFirstName("James");
        user1.setLastName("Bond");
        user1.setEmail("james@007.com");
        user1.setPassword("spectre");
        user1.setBalance(10000.0);
        user1.setAccount(account1);
        user1.setRole("USER");
        user1.setContactList(new ArrayList<Contact>());
        user1.setEnabled(true);
        account1.setId(2);
        account1.setIban("FR4401234567890123456780002");
        account1.setBalance(10000.0);
        account1.setUser(user1);
        //WHEN
        when(userRepository.findByEmail(user.getEmail())).thenReturn(user);
        //THEN
        assertThrows(RuntimeException.class, () -> transactionService.transfer("nico@gmail.com", "james@007.com", LocalDate.now(), 10.0, "AstonMartin"));
        verify(userRepository, times(2)).findByEmail(anyString());
    }




}
