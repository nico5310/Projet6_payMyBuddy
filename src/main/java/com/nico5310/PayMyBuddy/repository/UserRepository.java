package com.nico5310.PayMyBuddy.repository;


import com.nico5310.PayMyBuddy.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);

    void deleteByEmail(String email);
   User findUsersByEmail(String email);

    void deleteAllById(Integer id);
}
