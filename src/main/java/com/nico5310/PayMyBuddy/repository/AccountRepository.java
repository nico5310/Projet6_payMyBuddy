package com.nico5310.PayMyBuddy.repository;

import com.nico5310.PayMyBuddy.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository <Account, Integer> {


}
