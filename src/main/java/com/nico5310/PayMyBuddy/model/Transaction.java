package com.nico5310.PayMyBuddy.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
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
    private Double amount;

    @Column (name = "description")
    private String description;

    @Column (name = "fee")
    private BigDecimal fee;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sender_user_id")
    private User senderUser;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "recipient_user_id")
    private User recipientUser;


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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
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
