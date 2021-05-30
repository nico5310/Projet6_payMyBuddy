package com.nico5310.PayMyBuddy.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "balance")
    private Double balance;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "user")
    private Account account;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user")
    private List<Contact> contactList = new ArrayList<>();


    // GETTERS & SETTERS
    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }

    public String getLastName() {

        return lastName;
    }

    public void setLastName(String lastName) {

        this.lastName = lastName;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public Double getBalance() {

        return balance;
    }

    public void setBalance(Double balance) {

        this.balance = balance;
    }

    public Account getAccount() {

        return account;
    }

    public void setAccount(Account account) {

        this.account = account;
    }

    public List<Contact> getContactList() {

        return contactList;
    }

    public void setContactList(List<Contact> contactList) {

        this.contactList = contactList;
    }


    @Override
    public String toString() {

        return "User [id=" + getId() + ", firstName=" + getFirstName() + ", lastName=" + getLastName() + ", email=" + getEmail() + ", password=" + getPassword() + ", balance=" + getBalance() + ", account =" + getAccount() + ", contact=" + getContactList() + "]";
    }

}
