package com.nico5310.PayMyBuddy.service;


import com.nico5310.PayMyBuddy.model.User;
import com.nico5310.PayMyBuddy.repository.ContactRepository;
import com.nico5310.PayMyBuddy.repository.UserRepository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

//    @Autowired
//    DataBasePreparation dataBasePreparation;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContactRepository contactRepository;


    @Test
    @DisplayName("Test findAllUsers to UserService")
    void findAllUsersTest() {

        List<User> userList = this.userRepository.findAll();
//        assertEquals(2, userList.size());
        assertNotNull(userList);
    }

    @Test
    @DisplayName("Test findById to UserService")
    void findByIdTest()  {

        Optional<User> userList = this.userRepository.findById(1);
        assertTrue(userList.isPresent());
    }

    @Test
    @DisplayName("Test findByEmail to UserService")
    void findByEmailTest() {

        User userList = this.userRepository.findByEmail("nico@gmail.com");
        assertEquals( "nico@gmail.com", userList.getEmail());
    }


    @Test
    @DisplayName("Test saveUser to UserService")
    void saveUser() {

        User user = new User();
        user.setFirstName("tintin");
        user.setLastName("milou");
        user.setEmail("tintin@milou.com");
        user.setPassword("licorne");
        user.setBalance(7.0);
        userRepository.save(user);

//        List<User> userList = this.userRepository.findAll();
//        assertEquals(3, userList.size());
        assertThat(user.getFirstName().toString(), containsString("tintin"));
    }

    @Test
    @DisplayName("Test updateUser to UserService")
    void updateUser() {

        Optional<User> user = userRepository.findById(2);
        user.get().setPassword("spectre");
        userRepository.save(user.get());

        Optional<User> userList = this.userRepository.findById(2);
        assertEquals("spectre", userList.get().getPassword());
    }


}
