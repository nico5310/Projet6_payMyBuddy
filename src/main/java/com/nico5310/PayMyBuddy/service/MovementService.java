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

    public List<Movement> findAllMovements() {

        log.info("Get all movement");
        return movementRepository.findAll();
    }

    public Movement findMovementById(Integer idMovement) {

        log.info("Get movement by id");
        return movementRepository.findById(idMovement)
                                 .orElseThrow(() -> new NoFoundException("Movement doesn't exist"));
    }

    public Movement saveMovement(Movement movement) {

        log.info("Create au movement to Account");
        return movementRepository.save(movement);
    }

    // METHOD MOVEMENT
    public void transferToAccountBank(Integer id, Double amountMovement) {

        log.info("Transfer an amount of : " + amountMovement + "from application to account bank" + id);
        User user = userRepository.findById(id).orElseThrow(() -> new NoFoundException("Movement doesn't exist"));
        if (user.getBalance() < amountMovement) {
            throw new RuntimeException("Insufficient balance");
        } else {
            user.setBalance(user.getBalance() - amountMovement);
            userRepository.save(user);
        }
    }

    public void transfertToApplication(Integer id, Double amountMovement) {

        log.info("Transfer an amount of : " + amountMovement + "from account bank to Application" + id);
        User user = userRepository.findById(id).orElseThrow(() -> new NoFoundException("Movement doesn't exist"));
        user.setBalance(user.getBalance() + amountMovement);
        userRepository.save(user);
    }

    public void transferAccount(Integer id, Double amountMovement, Movement movement) {
        transferToAccountBank(id, amountMovement);
    }

    public  void transferApplication(Integer id, Double amountMovement, Movement movement) {
        transfertToApplication(id, amountMovement);
    }


}