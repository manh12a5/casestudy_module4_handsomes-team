package com.example.demo.service.cart;

import com.example.demo.model.cart.Cart;
import com.example.demo.model.cart.CartItem;
import com.example.demo.model.login.AppUser;
import com.example.demo.model.product.Product;
import com.example.demo.service.IService;

import java.util.List;


public interface ICartService extends IService<Cart> {
    Cart findByAppUser(AppUser appUser);

}
