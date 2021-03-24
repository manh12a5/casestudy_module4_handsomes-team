package com.example.demo.model.product;

import com.example.demo.model.category.Category;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
    private int size;
    @Transient
    private MultipartFile image;

    private String description;

    @ManyToOne
    private Category category;

    public Product() {
    }

    public Product(Long id, String name, double price, int size, MultipartFile image, String description, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.size = size;
        this.image = image;
        this.description = description;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
