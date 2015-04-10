package org.rogue.models;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Kevin on 2015-04-07.
 */
@Entity
@SelectBeforeUpdate
@DynamicUpdate
public class User implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    @Column(unique = true)
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    @ManyToOne
    private Address address;
    private String status;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    public User() {
    }

    public User(String email, String firstName, String password, String lastName) {
        this.email = email;
        this.firstName = firstName;
        this.password = password;
        this.lastName = lastName;
    }

    public User(String email, String password, String firstName, String lastName, Address address, String status, AccountType accountType) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.status = status;
        this.accountType = accountType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    enum AccountType {
        USER,
        ADMIN
    }
}
