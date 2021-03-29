package com.example.demo.controller;

import com.example.demo.model.cart.Cart;
import com.example.demo.model.cart.CartItem;
import com.example.demo.model.product.Product;
import com.example.demo.service.Cart.ICartItemService;
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

    @Autowired
    private ICartItemService cartItemService;

    @GetMapping("")
    public ModelAndView showCart() {
        ModelAndView mav = new ModelAndView("view/cart");
        Cart cart = appUserService.getCurrentUser().getCart();
        List<CartItem> cartItems = cartItemService.findAllByCartId(cart.getId());
        int discount = 0;
        int couponDiscount = 0;
        int tax = 5;
        double subTotal = 0;
        for (CartItem c :
                cartItems) {
            double total = c.getProduct().getPrice() * c.getQuantity();
            subTotal += total;
        }
        double grandTotal = subTotal-discount-couponDiscount+((subTotal/100)*5);
        mav.addObject("cartItems", cartItems);
        mav.addObject("subTotal", subTotal);
        mav.addObject("discount", discount);
        mav.addObject("couponDiscount", couponDiscount);
        mav.addObject("tax", tax);
        mav.addObject("grandTotal", grandTotal);
        return mav;
    }

}
