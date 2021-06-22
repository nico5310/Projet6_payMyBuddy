package com.nico5310.PayMyBuddy.controller;

import com.nico5310.PayMyBuddy.model.Movement;
import com.nico5310.PayMyBuddy.model.User;
import com.nico5310.PayMyBuddy.service.MovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping(value= "/movement")
public class MovementController {

    @Autowired
    private MovementService movementService;


    @GetMapping
    public List<Movement> findAll() {

        return  movementService.findAll();
    }

    @GetMapping(value = "/transferToApplication")
    public String transferToApplication(@AuthenticationPrincipal User user,  @RequestParam(value= "amount") Double amountMovement) {
        movementService.transfertToApplication(user.getEmail(), amountMovement);
        return "redirect:/profile";
    }



    @GetMapping(value = "/transferToAccountBank")
    public String transferToAccountBank(@AuthenticationPrincipal User user,  @RequestParam(value= "amount") Double amountMovement) {
          movementService.transferToAccountBank(user.getEmail(), amountMovement);
          return "redirect:/profile";
    }

}
