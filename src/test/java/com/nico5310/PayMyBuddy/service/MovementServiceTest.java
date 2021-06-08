package com.nico5310.PayMyBuddy.service;

import com.nico5310.PayMyBuddy.exception.NoFoundException;
import com.nico5310.PayMyBuddy.model.Account;
import com.nico5310.PayMyBuddy.model.Contact;
import com.nico5310.PayMyBuddy.model.Movement;
import com.nico5310.PayMyBuddy.model.Transaction;
import com.nico5310.PayMyBuddy.model.User;
import com.nico5310.PayMyBuddy.repository.AccountRepository;
import com.nico5310.PayMyBuddy.repository.MovementRepository;
import com.nico5310.PayMyBuddy.repository.TransactionRepository;
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
    @DisplayName("Test findAllMovements to Transaction service")
    public void findAllMovementsTest() {
        //GIVEN
        List<Movement> movementList = new ArrayList<>();
        //WHEN
        when(movementRepository.findAll()).thenReturn(movementList);
        List<Movement> movementList1 = movementService.findAllMovements();
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
        assertTrue(movementService.findAllMovements().isEmpty());
    }

}
