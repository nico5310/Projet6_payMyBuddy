package com.nico5310.PayMyBuddy.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "account")
public class Account  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAccount;

    @Column(name = "iban")
    private String iban;


    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "user_id")
    private User user;


    //GETTERS & SETTERS
    public int getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int id) {
        this.idAccount = id;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }



    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}