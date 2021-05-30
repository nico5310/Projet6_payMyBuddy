package com.nico5310.PayMyBuddy.repository;

import com.nico5310.PayMyBuddy.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository <Contact, Integer> {


}
