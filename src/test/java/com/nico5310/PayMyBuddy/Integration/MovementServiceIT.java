package com.nico5310.PayMyBuddy.Integration;

import com.nico5310.PayMyBuddy.model.Account;
import com.nico5310.PayMyBuddy.model.Movement;
import com.nico5310.PayMyBuddy.model.User;
import com.nico5310.PayMyBuddy.repository.AccountRepository;
import com.nico5310.PayMyBuddy.repository.MovementRepository;
import com.nico5310.PayMyBuddy.repository.UserRepository;
import com.nico5310.PayMyBuddy.service.MovementService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;


@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class MovementServiceIT {

    @Autowired
    private MovementRepository movementRepository;


    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovementService movementService;


    @Test
    @DisplayName("Test findAll to MovementServiceIT ")
    public void findAllMovementsTest() {
        //GIVEN
        Movement movement = new Movement();
        movement.setId(1);
        movement.setAmountMovement(500.0);
        movementRepository.save(movement);
        //WHEN

        //THEN
        Assertions.assertEquals(500.0, movementService.findAll().get(0).getAmountMovement());

    }

    @Test
    @DisplayName("Test findMovementById to MovementServiceIT ")
    public void findMovementByIdTest() {
        //GIVEN
        Movement movement = new Movement();
        movement.setId(1);
        movement.setAmountMovement(500.0);
        movementRepository.save(movement);
        //WHEN

        //THEN
        Assertions.assertEquals(500.0, movementService.findMovementById(1).getAmountMovement());

    }

    @Test
    @DisplayName("Test transferToAccountBank to TransactionServiceIT ")
    public void transferToAccountBankTest() {
        //GIVEN
        User user1 = new User();
        user1.setId(1);
        user1.setFirstName("Nicolas");
        user1.setLastName("Biancucci");
        user1.setEmail("nico@gmail.com");
        user1.setPassword("azerty");
        user1.setBalance(10000.0);
        userRepository.save(user1);
        //WHEN
        movementService.transferToAccountBank(user1.getEmail(), 2000.0);
        //THEN
        Assertions.assertEquals(userRepository.findById(user1.getId()).get().getBalance(), 8000.0);
    }

//    @Test
//    @DisplayName("Test transfertToApplication to TransactionServiceIT ")
//    public void transfertToApplicationTest() {
//        //GIVEN
//        User user1 = new User();
//        Account account1 = new Account();
//        user1.setId(1);
//        user1.setFirstName("Nicolas");
//        user1.setLastName("Biancucci");
//        user1.setEmail("nico@gmail.com");
//        user1.setPassword("azerty");
//        user1.setBalance(1000.0);
//        user1.setAccount(account1);
//        user1.setContactList(null);
//
//        account1.setId(1);
//        account1.setIban("FR4401234567890123456780000");
//        account1.setUser(user1);
//
//        user1.setAccount(account1);
//        account1.setUser(user1);
//
//        userRepository.save(user1);
//        accountRepository.save(account1);
//        //WHEN
//        movementService.transfertToApplication(user1.getEmail(), 9000.0);
//        //THEN
//        Assertions.assertEquals(userRepository.findById(user1.getId()).get().getBalance(), 10000.0);
//    }
}
