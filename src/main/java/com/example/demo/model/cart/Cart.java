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
    private double total;
    @OneToMany
    private List<CartItem> cartItem;
    @OneToOne
    private AppUser appUser;

    public Cart() {
        this.cartItem = new ArrayList<>();
    }

    public Cart(Long id, Date date, double total, List<CartItem> cartItem, AppUser appUser) {
        this.id = id;
        this.date = date;
        this.total = total;
        this.cartItem = cartItem;
        this.appUser = appUser;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<CartItem> getCartItem() {
        return cartItem;
    }

    public void setCartItem(List<CartItem> cartItem) {
        this.cartItem = cartItem;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }
}
