package com.example.demo.controller;

import com.example.demo.model.cart.Cart;
import com.example.demo.model.cart.CartItem;
import com.example.demo.service.Cart.ICartItemService;
import com.example.demo.service.category.ICategoryService;
import com.example.demo.service.login.user.IAppUserService;
import com.example.demo.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller

public class DefautController {

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IAppUserService appUserService;

    @Autowired
    private ICartItemService cartItemService;



    @RequestMapping("")
    public ModelAndView home() {
        ModelAndView mav = new ModelAndView("/view/index");
        Cart cart = appUserService.getCurrentUser().getCart();
        List<CartItem> cartItems = cartItemService.findAllByCartId(cart.getId());
        double subTotal = 0;
        for (CartItem c : cartItems) {
            double total = c.getProduct().getPrice() * c.getQuantity();
            subTotal += total;
        }
        mav.addObject("cartItems", cartItems);
        mav.addObject("subTotal", subTotal);
        mav.addObject("categories",categoryService.findAll());
        return mav;
    }

    @RequestMapping("/about")
    public ModelAndView about(){
        ModelAndView mav = new ModelAndView("/view/about");
        Cart cart = appUserService.getCurrentUser().getCart();
        List<CartItem> cartItems = cartItemService.findAllByCartId(cart.getId());
        double subTotal = 0;
        for (CartItem c : cartItems) {
            double total = c.getProduct().getPrice() * c.getQuantity();
            subTotal += total;
        }
        mav.addObject("cartItems", cartItems);
        mav.addObject("subTotal", subTotal);
        mav.addObject("categories",categoryService.findAll());
        return mav;
    }

    @RequestMapping("/checkout")
    public ModelAndView checkout(){
        ModelAndView mav = new ModelAndView("/view/checkout");
        Cart cart = appUserService.getCurrentUser().getCart();
        List<CartItem> cartItems = cartItemService.findAllByCartId(cart.getId());
        double subTotal = 0;
        for (CartItem c : cartItems) {
            double total = c.getProduct().getPrice() * c.getQuantity();
            subTotal += total;
        }
        mav.addObject("cartItems", cartItems);
        mav.addObject("subTotal", subTotal);
        mav.addObject("categories",categoryService.findAll());
        return mav;
    }

    @RequestMapping("/contact")
    public ModelAndView contact(){
        ModelAndView mav = new ModelAndView("/view/contact-us");
        Cart cart = appUserService.getCurrentUser().getCart();
        List<CartItem> cartItems = cartItemService.findAllByCartId(cart.getId());
        double subTotal = 0;
        for (CartItem c : cartItems) {
            double total = c.getProduct().getPrice() * c.getQuantity();
            subTotal += total;
        }
        mav.addObject("cartItems", cartItems);
        mav.addObject("subTotal", subTotal);
        mav.addObject("categories",categoryService.findAll());
        return mav;
    }

    @RequestMapping("/my-account")
    public ModelAndView myAccount(){
        ModelAndView mav = new ModelAndView("/view/my-account");
        Cart cart = appUserService.getCurrentUser().getCart();
        List<CartItem> cartItems = cartItemService.findAllByCartId(cart.getId());
        double subTotal = 0;
        for (CartItem c : cartItems) {
            double total = c.getProduct().getPrice() * c.getQuantity();
            subTotal += total;
        }
        mav.addObject("cartItems", cartItems);
        mav.addObject("subTotal", subTotal);
        mav.addObject("categories",categoryService.findAll());
        return mav;
    }

    @RequestMapping("/service")
    public ModelAndView service(){
        ModelAndView mav = new ModelAndView("/view/service");
        Cart cart = appUserService.getCurrentUser().getCart();
        List<CartItem> cartItems = cartItemService.findAllByCartId(cart.getId());
        double subTotal = 0;
        for (CartItem c : cartItems) {
            double total = c.getProduct().getPrice() * c.getQuantity();
            subTotal += total;
        }
        mav.addObject("cartItems", cartItems);
        mav.addObject("subTotal", subTotal);
        mav.addObject("categories",categoryService.findAll());
        return mav;
    }

    @RequestMapping("/wishlist")
    public ModelAndView wishList(){
        ModelAndView mav = new ModelAndView("/view/wishlist");
        Cart cart = appUserService.getCurrentUser().getCart();
        List<CartItem> cartItems = cartItemService.findAllByCartId(cart.getId());
        double subTotal = 0;
        for (CartItem c : cartItems) {
            double total = c.getProduct().getPrice() * c.getQuantity();
            subTotal += total;
        }
        mav.addObject("cartItems", cartItems);
        mav.addObject("subTotal", subTotal);
        mav.addObject("categories",categoryService.findAll());
        return mav;
    }

}
