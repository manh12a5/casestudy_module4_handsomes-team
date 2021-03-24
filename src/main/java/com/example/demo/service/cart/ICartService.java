package com.example.demo.service.cart;

import com.example.demo.model.cart.CartItem;
import com.example.demo.model.product.Product;

import java.util.List;


public interface ICartService {
    CartItem getItem(Product product);

    List<CartItem> getItems();

    int getItemCount();

    void addItem(Product product,int quantity);
    void updateItem(Product product,int quantity);
    void remoteItem(Product product);
    void remoteCart();
    boolean isEmpty();
}
