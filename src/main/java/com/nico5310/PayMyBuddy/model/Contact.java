package com.nico5310.PayMyBuddy.model;

import javax.persistence.*;

@Entity
@Table(name = "user_contacts")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_contacts_id")
    private User userContact;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    //GETTERS & SETTERS

    public long getId() {
        return id;
    }

    public void setId(long id) {
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


//    @Override
//    public String toString() {
//
//        return "Contact [id=" + getId() + ", userContact=" + getUserContact() + ", user =" + getUser()+ "]";
//    }

}
