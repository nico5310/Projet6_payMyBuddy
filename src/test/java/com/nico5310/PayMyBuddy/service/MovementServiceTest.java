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

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
    public void testTransferToAccountBank() {

        User user = new User();
        user.setLastName("Doe");
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setBalance(10.0);
        user.setAccount(new Account());
        user.setRole("Role");
        user.setId(1);
        user.setContactList(new ArrayList<Contact>());
        user.setEnabled(true);
        user.setFirstName("Jane");

        Account account = new Account();
        account.setBalance(10.0);
        account.setUser(user);
        account.setId(1);
        account.setIban("Iban");

        User user1 = new User();
        user1.setLastName("Doe");
        user1.setEmail("jane.doe@example.org");
        user1.setPassword("iloveyou");
        user1.setBalance(10.0);
        user1.setAccount(account);
        user1.setRole("Role");
        user1.setId(1);
        user1.setContactList(new ArrayList<Contact>());
        user1.setEnabled(true);
        user1.setFirstName("Jane");

        Account account1 = new Account();
        account1.setBalance(10.0);
        account1.setUser(user1);
        account1.setId(1);
        account1.setIban("Iban");

        User user2 = new User();
        user2.setLastName("Doe");
        user2.setEmail("jane.doe@example.org");
        user2.setPassword("iloveyou");
        user2.setBalance(10.0);
        user2.setAccount(account1);
        user2.setRole("Role");
        user2.setId(1);
        user2.setContactList(new ArrayList<Contact>());
        user2.setEnabled(true);
        user2.setFirstName("Jane");
        when(this.userRepository.save((User) any())).thenThrow(new NoFoundException("An error occurred"));
        when(this.userRepository.findUsersByEmail(anyString())).thenReturn(user2);
        assertThrows(NoFoundException.class, () -> this.movementService.transferToAccountBank("jane.doe@example.org", 10.0));
        verify(this.userRepository).findUsersByEmail(anyString());
        verify(this.userRepository).save((User) any());
    }

    @Test
    public void testTransferToAccountBank2() {

        User user = new User();
        user.setLastName("Doe");
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setBalance(10.0);
        user.setAccount(new Account());
        user.setRole("Role");
        user.setId(1);
        user.setContactList(new ArrayList<Contact>());
        user.setEnabled(true);
        user.setFirstName("Jane");

        Account account = new Account();
        account.setBalance(10.0);
        account.setUser(user);
        account.setId(1);
        account.setIban("Iban");

        User user1 = new User();
        user1.setLastName("Doe");
        user1.setEmail("jane.doe@example.org");
        user1.setPassword("iloveyou");
        user1.setBalance(10.0);
        user1.setAccount(account);
        user1.setRole("Role");
        user1.setId(1);
        user1.setContactList(new ArrayList<Contact>());
        user1.setEnabled(true);
        user1.setFirstName("Jane");

        Account account1 = new Account();
        account1.setBalance(10.0);
        account1.setUser(user1);
        account1.setId(1);
        account1.setIban("Iban");

        User user2 = new User();
        user2.setLastName("Doe");
        user2.setEmail("jane.doe@example.org");
        user2.setPassword("iloveyou");
        user2.setBalance(10.0);
        user2.setAccount(account1);
        user2.setRole("Role");
        user2.setId(1);
        user2.setContactList(new ArrayList<Contact>());
        user2.setEnabled(true);
        user2.setFirstName("Jane");
        when(this.userRepository.save((User) any())).thenThrow(new NoFoundException("An error occurred"));
        when(this.userRepository.findUsersByEmail(anyString())).thenReturn(user2);
        assertThrows(NoFoundException.class, () -> this.movementService.transferToAccountBank("jane.doe@example.org", 10.0));
        verify(this.userRepository).findUsersByEmail(anyString());
        verify(this.userRepository).save((User) any());
    }

}
