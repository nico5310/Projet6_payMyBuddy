package com.nico5310.PayMyBuddy.repository;


import com.nico5310.PayMyBuddy.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


    void deleteUserByEmail(String email);

    User findByEmail(String email);
}
