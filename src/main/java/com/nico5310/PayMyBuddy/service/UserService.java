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

@Log4j2
@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository    userRepository;
    @Autowired
    private ContactRepository contactRepository;

    // user
    public List<User> findAllUsers() {

        log.info("Get all users");
        return userRepository.findAll();
    }

    public User findById(Integer id) {

        log.info("Get user by id");
        return userRepository.findById(id).orElseThrow(() -> new NoFoundException("User doesn't exist"));
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

        User user = userRepository.findById(id).orElseThrow();
        user.setFirstName(userUpdated.getFirstName());
        user.setLastName(userUpdated.getLastName());
        user.setEmail(userUpdated.getEmail());
        user.setPassword(userUpdated.getPassword());
        userRepository.save(user);
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

    public void saveContact(Integer userId, Integer contactId) {
        log.info("Create new userContact");
        User user = userRepository.findById(userId).orElseThrow();
        User userContact = userRepository.findById(contactId)
                                         .orElseThrow(() -> new NoFoundException("User doesn't exist"));

        Contact contact = new Contact();
        contact.setUser(user);
        contact.setUserContact(userContact);
        contactRepository.save(contact);
    }

    public void deleteContact(Integer userId, Integer userContactId) {
        log.info("Delete userContact");
        contactRepository.deleteContactByUserIdAndUserContactId(userId, userContactId);
    }


}
