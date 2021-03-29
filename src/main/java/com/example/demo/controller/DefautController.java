package com.example.demo.controller;

import com.example.demo.model.cart.Cart;
import com.example.demo.model.cart.CartItem;
import com.example.demo.service.Cart.ICartItemService;
import com.example.demo.service.category.ICategoryService;
import com.example.demo.service.login.user.IAppUserService;
import com.example.demo.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("")
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
        List<CartItem> cartItems = cartItemService.findCartItemsByStatus(1,2,cart.getId());
        double subTotal = 0;
        for (CartItem c : cartItems) {
            double total = c.getProduct().getPrice() * c.getQuantity();
            subTotal += total;
        }
        mav.addObject("cartItems", cartItems);
        mav.addObject("subTotal", subTotal);
        mav.addObject("categories", categoryService.findAll());
        return mav;
    }

    @RequestMapping("/about")
    public ModelAndView about() {
        ModelAndView mav = new ModelAndView("/view/about");
        Cart cart = appUserService.getCurrentUser().getCart();
        List<CartItem> cartItems = cartItemService.findCartItemsByStatus(1,2,cart.getId());
        double subTotal = 0;
        for (CartItem c : cartItems) {
            double total = c.getProduct().getPrice() * c.getQuantity();
            subTotal += total;
        }
        mav.addObject("cartItems", cartItems);
        mav.addObject("subTotal", subTotal);
        return mav;
    }

    @GetMapping("/checkout")
    public ModelAndView checkout() {
        ModelAndView mav = new ModelAndView("/view/checkout");
        Cart cart = appUserService.getCurrentUser().getCart();
        List<CartItem> cartItems = cartItemService.findCartItemsByStatus(1,2,cart.getId());
        double subTotal = 0;
        for (CartItem c : cartItems) {
            double total = c.getProduct().getPrice() * c.getQuantity();
            subTotal += total;
        }
        List<CartItem> checkoutList = cartItemService.findAllByCartIdAndStatus(cart.getId(), 2);
        double subTotalCheckoutList = 0;
        for (CartItem c : checkoutList) {
            double total = c.getProduct().getPrice() * c.getQuantity();
            subTotalCheckoutList += total;
        }
        mav.addObject("idCart", cart.getId());
        mav.addObject("subTotalCheckoutList", subTotalCheckoutList);
        mav.addObject("checkoutList", checkoutList);
        mav.addObject("cartItems", cartItems);
        mav.addObject("subTotal", subTotal);
        return mav;
    }

    @PostMapping("/checkout")
    public ModelAndView placeOrder(@RequestParam Long placeOrder) {
        ModelAndView modelAndView = new ModelAndView("view/checkout");
        List<CartItem> cartItems = cartItemService.findAllByCartIdAndStatus(placeOrder, 2);
        for (int i = 0; i < cartItems.size(); i++) {
                cartItems.get(i).setStatus(3);
                cartItemService.save(cartItems.get(i));
        }
        return modelAndView;
    }

    @RequestMapping("/contact")
    public ModelAndView contact() {
        ModelAndView mav = new ModelAndView("/view/contact-us");
        Cart cart = appUserService.getCurrentUser().getCart();
        List<CartItem> cartItems = cartItemService.findCartItemsByStatus(1,2,cart.getId());
        double subTotal = 0;
        for (CartItem c : cartItems) {
            double total = c.getProduct().getPrice() * c.getQuantity();
            subTotal += total;
        }
        mav.addObject("cartItems", cartItems);
        mav.addObject("subTotal", subTotal);
        return mav;
    }

    @RequestMapping("/my-account")
    public ModelAndView myAccount() {
        ModelAndView mav = new ModelAndView("/view/my-account");
        Cart cart = appUserService.getCurrentUser().getCart();
        List<CartItem> cartItems = cartItemService.findCartItemsByStatus(1,2,cart.getId());
        double subTotal = 0;
        for (CartItem c : cartItems) {
            double total = c.getProduct().getPrice() * c.getQuantity();
            subTotal += total;
        }
        mav.addObject("cartItems", cartItems);
        mav.addObject("subTotal", subTotal);
        return mav;
    }

    @RequestMapping("/service")
    public ModelAndView service() {
        ModelAndView mav = new ModelAndView("/view/service");
        Cart cart = appUserService.getCurrentUser().getCart();
        List<CartItem> cartItems = cartItemService.findCartItemsByStatus(1,2,cart.getId());
        double subTotal = 0;
        for (CartItem c : cartItems) {
            double total = c.getProduct().getPrice() * c.getQuantity();
            subTotal += total;
        }
        mav.addObject("cartItems", cartItems);
        mav.addObject("subTotal", subTotal);
        return mav;
    }

    @RequestMapping("/wishlist")
    public ModelAndView wishList() {
        ModelAndView mav = new ModelAndView("/view/wishlist");
        Cart cart = appUserService.getCurrentUser().getCart();
        List<CartItem> cartItems = cartItemService.findCartItemsByStatus(1,2,cart.getId());
        double subTotal = 0;
        for (CartItem c : cartItems) {
            double total = c.getProduct().getPrice() * c.getQuantity();
            subTotal += total;
        }
        mav.addObject("cartItems", cartItems);
        mav.addObject("subTotal", subTotal);
        return mav;
    }

}
