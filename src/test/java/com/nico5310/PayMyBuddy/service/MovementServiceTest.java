package com.nico5310.PayMyBuddy.service;

import com.nico5310.PayMyBuddy.exception.NoFoundException;
import com.nico5310.PayMyBuddy.model.Account;
import com.nico5310.PayMyBuddy.model.Contact;
import com.nico5310.PayMyBuddy.model.Movement;
import com.nico5310.PayMyBuddy.model.User;
import com.nico5310.PayMyBuddy.repository.AccountRepository;
import com.nico5310.PayMyBuddy.repository.MovementRepository;
import com.nico5310.PayMyBuddy.repository.UserRepository;

import java.time.LocalDate;
import java.util.Optional;

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

import java.util.ArrayList;
import java.util.List;

import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {TransactionService.class, MovementService.class})
@ExtendWith(MockitoExtension.class)
public class MovementServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private MovementRepository movementRepository;

    @InjectMocks
    private AccountService accountService;

    @InjectMocks
    private MovementService movementService;

    @BeforeEach
    void setup() {

        accountService = new AccountService(accountRepository, userRepository);

    }

    @Test
    @DisplayName("Test findAll to Transaction service")
    public void findAllMovementsTest() {
        //GIVEN
        List<Movement> movementList = new ArrayList<>();
        //WHEN
        when(movementRepository.findAll()).thenReturn(movementList);
        List<Movement> movementList1 = movementService.findAll();
        //THEN
        assertSame(movementList, movementList1);
        assertTrue(movementList1.isEmpty());
        verify(movementRepository).findAll();
    }

    @Test
    @DisplayName("Test testFindMovementById to Transaction service")
    public void testFindMovementByIdTest() {
        //GIVEN
        User user = new User();
        user.setId(1);
        user.setFirstName("Nicolas");
        user.setLastName("Biancucci");
        user.setEmail("nico@gmail.com");
        user.setPassword("azerty");
        user.setBalance(10000.0);
        Movement movement = new Movement();
        movement.setId(1);
        movement.setDate(LocalDate.now());
        movement.setAmountMovement(1000.0);
        movement.setUser(user);
        //WHEN
        when(movementRepository.findById(1)).thenReturn(Optional.of(movement));
        //THEN
        assertSame(movement, this.movementService.findMovementById(1));
        verify(movementRepository).findById(any(Integer.class));
        assertTrue(movementService.findAll().isEmpty());
    }

    @Test
    @DisplayName("Test transfertToApplication to Transaction service")
    public void transfertToApplicationTest() {
        //GIVEN
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
        account.setBalance(10000.0);
        account.setUser(user);
        //WHEN
        when(userRepository.findUsersByEmail(user.getEmail())).thenReturn(user);
        when(accountRepository.findAccountByUserEmail(user.getEmail())).thenReturn(account);
        movementService.transfertToApplication(user.getEmail(), 2000.0);
        //THEN
        assertEquals(user.getBalance(), 12000.0);
        assertEquals(account.getBalance(), 8000.0);
    }

    @Test
    @DisplayName("Test transferToAccountBank to Transaction service")
    public void transferToAccountBankTest() {
        //GIVEN
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
        account.setBalance(10000.0);
        account.setUser(user);
        //WHEN
        when(userRepository.findUsersByEmail(user.getEmail())).thenReturn(user);
        when(accountRepository.findAccountByUserEmail(user.getEmail())).thenReturn(account);
        movementService.transferToAccountBank(user.getEmail(), 2000.0);
        //THEN
        assertEquals(user.getBalance(), 8000.0);
        assertEquals(account.getBalance(), 12000.0);
    }

}
