package com.example.demo.model.login;

import com.example.demo.model.cart.Cart;

import javax.persistence.*;

import java.sql.Date;

@Entity
public class AppUser {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String username;
    private String fullName;
    private String password;
    private String gmail;
    private Date birthday;
    private String phoneNumber;
    private String address;

    @ManyToOne
    private AppRole appRole;

    @OneToOne
    private Cart cart;

    public AppUser() {
    }

    public AppUser(Long id, String username, String fullName, String password, String gmail, Date birthday, String phoneNumber, String address, AppRole appRole) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.password = password;
        this.gmail = gmail;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.appRole = appRole;
    }

    public AppUser(Long id, String username, String fullName, String password, String gmail, Date birthday, String phoneNumber, String address, AppRole appRole, Cart cart) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.password = password;
        this.gmail = gmail;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.appRole = appRole;
        this.cart = cart;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public AppRole getAppRole() {
        return appRole;
    }

    public void setAppRole(AppRole appRole) {
        this.appRole = appRole;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}