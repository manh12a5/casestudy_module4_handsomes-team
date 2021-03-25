package com.example.demo.service.cart;

import com.example.demo.model.cart.Cart;
import com.example.demo.model.login.AppUser;
import com.example.demo.service.IService;



public interface ICartService extends IService<Cart> {
    Cart findByAppUser(AppUser appUser);

}
