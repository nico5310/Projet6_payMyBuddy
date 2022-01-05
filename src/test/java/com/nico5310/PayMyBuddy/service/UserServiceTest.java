package com.nico5310.PayMyBuddy.service;

import com.nico5310.PayMyBuddy.exception.NoCreateUserPossibleException;
import com.nico5310.PayMyBuddy.exception.NoFoundException;
import com.nico5310.PayMyBuddy.model.Account;
import com.nico5310.PayMyBuddy.model.Contact;
import com.nico5310.PayMyBuddy.model.User;
import com.nico5310.PayMyBuddy.repository.ContactRepository;
import com.nico5310.PayMyBuddy.repository.UserRepository;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {UserService.class})
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
        Account       account1     = new Account(1, "FR4401234567890123456780000", 10000.0, user1);
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
        Account       account2     = new Account(2, "FR4401234567890123456780002", 10000.0, user2);
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
        Account       account1     = new Account(1, "FR4401234567890123456780000", 10000.0, user1);
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
        Account       account1     = new Account(1, "FR4401234567890123456780000", 10000.0, user1);
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
    public void saveUserTest() throws NoCreateUserPossibleException {
        //GIVEN
        User          user1        = new User();
        List<Contact> contactList1 = new ArrayList<>();
        Account       account1     = new Account(1, "FR4401234567890123456780000", 10000.0, user1);
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
        Account       account1     = new Account(1, "FR4401234567890123456780000", 10000.0, user1);
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

    @Test
    @DisplayName("Test findContactByUserEmail to UserService")
    public void findContactByUserEmailTest() {
        //GIVEN
        List<Contact> contactList = new ArrayList<>();
        when(contactRepository.findContactByUserEmail(any(String.class))).thenReturn(contactList);
        List<Contact> contactList2 = userService.findContactByUserEmail("nico@gmail.com");
        assertSame(contactList, contactList2);
        assertTrue(contactList2.isEmpty());
        //WHEN
        verify(this.contactRepository).findContactByUserEmail(anyString());
        //THEN
        assertTrue(this.userService.findAllUsers().isEmpty());
    }

    @Test
    @DisplayName("Test usersExceptFriends to UserService")
    public void usersExceptFriendsTest() {
        //GIVEN
        User    user    = new User();
        Account account = new Account();
        user.setFirstName("Nicolas");
        user.setLastName("Biancucci");
        user.setEmail("nico@gmail.com");
        user.setPassword("azerty");
        user.setBalance(10000.0);
        user.setAccount(new Account());
        user.setRole("USER");
        user.setId(1);
        user.setContactList(new ArrayList<Contact>());
        user.setEnabled(true);
        account.setId(1);
        account.setIban("FR4401234567890123456780000");
        account.setBalance(10.0);
        account.setUser(user);

        User    user1    = new User();
        Account account1 = new Account();
        user1.setFirstName("James");
        user1.setLastName("Bond");
        user1.setEmail("james@007.com");
        user1.setPassword("spectre");
        user1.setBalance(10000.0);
        user1.setAccount(account1);
        user1.setRole("USER");
        user1.setId(2);
        user1.setContactList(new ArrayList<Contact>());
        user1.setEnabled(true);
        account1.setId(2);
        account1.setIban("FR4401234567890123456780002");
        account1.setBalance(10000.0);
        account1.setUser(user1);

        User    user2    = new User();
        Account account2 = new Account();
        user2.setFirstName("Oncle");
        user2.setLastName("Sam");
        user2.setEmail("s10@samsung.com");
        user2.setPassword("samsung");
        user2.setBalance(10000.0);
        user2.setAccount(account2);
        user2.setRole("USER");
        user2.setId(3);
        user2.setContactList(new ArrayList<Contact>());
        user2.setEnabled(true);
        account2.setId(3);
        account2.setIban("FR4401234567890123456780004");
        account2.setBalance(10.0);
        account2.setUser(user2);
        //WHEN
        when(userRepository.findUsersByEmail(anyString())).thenReturn(user2);
        List<User> userList = new ArrayList<User>();
        when(userRepository.findAll()).thenReturn(userList);
        List<User> userList1 = userService.usersExceptFriends("nico@gmail.com");
        assertSame(userList, userList1);
        assertTrue(userList1.isEmpty());
        //THEN
        verify(userRepository).findAll();
        verify(userRepository).findByEmail(anyString());
        verify(userRepository).findUsersByEmail(anyString());
        assertSame(userList1, userService.findAllUsers());
    }

    @Test
    @DisplayName("Test saveContact to UserService")
    public void saveContactTest() {
        //WHEN
        User    user    = new User();
        Account account = new Account();
        user.setId(1);
        user.setFirstName("Nicolas");
        user.setLastName("Biancucci");
        user.setEmail("nico@gmail.com");
        user.setPassword("azerty");
        user.setBalance(10000.0);
        user.setAccount(new Account());
        user.setRole("USER");
        user.setContactList(new ArrayList<Contact>());
        user.setEnabled(true);
        account.setId(1);
        account.setIban("FR4401234567890123456780000");
        account.setBalance(10.0);
        account.setUser(user);

        User    user1    = new User();
        Account account1 = new Account();
        user1.setFirstName("James");
        user1.setLastName("Bond");
        user1.setEmail("james@007.com");
        user1.setPassword("spectre");
        user1.setBalance(10000.0);
        user1.setAccount(account1);
        user1.setRole("USER");
        user1.setId(2);
        user1.setContactList(new ArrayList<Contact>());
        user1.setEnabled(true);
        account1.setId(2);
        account1.setIban("FR4401234567890123456780002");
        account1.setBalance(10000.0);
        account1.setUser(user1);
        Optional<User> userTest = Optional.<User>of(user1);
        //WHEN
        when(userRepository.findById((Integer) org.mockito.Mockito.any())).thenReturn(userTest);
        assertThrows(NoFoundException.class, () -> this.userService.saveContact(1, 1));
        //THEN
        verify(this.userRepository, times(2)).findById((Integer) org.mockito.Mockito.any());
    }





}
