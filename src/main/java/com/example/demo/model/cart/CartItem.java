package com.example.demo.model.cart;

import com.example.demo.model.product.Product;

import javax.persistence.*;
import java.util.List;

@Entity
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Product product;
    private int quantity;
    private double unit_price;
    private double total;
    private int status;
    @ManyToOne
    private Cart cart;


    public CartItem() {
    }

    public CartItem(Long id, Product product, int quantity, double price, double total, Cart cart) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.unit_price = price;
        this.total = total;
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

    public double getPrice_product() {
        return this.product.getPrice();
    }

    public void setPrice(double price) {
        this.unit_price = price;
    }

    public double getTotal() {
        return total = getPrice_product() * getQuantity();
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
