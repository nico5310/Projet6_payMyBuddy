package com.nico5310.PayMyBuddy.controller;

import com.nico5310.PayMyBuddy.model.Contact;
import com.nico5310.PayMyBuddy.model.Transaction;
import com.nico5310.PayMyBuddy.model.User;
import com.nico5310.PayMyBuddy.service.TransactionService;
import com.nico5310.PayMyBuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
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

    /**
     * Open homepage view
     *
     * @param user
     *         the user
     * @param model
     *         the model
     *
     * @return the homepage form view
     */
    @GetMapping(value = "/transfer")
    public String transferPage(@AuthenticationPrincipal User user, Model model) {
        List<Transaction> transactions = transactionService.findTransactionsOfUserPrincipal(user);
        List<Contact> contactList = userService.findContactByUserEmail(user.getEmail());
        model.addAttribute("user", user.getFirstName());
        model.addAttribute("balance", user.getBalance());
        model.addAttribute("userAddContact", userService.userAddContact(user.getEmail()));
        model.addAttribute("contacts", contactList);
        model.addAttribute("transactions", transactions);
        return "transfer";
    }

    @GetMapping(value = "/send")
    public String send(@AuthenticationPrincipal User user, @RequestParam(value = "emailContact", required = false) String emailContact,@RequestParam(value = "date", required = false) LocalDate date,@RequestParam(value = "amount", required = false) Double amountTransaction, @RequestParam(value = "description", required = false) String description, @RequestParam(value = "amount", required = false) Double amount) {

        transactionService.transfer(user.getEmail(), emailContact,date, amountTransaction, description);
        return "redirect:/homepage";
    }


    @PostMapping("/saveContact")
    public String saveContact(@AuthenticationPrincipal User user, @RequestParam(value="idContact", required = false) Integer idContact) {
        userService.saveContact(user.getId(), idContact);

        return "redirect:/transfer";
    }





}
