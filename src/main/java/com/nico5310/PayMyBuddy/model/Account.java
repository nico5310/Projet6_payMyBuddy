package com.nico5310.PayMyBuddy.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "account")
public class Account  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "iban")
    private String iban;


    private Double balance;


    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"password", "balance", "account", "contactList"})
    private User user;

    public Account(int id, String iban,Double balance, User user) {

        this.id   = id;
        this.iban = iban;
        this.balance = balance;
        this.user = user;
    }

    public Account() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public Double getBalance() {

        return balance;
    }

    public void setBalance(Double balance) {

        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}