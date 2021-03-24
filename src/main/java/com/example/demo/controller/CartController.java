package com.example.demo.controller;

import com.example.demo.model.product.Product;
import com.example.demo.service.cart.CartService;
import com.example.demo.service.cart.ICartService;
import com.example.demo.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private ICartService cartService;

    @Autowired
    IProductService productService;

    @ModelAttribute("listProduct")
    public List<Product>productList(){
        return productService.findAll();
    }

    @GetMapping("")
    public String list(@RequestParam Product product,@RequestParam(value ="quantity",required = false, defaultValue = "1")int quantity){
//        ModelAndView modelAndView=new ModelAndView("/cart/list");

        return "add";
    }
}
