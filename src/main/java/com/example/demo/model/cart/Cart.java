package com.example.demo.model.cart;


import com.example.demo.model.login.AppUser;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    @OneToMany
    private List<CartItem> cartItemList;
    @OneToOne
    private AppUser appUser;

    public Cart() {
        this.cartItemList = new ArrayList<>();
    }

    public Cart(Long id, Date date, List<CartItem> cartItem, AppUser appUser) {
        this.id = id;
        this.date = date;
        this.cartItemList = cartItem;
        this.appUser = appUser;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<CartItem> getCartItem() {
        return cartItemList;
    }

    public void setCartItem(List<CartItem> cartItem) {
        this.cartItemList = cartItem;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CartItem> getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }
}
