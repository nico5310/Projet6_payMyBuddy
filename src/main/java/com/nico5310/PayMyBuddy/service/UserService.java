package com.nico5310.PayMyBuddy.service;


import com.nico5310.PayMyBuddy.model.Contact;
import com.nico5310.PayMyBuddy.model.User;
import com.nico5310.PayMyBuddy.repository.ContactRepository;
import com.nico5310.PayMyBuddy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContactRepository contactRepository;

    // user

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void updateUser( Integer id, User userUpdated) {
        Optional<User> user = userRepository.findById(id);
        user.get().setFirstName(userUpdated.getFirstName());
        user.get().setLastName(userUpdated.getLastName());
        user.get().setEmail(userUpdated.getEmail());
        user.get().setPassword(userUpdated.getPassword());

        userRepository.save(user.get());
    }

    public void deleteUserByEmail (String email) {
        userRepository.deleteUserByEmail(email);
    }

    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }


    // contact

    public List<Contact> findAllContacts() {
        return contactRepository.findAll();
    }

    public List<Contact> findContactByUserEmail(String email) {

        return contactRepository.findContactByUserEmail(email);
    }

    public void saveContact(Integer userId, Integer contactId) {

        Optional<User> user = userRepository.findById(userId);
        Optional<User> userContact = userRepository.findById(contactId);

        Contact contact = new Contact();
        contact.setUser(user.get());
        contact.setUserContact(userContact.get());
        contactRepository.save(contact);
    }

    public void deleteContact(Integer userId, Integer userContactId) {

        contactRepository.deleteContactByUserIdAndUserContactId(userId, userContactId);
    }


}
