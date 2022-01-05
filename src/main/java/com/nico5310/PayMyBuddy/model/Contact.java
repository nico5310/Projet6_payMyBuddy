package com.nico5310.PayMyBuddy.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_contacts")
public class Contact implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "user_contact_id")
    @JsonIgnoreProperties({"password", "balance", "account", "contactList"})
    private User userContact;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"password", "balance", "account", "contactList"})
    private User user;

    public  Contact(Integer id, User userContact, User user) {
        this.id = id;
        this.userContact = userContact;
        this.user = user;
    }

    public Contact() {

    }

    //GETTERS & SETTERS

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUserContact() {
        return userContact;
    }

    public void setUserContact(User userContact) {
        this.userContact = userContact;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Contact {" + "id=" + getId() + "userContact=" + getUserContact() + "user=" + getUser() + "}" ;
    }


}
