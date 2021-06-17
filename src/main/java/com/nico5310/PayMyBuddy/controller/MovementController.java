package com.nico5310.PayMyBuddy.controller;

import com.nico5310.PayMyBuddy.exception.InsufficientFundsException;
import com.nico5310.PayMyBuddy.model.Movement;
import com.nico5310.PayMyBuddy.model.User;
import com.nico5310.PayMyBuddy.service.MovementService;
import com.nico5310.PayMyBuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * MovementController
 * @author Nicolas
 */
@Controller
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

    @GetMapping(value = "/transferToApplication")
    public String transferToApplication(@AuthenticationPrincipal User user,  @RequestParam(value= "movement") Movement movement) {
        movementService.transfertToApplication(user, movement);
        return "profile";
    }

    @GetMapping(value = "/transferToAccountBank")
    public String transferToAccountBank(@AuthenticationPrincipal User user,  @ModelAttribute("movement") Movement movement, Model model) {
            try {
                movementService.subtractToBalance(user, movement);
            }catch (InsufficientFundsException e) {
                model.addAttribute("error", e.getMessage());
                return "profile";
            }
            return "profile";
    }

}
