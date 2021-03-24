package com.example.demo.repository.cart;

import com.example.demo.model.cart.Cart;
import com.example.demo.model.cart.CartItem;
import com.example.demo.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    Iterable<CartItem>findAllByCart(Cart cart);
//    CartItem getByCartIsAndProductIs(Cart cart, Product product);
}
