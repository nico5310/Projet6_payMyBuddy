package com.nico5310.PayMyBuddy.service;


import com.nico5310.PayMyBuddy.exception.NoCreateUserPossibleException;
import com.nico5310.PayMyBuddy.exception.NoFoundException;
import com.nico5310.PayMyBuddy.model.Contact;
import com.nico5310.PayMyBuddy.model.User;
import com.nico5310.PayMyBuddy.repository.ContactRepository;
import com.nico5310.PayMyBuddy.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@Transactional
public class UserService implements UserDetailsService {

    @Autowired
    private  UserRepository    userRepository;
    @Autowired
    private  ContactRepository contactRepository;


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

    public void saveUser(User user) throws NoCreateUserPossibleException {

        if(user.getFirstName() != "" && user.getLastName() != "" && user.getEmail() != "" && user.getPassword()  != ""){
            List<Contact> contactList = new ArrayList<>();
            log.info("Create user valid");
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            user.setBalance(1000.0);
            user.setAccount(null);
            user.setContactList(contactList);
            user.setEnabled(true);
            userRepository.save(user);
        }
        else{
            throw new NoCreateUserPossibleException("Parameters to save user are invalid");
        }
    }


    public void updateUser(Integer id, User userUpdated) {
        log.info("Update user valid");
        Optional<User> user = userRepository.findById(id);
        user.get().setFirstName(userUpdated.getFirstName());
        user.get().setLastName(userUpdated.getLastName());
        user.get().setEmail(userUpdated.getEmail());
        user.get().setPassword(userUpdated.getPassword());
        userRepository.save(user.get());
    }



    public void deleteByEmail(String email) {

        log.info("Delete user by email");
        userRepository.deleteByEmail(email);
    }

    public List<Contact> findContactByUserEmail(String email) {

        log.info("Get userContact by user email");
        return contactRepository.findContactByUserEmail(email);
    }

    // contact
//    public List<Contact> findAllContacts() {
//
//        log.info("Get all userContacts");
//        return contactRepository.findAll();
//    }



    public List<Contact> findContactByUserId(Integer id) {

        log.info("Get userContact by user email");
        return contactRepository.findContactByUserId(id);
    }

    public List<User> usersExceptFriends(String email) {
        log.info("Find all users except User principal");
        List<User> userList = userRepository.findAll();
        User       user     = userRepository.findUsersByEmail(email);
        userList.remove(userRepository.findByEmail(email));
        for (Contact contact : user.getContactList()) {
            userList.remove(contact.getUserContact());
        }
        return userList;
    }

    public void saveContact(Integer userId, Integer idContact) {

        User user = userRepository.findById(userId).orElseThrow(() -> new NoFoundException("User don't exist"));
        User contact = userRepository.findById(idContact).orElseThrow(() -> new NoFoundException("User don't exist"));
        if (user.getId().equals(contact.getId())) {
            throw new NoFoundException("UserContact does exist!");
        } else {
        log.info("Create new userContact"); }
        Contact contact1 = new Contact();
        contact1.setUser(user);
        contact1.setUserContact(contact);
        contactRepository.save(contact1);
    }

    public void deleteContactByID(Integer contactId) {
        log.info("Delete contact By Id");
        contactRepository.deleteContactById(contactId);

    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findByEmail(username);
    }
}
