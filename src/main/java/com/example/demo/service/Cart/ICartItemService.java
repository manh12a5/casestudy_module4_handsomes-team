package com.example.demo.service.Cart;


import com.example.demo.model.cart.CartItem;
import com.example.demo.service.IService;


public interface ICartItemService extends IService<CartItem> {
    void createCartItem(CartItem cartItem);
}
