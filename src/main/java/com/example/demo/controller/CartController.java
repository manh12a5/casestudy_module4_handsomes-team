package com.example.demo.controller;

import com.example.demo.model.product.Product;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("cart")
public class CartController {
    @GetMapping("")
    public ModelAndView showCart(){
        ModelAndView mav = new ModelAndView("cart/cart");
        mav.addObject("product1", new Product());
        return mav;
    }

}
