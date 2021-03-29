package com.example.demo.service.Cart;


import com.example.demo.model.cart.CartItem;
import com.example.demo.service.IService;

import java.util.List;


public interface ICartItemService extends IService<CartItem> {
    void createCartItem(CartItem cartItem);

    List<CartItem> findAllByCartId(Long id);
}
