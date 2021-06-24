package com.nico5310.PayMyBuddy.service;

import com.nico5310.PayMyBuddy.exception.NoFoundException;
import com.nico5310.PayMyBuddy.model.Account;
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
    public void transfertToApplication(String emailUser, Double amountMovement)  {
        log.info("Transfer to application");
        User user = userRepository.findUsersByEmail(emailUser);
        user.setBalance(user.getBalance() + amountMovement);
        userRepository.save(user);

        Account account = accountRepository.findAccountByUserEmail(emailUser);
        account.setBalance(account.getBalance() - amountMovement);
        accountRepository.save(account);

    }


    public void transferToAccountBank(String emailUser, Double amountMovement) {
        log.info("Transfer to Account bank");
        User user = userRepository.findUsersByEmail(emailUser);
        user.setBalance(user.getBalance() - amountMovement);
        userRepository.save(user);

        Account account = accountRepository.findAccountByUserEmail(emailUser);
        account.setBalance(account.getBalance() + amountMovement);
        accountRepository.save(account);
    }








}