package com.nico5310.PayMyBuddy.model;

import com.sun.istack.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "user")
public class User implements Serializable, UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotBlank
    @Column(name = "first_name")
    private String firstName;

    @NotBlank
    @Column(name = "last_name")
    private String lastName;

    @NotBlank
    @Column(name = "email", unique = true)
    private String email;

    @NotBlank
    @Column(name = "password")
    private String password;

    @Column(name = "balance")
    private Double balance;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Account account;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Contact> contactList = new ArrayList<>();

    private boolean enabled;

    private String role;


    public User(Integer id, String firstName, String lastName, String email, String password, Double balance, Account account, List<Contact> contactList, boolean enabled, String role) {

        this.id          = id;
        this.firstName   = firstName;
        this.lastName    = lastName;
        this.email       = email;
        this.password    = password;
        this.balance     = balance;
        this.account     = account;
        this.contactList = contactList;
        this.enabled     = enabled;
        this.role        = role;
    }

    public User() {

    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {

        return password;
    }

    @Override
    public String getUsername() {

        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    @Override
    public boolean isAccountNonLocked() {

        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {

        return true;
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

        return "User [id=" + getId() + ", firstName=" + getFirstName() + ", lastName=" + getLastName() + ", email=" + getEmail() + ", password=" + getPassword() + ", balance=" + getBalance() + ", account =" + getAccount() + ", contact=" + getContactList() + ", enabled=" + enabled + ", role='" + role + '\'' + "]";
    }

}
