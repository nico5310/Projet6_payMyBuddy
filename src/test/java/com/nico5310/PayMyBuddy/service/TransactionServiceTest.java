package com.nico5310.PayMyBuddy.service;

import com.nico5310.PayMyBuddy.model.Transaction;
import com.nico5310.PayMyBuddy.repository.AccountRepository;
import com.nico5310.PayMyBuddy.repository.TransactionRepository;
import com.nico5310.PayMyBuddy.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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

//    @Test
//    @DisplayName("Test FindAllTransactions to Transaction service")
//    public void findAllTransactionsTest() {
//        //GIVEN
//        ArrayList<Transaction> transactionList = new ArrayList<Transaction>();
//        //WHEN
//        when(transactionRepository.findAll()).thenReturn(transactionList);
//        List<Transaction> transactionList1 = transactionService.findAllTransactions();
//        //THEN
//        assertSame(transactionList, transactionList1);
//        assertTrue(transactionList1.isEmpty());
//        verify(transactionRepository).findAll();
//    }


}
