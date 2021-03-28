package com.example.demo.model.comment;

import com.example.demo.model.login.AppUser;
import com.example.demo.model.product.Product;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;

    @ManyToOne
    private AppUser user;

    @ManyToOne
    private Product product;

    private Timestamp date;

    public Comment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public Comment(Long id, String comment, AppUser user, Product product, Timestamp date) {
        this.id = id;
        this.comment = comment;
        this.user = user;
        this.product = product;
        this.date = date;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }
    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
