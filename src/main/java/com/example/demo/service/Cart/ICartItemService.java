package com.example.demo.service.Cart;


import com.example.demo.model.cart.CartItem;
import com.example.demo.service.IService;

import java.util.List;


public interface ICartItemService extends IService<CartItem> {
    void createCartItem(CartItem cartItem);

    List<CartItem> findAllByCartId(Long id);

    List<CartItem> findAllByStatus(int stt);

    List<CartItem> findAllByCartIdAndStatus(Long id,int stt);

    List<CartItem> findCartItemsByStatus(Integer stt1, Integer stt2, Long cartId);
}
