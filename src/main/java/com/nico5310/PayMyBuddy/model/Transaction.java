package com.nico5310.PayMyBuddy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "transaction")
public class Transaction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "amount")
    private Double amountTransaction;

    @Column (name = "description")
    private String description;



    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "sender_user_id")
    private User senderUser;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "recipient_user_id")
    @JsonIgnoreProperties({"id", "lastName", "email", "password", "balance", "account", "contactList"})
    private User recipientUser;

    public Transaction(Integer id, LocalDate date, Double amountTransaction, String description,User senderUser, User recipientUser) {

        this.id                = id;
        this.date              = date;
        this.amountTransaction = amountTransaction;
        this.description       = description;
        this.senderUser        = senderUser;
        this.recipientUser     = recipientUser;
    }

    public Transaction() {

            }

    // GETTERS & SETTERS

    public Integer getId() {
        return id;
    }

    public void setId(Integer transactionId) {
        this.id = transactionId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getAmountTransaction() {
        return amountTransaction;
    }

    public void setAmountTransaction(Double amount) {
        this.amountTransaction = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public User getSenderUser() {
        return senderUser;
    }

    public void setSenderUser(User senderUser) {
        this.senderUser = senderUser;
    }

    public User getRecipientUser() {
        return recipientUser;
    }

    public void setRecipientUser(User recipientUser) {
        this.recipientUser = recipientUser;
    }




}
