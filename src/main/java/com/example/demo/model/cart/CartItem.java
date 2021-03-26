package com.example.demo.model.cart;

import com.example.demo.model.product.Product;

import javax.persistence.*;
import java.util.List;

@Entity
public class CartItem{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Product product;
    private int quantity;
    private boolean status;
    @ManyToOne
    private Cart cart;


    public CartItem() {
    }

    public CartItem(Long id, Product product, int quantity, boolean status, Cart cart) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.status = status;
        this.cart = cart;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

}
