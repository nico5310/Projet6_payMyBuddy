package com.nico5310.PayMyBuddy.service;

import com.nico5310.PayMyBuddy.exception.InsufficientFundsException;
import com.nico5310.PayMyBuddy.exception.NoFoundException;
import com.nico5310.PayMyBuddy.model.Movement;
import com.nico5310.PayMyBuddy.model.User;
import com.nico5310.PayMyBuddy.repository.AccountRepository;
import com.nico5310.PayMyBuddy.repository.MovementRepository;
import com.nico5310.PayMyBuddy.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Log4j2
@Service
@Transactional
public class MovementService {

    @Autowired
    MovementRepository movementRepository;
    @Autowired
    UserRepository     userRepository;
    @Autowired
    AccountRepository  accountRepository;
    @Autowired
    MovementService movementService;

    public List<Movement> findAll() {

        log.info("Get all movement");
        return movementRepository.findAll();
    }

    public Movement findMovementById(Integer idMovement) {

        log.info("Get movement by id");
        return movementRepository.findById(idMovement)
                                 .orElseThrow(() -> new NoFoundException("Movement doesn't exist"));
    }

    // METHOD MOVEMENT
    @Transactional
    public void transferToAccountBank(String email, Double amountMovement) {

        log.info("Transfer an amount of : " + amountMovement + "from application to account bank" + email);
        User user = userRepository.findByEmail(email);
        if (user.getBalance() < amountMovement) {
            throw new RuntimeException("Insufficient balance");
        } else {
            user.setBalance(user.getBalance() - amountMovement);
            userRepository.save(user);
        }
    }

    @Transactional
    public void transfertToApplication(User user, Movement movement) {

        log.info("Transfer an amount of : " + movement.getAmountMovement() + "from account bank to Application");
        user.setBalance(user.getBalance() + movement.getAmountMovement());
        movement.setUser(user);
        userRepository.save(user);
        movementService.saveMovement(movement);
    }

    @Transactional
    public void saveMovement(Movement movement) {
        movement.setDate(LocalDate.now());
        movementRepository.save(movement);
    }

    @Transactional(rollbackOn = InsufficientFundsException.class)
    public void subtractToBalance(User user, Movement movement) throws InsufficientFundsException {
        if (user.getBalance()-movement.getAmountMovement() <0) {
            throw new InsufficientFundsException("Insufficient funds to Balance votre Movement to account");
        }
        user.setBalance(user.getBalance()- movement.getAmountMovement());
        movement.setAmountMovement(movement.getAmountMovement() *-1);
        movement.setUser(user);
        userRepository.save(user);
        movementService.saveMovement(movement);

    }




}