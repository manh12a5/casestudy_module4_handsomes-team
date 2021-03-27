package com.example.demo.service.Cart;

import com.example.demo.model.cart.Cart;
import com.example.demo.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService implements ICartService{
    @Autowired
    private CartRepository cartRepository;
    @Override
    public Cart save(Cart cart){
        return cartRepository.save(cart);
    }
}
