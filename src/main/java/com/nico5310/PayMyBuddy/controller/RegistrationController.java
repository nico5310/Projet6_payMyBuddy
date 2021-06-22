package com.nico5310.PayMyBuddy.controller;

import com.nico5310.PayMyBuddy.model.User;
import com.nico5310.PayMyBuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;


@Controller
public class Registration {

    @Autowired
    private UserService userService;


    @GetMapping(value = "/registration")
    public String addUser(Model model) {
        User user = new User();
        model.addAttribute(user);
        return "registration";
    }

    @PostMapping(value = "/save")
    public String addUser(@Valid @ModelAttribute("user") User user, Model model) {

        userService.saveUser(user);
        return "redirect:/login";
    }

    @GetMapping(value = "/login")
    public String login(Model model) {
        return "login";
    }



}
