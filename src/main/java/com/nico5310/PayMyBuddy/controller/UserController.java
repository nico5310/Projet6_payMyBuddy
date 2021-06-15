package com.nico5310.PayMyBuddy.controller;

import com.nico5310.PayMyBuddy.model.Contact;
import com.nico5310.PayMyBuddy.model.User;
import com.nico5310.PayMyBuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    // REQUEST USER
    @GetMapping()
    public List<User> findAllUsers() {

        return userService.findAllUsers();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<User> findById(@PathVariable Integer id) {

        return userService.findById(id);
    }



    @PutMapping(value = "/{id}")
    public void updateUser(@PathVariable Integer id, @RequestBody User user) {

        userService.updateUser(id, user);
    }


    //REQUEST CONTACT
    @GetMapping(value = "/contacts", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Contact> findAllContacts() {

        return userService.findAllContacts();
    }

    @PostMapping("/contacts")
    public void saveContact(@RequestParam(name = "idUser") Integer idUser, @RequestParam(name = "idUserContact") Integer idUserContact) {

        userService.saveContact(idUser, idUserContact);

    }

    @DeleteMapping(value = "/contacts")
    public void deleteContact(@RequestParam(name = "idUser") Integer idUser, @RequestParam(name = "idUserContact") Integer idUserContact) {

        userService.deleteContact(idUser, idUserContact);
    }

    @GetMapping(value = "/contacts/{email}")
    public List<Contact> findContactByUserEmail(@PathVariable String email) {

        return userService.findContactByUserEmail(email);
    }



//    @DeleteMapping(value = "/user/{id}")
//    public void deleteById(@PathVariable Integer id) {
//
//        log.info("Delete user by id" + id);
//        userService.deleteById(id);
//    }
//
//    @DeleteMapping(value = "/{email}")
//    public void deleteByEmail(@PathVariable String email) {
//
//        log.info("Delete user by email" + email);
//        userService.deleteByEmail(email);
//    }


}
