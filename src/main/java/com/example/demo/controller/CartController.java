package com.example.demo.controller;

import com.example.demo.model.cart.Cart;
import com.example.demo.model.cart.CartItem;
import com.example.demo.model.product.Product;
import com.example.demo.service.login.user.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("cart")
public class CartController {
    @Autowired
    private IAppUserService appUserService;

//    @GetMapping("")
//    public ModelAndView showCart(){
//        ModelAndView mav = new ModelAndView("view/cart");
//        Cart cart = appUserService.getCurrentUser().getCart();
//        List<CartItem> cartItems = cart.getCartItem();
//        mav.addObject("cartItems", cartItems);
//        return mav;
//    }

}
