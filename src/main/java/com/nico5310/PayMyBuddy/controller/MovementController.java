package com.nico5310.PayMyBuddy.controller;

import com.nico5310.PayMyBuddy.model.Movement;
import com.nico5310.PayMyBuddy.service.MovementService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Log4j2
@RestController
public class MovementController {

    @Autowired
    private MovementService movementService;

    @GetMapping(value = "/movement")
    public List<Movement> findAllMovements() {

        log.info("Get all movements");
        return  movementService.findAllMovements();

    }

    @GetMapping(value = "/movement/{idMovement}")
    public Movement findMovementById(@PathVariable (value = "idMovement") Integer idMovement) {

        log.info("Get movement by id");
        return  movementService.findMovementById(idMovement);
    }

    @PostMapping(value = "/movement/transferToAccountBank")
    public Movement transferToAccountBank(@Valid @RequestBody Movement movement) {

        log.info("Create a transfer money from account bank to application");
        movementService.transferAccount(movement.getUser(), movement.getAmountMovement(), movement);
        return movementService.saveMovement(movement);
    }

    @PostMapping(value = "/movement/transferToApplication")
    public Movement transferToApplication(@Valid @RequestBody Movement movement) {

        log.info("Create a transfer money from account bank to application");
        movementService.transferApplication(movement.getUser(), movement.getAmountMovement(), movement);
        return movementService.saveMovement(movement);
    }



}
