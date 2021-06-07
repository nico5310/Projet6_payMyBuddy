package com.nico5310.PayMyBuddy.Integration;

import com.nico5310.PayMyBuddy.model.User;
import com.nico5310.PayMyBuddy.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;


import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

//    @BeforeAll
//    public static void init() {
//        user1 = new User (3, "Lewis", "Hamilton", "lh@gmail.com", "44", 100000000.0, null, null);
//    }

    @Test
    @DisplayName("Test findAllUsers to UserService")
    void findAllUsersTest() throws Exception {

        this.mockMvc.perform(get("/api/user"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    @DisplayName("Test findAllUsers to UserService")
    void findByIdTest() throws Exception {

        this.mockMvc.perform(get("/api/user/id/1"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id", is(1)))
                    .andExpect(jsonPath("$.firstName", is("Nicolas")))
                    .andExpect(jsonPath("$.lastName", is("Biancucci")))
                    .andExpect(jsonPath("$.email", is("nico@gmail.com")));
    }

    @Test
    @DisplayName("Test findByEmail to UserService")
    void findByEmailTest() throws Exception {

        this.mockMvc.perform(get("/api/user/email/nico@gmail.com"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id", is(1)))
                    .andExpect(jsonPath("$.firstName", is("Nicolas")))
                    .andExpect(jsonPath("$.lastName", is("Biancucci")))
                    .andExpect(jsonPath("$.email", is("nico@gmail.com")));
    }

//    @Test
//    @DisplayName("Test saveUser to UserService")
//    void saveUserTest() throws Exception {
//
//        mockMvc.perform(post("/api/user")
//                .contentType(asJsonString(new User(3, "Lewis", "Hamilton", "lh@gmail.com", "44", 100000000.0, null, null)
//                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
//        this.mockMvc.perform(get("/api/user"))
//                    .andDo(print())
//                    .andExpect(status().isOk())
//                    .andExpect(jsonPath("$", hasSize(3)));
//    }
//
//    @Test
//    public void saveUserTest2() {
//        Mockito.when(userRepository.save(user1)).thenReturn(user1);
//
//    }

//    @Test
//    @DisplayName("Test saveUser to UserService")
//    void saveUser() {
//
//        User user = new User();
//        user.setFirstName("Nicolas");
//        user.setLastName("Biancucci");
//        user.setEmail("nico@gmail.com");
//        user.setPassword("azerty");
//        user.setBalance(10000.0);
//        user.setContactList(new User(2, "James", "Bond", "james@007.com", "spectre", 10000.0, null, null));
//        userRepository.save(user);
//
//        User user2 = new User();
//        user.setFirstName("James");
//        user.setLastName("Bond");
//        user.setEmail("james@007.com");
//        user.setPassword("spectre");
//        user.setBalance(10000.0);
//        userRepository.save(user2);
//
//
//        List<User> userList = this.userRepository.findAll();
//        assertEquals(2, userList.size());
//
//        assert(user.getFirstName().equals("Nicolas"));
//        assert(user.getLastName().equals("Biancucci"));
//        assert(user.getEmail().equals("nico@gmail.com"));
//        assert(user.getPassword().equals("azerty"));
//        assert (user.getBalance().equals(10000.0));
//    }

    @Test
    @DisplayName("Test updateUser to UserService")
    void updateUser() {

        Optional<User> user = userRepository.findById(2);
        user.get().setPassword("casino");
        userRepository.save(user.get());

        Optional<User> userList = this.userRepository.findById(2);
        assertEquals("casino", userList.get().getPassword());
    }


}
