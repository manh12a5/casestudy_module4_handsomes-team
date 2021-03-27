package com.example.demo.repository.cart;

import com.example.demo.model.cart.Cart;
import com.example.demo.model.login.AppUser;
import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends CrudRepository<Cart,Long> {
    Cart findByAppUser(AppUser appUser);
}
