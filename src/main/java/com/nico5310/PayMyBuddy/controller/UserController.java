package com.nico5310.PayMyBuddy.controller;

import com.nico5310.PayMyBuddy.model.Contact;
import com.nico5310.PayMyBuddy.model.User;
import com.nico5310.PayMyBuddy.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    // REQUEST USER
    @GetMapping(value = "/user")
    public List<User> findAllUsers() {
        log.info("Get all users");
        return userService.findAllUsers();
    }

    @GetMapping(value = "/user/{id}")
    public Optional<User> findById(@PathVariable(value = "id") Integer id) {
        log.info("Get user by id" + id);
        return userService.findById(id);
    }

    @GetMapping(value = "/user/{email}")
    public User findByEmail(@PathVariable(value = "email") String email) {
        log.info("Get user by email" + email);
        return userService.findByEmail(email);
    }

    @PostMapping(value = "/user")
    public User saveUser(@Valid @RequestBody User user) {
        log.info("Create a new user" + user);
        return userService.saveUser(user);
    }

    @PutMapping(value = "/user/{id}")
    public void updateUser(@PathVariable(value = "id") Integer id, @Valid @RequestBody User user) {
        log.info("Update a user by id" + id);
        userService.updateUser(id, user);
    }

    @DeleteMapping(value = "/user/{id}")
    public void deleteById(@PathVariable(value = "id") Integer id) {
        log.info("Delete user by id" + id);
        userService.deleteById(id);
    }

    @DeleteMapping(value = "/user/{email}")
    public void deleteUserByEmail(@PathVariable(value = "email") String email) {
        log.info("Delete user by email" + email);
        userService.deleteUserByEmail(email);
    }

    //REQUEST CONTACT
    @GetMapping(value = "/user/contacts")
    public List<Contact> findAllContacts() {
        log.info("Get all userContacts");
        return userService.findAllContacts();
    }

    @GetMapping(value = "/user/contacts/{email}")
    public List<Contact> findContactByUserEmail(@PathVariable(value = "email") String email) {
        log.info("Get userContact by email" + email);
        return userService.findContactByUserEmail(email);
    }

    @PostMapping(value = "/user/contacts/{idUser}/{idUserContact}")
    public void saveContact(@PathVariable(value = "idUser") Integer idUser, @PathVariable(value = "idUserContact") User idUserContact) {
        log.info("Create a new userContact" + idUser);
        userService.saveContact(idUser, idUserContact);
    }

    @DeleteMapping(value = "/user/contacts/{idUser}/{idUserContact}")
    public void deleteContact(@PathVariable(value = "idUser") Integer idUser, @PathVariable(value = "idUserContact") Integer idUserContact) {
        log.info("Delete userContact by idUser" + idUser);
        userService.deleteContact(idUser, idUserContact);
    }


}
