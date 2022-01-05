package com.nico5310.PayMyBuddy.Integration;

import com.nico5310.PayMyBuddy.exception.NoCreateUserPossibleException;
import com.nico5310.PayMyBuddy.model.User;
import com.nico5310.PayMyBuddy.repository.ContactRepository;
import com.nico5310.PayMyBuddy.repository.UserRepository;
import com.nico5310.PayMyBuddy.service.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserServiceIT {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private UserService userService;


    @BeforeEach
    void setup() {

        userRepository.deleteAll();
        contactRepository.deleteAll();
    }

    @Test
    @DisplayName("Test findAllUsers to UserServiceIT")
    public void findAllUsersTest(){
        assertNotNull(userRepository.findAll());
    }


    @Test
    @DisplayName("Test findById to UserServiceIT")
    public void findByIdTestIT(){
        assertNotNull(userRepository.findById(1));
        assertNotNull(userRepository.findById(2));
    }

    @Test
    @DisplayName("Test findfindByEmailById to UserServiceIT")
    public void findByEmailTestIT(){
        assertNull(userRepository.findByEmail("nico@gmail.com"));

    }

    @Test
    @DisplayName("Test saveUser to UserService")
    void saveUserTest() {

        User user = new User();
        user.setFirstName("James");
        user.setLastName("Bond");
        user.setEmail("james@007.com");
        user.setPassword("spectre");
        user.setBalance(10000.0);
        userRepository.save(user);

        List<User> userList = this.userRepository.findAll();
        assertEquals(1, userList.size());

        assert(user.getFirstName().equals("James"));
        assert(user.getLastName().equals("Bond"));
        assert(user.getEmail().equals("james@007.com"));
        assert(user.getPassword().equals("spectre"));
        assert (user.getBalance().equals(10000.0));
    }

    @Test
    @DisplayName("Test updateUser to UserService")
    void updateUserTestIT() throws NoCreateUserPossibleException {
        //GIVEN
        User user = new User();
        user.setId(1);
        user.setFirstName("James");
        user.setLastName("Bond");
        user.setEmail("james@007.com");
        user.setPassword("spectre");
        user.setBalance(10000.0);
        userService.saveUser(user);

        List<User> userList = userRepository.findAll();
        Assertions.assertTrue(userList.toString().contains("James"));

        User userUpdate = new User();
        userUpdate.setFirstName("Lewis");
        userUpdate.setLastName("Hamilton");
        userUpdate.setEmail("44@44.com");
        userUpdate.setPassword("mercedes");
        userUpdate.setBalance(10000.0);
        //WHEN
        userService.updateUser(user.getId(), userUpdate);
        //THEN
        Assertions.assertTrue(userRepository.findById(1).get().getLastName().contains("Hamilton"));
    }



    @Test
    @DisplayName("Test deleteUserByEmail to UserService")
    void deleteUserByEmailTestIT() throws NoCreateUserPossibleException {
        //GIVEN
        User user = new User();
        user.setId(1);
        user.setFirstName("James");
        user.setLastName("Bond");
        user.setEmail("james@007.com");
        user.setPassword("spectre");
        user.setBalance(10000.0);
        user.setAccount(null);
        user.setContactList(null);
        userService.saveUser(user);

        //WHEN
        userService.deleteByEmail(user.getEmail());
        //THEN
        assertFalse(userRepository.findById(1).isPresent());
    }

    @Test
    @DisplayName("Test deleteUserByEmail to UserService")
    void findContactByUserIdTestIT()throws NoCreateUserPossibleException {
        //GIVEN
        User user = new User();
        user.setId(1);
        user.setFirstName("James");
        user.setLastName("Bond");
        user.setEmail("james@007.com");
        user.setPassword("spectre");
        user.setBalance(10000.0);
        user.setAccount(null);
        user.setContactList(null);
        user.setEnabled(true);
        user.setRole("USER");
        userService.saveUser(user);

        //WHEN
        userService.findContactByUserId(user.getId());
        //THEN
        assertTrue(userRepository.findById(1).isPresent());
    }

    @Test
    @DisplayName("Test deleteUserByEmail to UserService")
    void deleteContactByIDTestIT()throws NoCreateUserPossibleException {
        //GIVEN
        User user = new User();
        user.setId(1);
        user.setFirstName("James");
        user.setLastName("Bond");
        user.setEmail("james@007.com");
        user.setPassword("spectre");
        user.setBalance(10000.0);
        user.setAccount(null);
        user.setContactList(null);
        user.setEnabled(true);
        user.setRole("USER");
        userService.saveUser(user);

        //WHEN
        userService.deleteContactByID(user.getId());
        //THEN
        assertTrue(userRepository.findById(1).isPresent());
    }


}
