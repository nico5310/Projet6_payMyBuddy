package com.nico5310.PayMyBuddy.service;

import com.nico5310.PayMyBuddy.model.Account;
import com.nico5310.PayMyBuddy.model.Contact;
import com.nico5310.PayMyBuddy.model.User;
import com.nico5310.PayMyBuddy.repository.ContactRepository;
import com.nico5310.PayMyBuddy.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ContactRepository contactRepository;

    @InjectMocks
    private UserService userService;


    @BeforeEach
    void setup() {

        userService = new UserService(userRepository, contactRepository);

    }


    @Test
    @DisplayName("Test findAllUsers to UserService")
    public void findAllUsersTest() {
        //GIVEN
        User          user1        = new User();
        List<Contact> contactList1 = new ArrayList<>();
        Account       account1     = new Account(1, "FR4401234567890123456780000", user1);
        user1.setId(1);
        user1.setFirstName("Nicolas");
        user1.setLastName("Biancucci");
        user1.setEmail("nico@gmail.com");
        user1.setPassword("azerty");
        user1.setBalance(10000.0);
        user1.setAccount(account1);
        user1.setContactList(contactList1);
        User          user2        = new User();
        List<Contact> contactList2 = new ArrayList<>();
        Account       account2     = new Account(2, "FR4401234567890123456780002", user2);
        user2.setId(1);
        user2.setFirstName("James");
        user2.setLastName("Bond");
        user2.setEmail("james@007.com");
        user2.setPassword("spectre");
        user2.setBalance(10000.0);
        user2.setAccount(account2);
        user2.setContactList(contactList2);
        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);

        //WHEN
        when(userRepository.findAll()).thenReturn(userList);
        //THEN
        assertThat(userService.findAllUsers().toString(), containsString("Nicolas"));
    }

    @Test
    @DisplayName("Test findById to UserService")
    public void findByIdTest() {
        //GIVEN
        User          user1        = new User();
        List<Contact> contactList1 = new ArrayList<>();
        Account       account1     = new Account(1, "FR4401234567890123456780000", user1);
        user1.setId(1);
        user1.setFirstName("Nicolas");
        user1.setLastName("Biancucci");
        user1.setEmail("nico@gmail.com");
        user1.setPassword("azerty");
        user1.setBalance(10000.0);
        user1.setAccount(account1);
        user1.setContactList(contactList1);

        //WHEN
        when(userRepository.findById(any(Integer.class))).thenReturn(java.util.Optional.of(user1));
        //THEN
        assertThat(userService.findById(any(Integer.class)).toString(), containsString("Nicolas"));
    }

    @Test
    @DisplayName("Test findByEmail to UserService")
    public void findByEmailTest() {
        //GIVEN
        User          user1        = new User();
        List<Contact> contactList1 = new ArrayList<>();
        Account       account1     = new Account(1, "FR4401234567890123456780000", user1);
        user1.setId(1);
        user1.setFirstName("Nicolas");
        user1.setLastName("Biancucci");
        user1.setEmail("nico@gmail.com");
        user1.setPassword("azerty");
        user1.setBalance(10000.0);
        user1.setAccount(account1);
        user1.setContactList(contactList1);

        //WHEN
        when(userRepository.findByEmail(any(String.class))).thenReturn(user1);
        //THEN
        assertEquals(userService.findByEmail("").getEmail(), "nico@gmail.com");
    }


    @Test
    @DisplayName("Test saveUser to UserService")
    public void saveUserTest() {
        //GIVEN
        User          user1        = new User();
        List<Contact> contactList1 = new ArrayList<>();
        Account       account1     = new Account(1, "FR4401234567890123456780000", user1);
        user1.setId(1);
        user1.setFirstName("Nicolas");
        user1.setLastName("Biancucci");
        user1.setEmail("nico@gmail.com");
        user1.setPassword("azerty");
        user1.setBalance(10000.0);
        user1.setAccount(account1);
        user1.setContactList(contactList1);

        //WHEN
        when(userRepository.save(user1)).thenReturn(user1);
        userService.saveUser(user1);
        //THEN
        verify(userRepository, times(1)).save(user1);
    }

    @Test
    @DisplayName("Test deleteUserByEmail to UserService")
    public void deleteUserByEmailTest() {
        //GIVEN
        User          user1        = new User();
        List<Contact> contactList1 = new ArrayList<>();
        Account       account1     = new Account(1, "FR4401234567890123456780000", user1);
        user1.setId(1);
        user1.setFirstName("Nicolas");
        user1.setLastName("Biancucci");
        user1.setEmail("nico@gmail.com");
        user1.setPassword("azerty");
        user1.setBalance(10000.0);
        user1.setAccount(account1);
        user1.setContactList(contactList1);

        //WHEN
        userService.deleteByEmail("nico@gmail.com");
        //THEN
        verify(userRepository).deleteByEmail("nico@gmail.com");
    }

//    @Test
//    @DisplayName("Test deleteById to UserService")
//    public void deleteByIdTest() {
//        //GIVEN
//        User          user1        = new User();
//        List<Contact> contactList1 = new ArrayList<>();
//        Account       account1     = new Account(1, "FR4401234567890123456780000", user1);
//        user1.setId(1);
//        user1.setFirstName("Nicolas");
//        user1.setLastName("Biancucci");
//        user1.setEmail("nico@gmail.com");
//        user1.setPassword("azerty");
//        user1.setBalance(10000.0);
//        user1.setAccount(account1);
//        user1.setContactList(contactList1);
//
//        //WHEN
//        userService.deleteById(1);
//        //THEN
//        verify(userRepository).deleteById(1);
//    }


}
