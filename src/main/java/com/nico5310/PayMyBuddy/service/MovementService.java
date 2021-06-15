package com.nico5310.PayMyBuddy.service;

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

    public void transfertToApplication(String email, Double amountMovement ) {

        log.info("Transfer an amount of : " + amountMovement + "from account bank to Application" + email);
        User user = userRepository.findByEmail(email);
        user.setBalance(user.getBalance() + amountMovement);
        userRepository.save(user);
    }




}