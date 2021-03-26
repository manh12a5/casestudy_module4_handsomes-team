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
}
