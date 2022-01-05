package com.nico5310.PayMyBuddy.controller;

import com.nico5310.PayMyBuddy.exception.NoFoundException;
import com.nico5310.PayMyBuddy.model.User;
import com.nico5310.PayMyBuddy.service.TransactionService;
import com.nico5310.PayMyBuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@Controller
@RequestMapping("/transfer")
public class TransferController {

    /**
     * @see UserService
     */
    @Autowired
    UserService userService;

    /**
     * @see TransactionService
     */
    @Autowired
    TransactionService transactionService;


    @GetMapping(value = "/send")
    public String send(@AuthenticationPrincipal User user, @RequestParam(value = "emailContact", required = false) String emailContact, @RequestParam(value = "date", required = false) LocalDate date, @RequestParam(value = "amount", required = false) Double amountTransaction, @RequestParam(value = "description", required = false) String description) throws NoFoundException {

        transactionService.transfer(user.getEmail(), emailContact,date, amountTransaction, description);
        return "redirect:/homepage";
    }


    @PostMapping("/saveContact")
    public String saveContact(@AuthenticationPrincipal User user, @RequestParam(value="idContact", required = false) Integer idContact) {
        userService.saveContact(user.getId(), idContact);

        return "redirect:/transfer";
    }

}
