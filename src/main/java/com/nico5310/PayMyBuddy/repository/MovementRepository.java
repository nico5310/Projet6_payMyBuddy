package com.nico5310.PayMyBuddy.repository;

import com.nico5310.PayMyBuddy.model.Movement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovementRepository extends JpaRepository<Movement, Integer> {

}
