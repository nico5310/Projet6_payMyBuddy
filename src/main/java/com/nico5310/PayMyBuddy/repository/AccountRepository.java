package com.nico5310.PayMyBuddy.repository;

import com.nico5310.PayMyBuddy.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository <Account, Integer> {

    Account findAccountByUserEmail(String email);

    Account findAccountById(Integer idAccount);


}
