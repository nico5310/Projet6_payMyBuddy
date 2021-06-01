package com.nico5310.PayMyBuddy.controller;

import com.nico5310.PayMyBuddy.model.Contact;
import com.nico5310.PayMyBuddy.model.User;
import com.nico5310.PayMyBuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserService userService;


    // REQUEST USER
    @GetMapping(value = "/user")
    public List<User> findAll() {

        return userService.findAllUsers();
    }

    @GetMapping(value = "/user/{id}")
    public Optional<User> findById(@RequestParam (name = "id") Integer id) {

        return userService.findById(id);
    }

    @GetMapping(value = "/user/{email}")
    public User findByEmail(@RequestParam (name = "email") String email) {

        return userService.findByEmail(email);
    }

    @PostMapping(value = "/user")
    public User saveUser (@RequestBody User user) {

        return userService.saveUser(user);
    }

    @PutMapping(value = "/user/{id}")
    public void updateUser (@RequestParam (name = "id") Integer id, @RequestBody User user) {

        userService.updateUser(id, user);
    }

    //REQUEST CONTACT
    @GetMapping(value = "/user/contacts")
    public List<Contact> findAllContacts() {

        return userService.findAllContacts();
    }

    @GetMapping(value = "/user/contacts/{email}")
    public List<Contact> findContactByUserEmail (@RequestParam (name = "email") String email) {

        return userService.findContactByUserEmail(email);
    }

    @PostMapping(value = "/user/contacts")
    public void saveContact (@RequestParam(name = "idUser") Integer idUser, @RequestParam (name = "idUserContact") Integer idUserContact) {

        userService.saveContact(idUser, idUserContact);
    }

    @DeleteMapping(value = "/user/contacts")
    public void deleteContact (@RequestParam (name = "idUser") Integer idUser, @RequestParam (name = "idUserContact") Integer idUserContact) {

        userService.deleteContact(idUser, idUserContact);
    }



}
