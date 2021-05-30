package com.nico5310.PayMyBuddy.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "date" )
    private LocalDate date;

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


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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
