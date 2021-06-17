package com.nico5310.PayMyBuddy.controller;

import com.nico5310.PayMyBuddy.model.User;
import com.nico5310.PayMyBuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.validation.Valid;

/**
 * Registration view controller
 * @author Nicolas
 */
@Controller
public class Registration {

    /**
     * @see UserService
     */
    @Autowired
    private UserService userService;

    /**
     * Get sign up view
     * @param model the model
     * @return the registration view
     */
    @PreAuthorize("isAnonymous()")
    @GetMapping(value = "/registration")
    public String addUser(Model model) {

        User user = new User();
        model.addAttribute(user);
        return "registration";

    }

    /**
     * Get add user view
     *
     * @return the add user from view
     */
    @PreAuthorize("isAnonymous()")
    @GetMapping(value = "/save")
    public String addUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {

        userService.saveUser(user);
        return "redirect:/login";
    }

    @GetMapping(value = "/login")
    public String login(Model model) {
        return "login";
    }



}
