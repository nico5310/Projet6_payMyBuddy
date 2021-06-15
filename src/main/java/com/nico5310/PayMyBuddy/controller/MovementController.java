package com.nico5310.PayMyBuddy.controller;

import com.nico5310.PayMyBuddy.model.Movement;
import com.nico5310.PayMyBuddy.service.MovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * MovementController
 * @author Nicolas
 */
@RestController
@RequestMapping(value= "/movement")
public class MovementController {

    /**
     * @see MovementService
     */
    @Autowired
    private MovementService movementService;

    /**
     * Get List of Movements
     * @return list of movements
     */
    @GetMapping
    public List<Movement> findAll() {
            return  movementService.findAll();
    }

    /**
     * Get TransferController to account bank
     * @param email the email
     * @param amountMovement the amount of movement
     */
    @PostMapping(value = "/transferToAccountBank")
    public void transferToAccountBank(@RequestParam (name = "email") String email, @RequestParam(name= "amount") Double amountMovement ) {
            movementService.transferToAccountBank(email, amountMovement);
    }

    /**
     * Get TransferController to application
     * @param email the email
     * @param amountMovement the amount of movement
     */
    @PostMapping(value = "/transferToApplication")
    public void transferToApplication(@RequestParam (name = "email") String email, @RequestParam(name= "amount") Double amountMovement ) {
            movementService.transfertToApplication(email, amountMovement);
    }

}
