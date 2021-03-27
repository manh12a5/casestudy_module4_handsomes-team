package com.example.demo.service.Cart;


import com.example.demo.model.cart.CartItem;
import com.example.demo.model.product.Product;
import com.example.demo.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartItemServiceImp implements ICartItemService {
    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public CartItem save(CartItem cartItem){
        return cartItemRepository.save(cartItem);
    }
}
