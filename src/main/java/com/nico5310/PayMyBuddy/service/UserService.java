package com.nico5310.PayMyBuddy.service;

import com.nico5310.PayMyBuddy.exception.NoFoundException;
import com.nico5310.PayMyBuddy.model.Contact;
import com.nico5310.PayMyBuddy.model.User;
import com.nico5310.PayMyBuddy.repository.ContactRepository;
import com.nico5310.PayMyBuddy.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository    userRepository;
    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    public UserService(UserRepository userRepository, ContactRepository contactRepository) {

        this.userRepository    = userRepository;
        this.contactRepository = contactRepository;

    }

    // user
    public List<User> findAllUsers() {

        log.info("Get all users");
        return userRepository.findAll();
    }

    public Optional<User> findById(Integer id) {

        log.info("Get user by id");
        return userRepository.findById(id);
    }

    public User findByEmail(String email) {

        log.info("Get user by email");
        return userRepository.findByEmail(email);
    }

    public User saveUser(User user) {

        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new NoFoundException("User does exist !");
        } else {
            log.info("Create user");
            return userRepository.save(user);
        }
    }

    public void updateUser(Integer id, User userUpdated) {

        Optional<User> user = userRepository.findById(id);
        user.get().setFirstName(userUpdated.getFirstName());
        user.get().setLastName(userUpdated.getLastName());
        user.get().setEmail(userUpdated.getEmail());
        user.get().setPassword(userUpdated.getPassword());
        userRepository.save(user.get());
    }

    public void deleteUserByEmail(String email) {

        log.info("Delete user by email");
        userRepository.deleteUserByEmail(email);
    }

    public void deleteById(Integer id) {

        log.info("Delete user by id");
        userRepository.deleteById(id);
    }

    // contact
    public List<Contact> findAllContacts() {

        log.info("Get all userContacts");
        return contactRepository.findAll();
    }

    public List<Contact> findContactByUserEmail(String email) {

        log.info("Get userContact by user email");
        return contactRepository.findContactByUserEmail(email);
    }

    public void saveContact(Integer userId, User contact) {

        User user = userRepository.findById(userId).orElseThrow(() -> new NoFoundException("User don't exist"));
        if (user.getId().equals(contact.getId())) {
            throw new NoFoundException("UserContact does exist !");
        } else {
        log.info("Create new userContact"); }
        userRepository.findAll().add(contact);

    }

    public void deleteContact(Integer userId, Integer userContactId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new NoFoundException("User don't exist"));
        User contact = userRepository.findById(userContactId).orElseThrow(()-> new NoFoundException("Contact don't exist"));
        log.info("Delete userContact");
        if (user.getContactList().contains(contact))
            user.getContactList().remove(contact);
        else throw new NoFoundException("Contact don't exist");

    }


}
