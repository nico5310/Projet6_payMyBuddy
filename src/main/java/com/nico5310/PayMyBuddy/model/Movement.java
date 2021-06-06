package com.nico5310.PayMyBuddy.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "movement")
public class Movement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Column(name = "type_operation")
    private String typeOperation;

    @Column(name = "amount")
    private double amountMovement;

    @Column(name = "date")
    private LocalDate date;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;


    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {

        this.id = id;
    }

    public String getTypeOperation() {

        return typeOperation;
    }

    public void setTypeOperation(String typeOperation) {

        this.typeOperation = typeOperation;
    }

    public Double getAmountMovement() {

        return amountMovement;
    }

    public void setAmountMovement(Double amount) {

        this.amountMovement = amount;
    }

    public LocalDate getDate() {

        return date;
    }

    public void setDate(LocalDate date) {

        this.date = date;
    }

    public Integer getUser() {

        return user.getId();
    }

    public void setUser(User user) {

        this.user = user;
    }

    public Account getAccount() {

        return account;
    }

    public void setAccount(Account account) {

        this.account = account;
    }
}
