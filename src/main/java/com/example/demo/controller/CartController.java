package com.example.demo.controller;

import com.example.demo.model.cart.Cart;
import com.example.demo.model.cart.CartItem;
import com.example.demo.service.Cart.ICartItemService;
import com.example.demo.service.login.user.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
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
        List<CartItem> cartItems = cartItemService.findCartItemsByStatus(1, 2, cart.getId());
        int tax = 5;
        double subTotal = 0;
        for (CartItem c :
                cartItems) {
            double total = c.getProduct().getPrice() * c.getQuantity();
            subTotal += total;
        }
        double grandTotal = subTotal - +((subTotal / 100) * tax);
        mav.addObject("cartItems", cartItems);
        mav.addObject("subTotal", subTotal);
        mav.addObject("grandTotal", grandTotal);
        return mav;
    }

    @PostMapping("")
    public ModelAndView selectCartItem(@RequestParam List<Long> selected, int size, int quantity) {
        List<Long> checkBoxList = selected;
        Cart cart = appUserService.getCurrentUser().getCart();
        List<CartItem> cartItems = cartItemService.findAllByCartId(cart.getId());
        for (int i = 0; i < cartItems.size(); i++) {
            for (int j = 0; j < checkBoxList.size(); j++) {
                if (cartItems.get(i).getId() == checkBoxList.get(j)) {
                    cartItems.get(i).setSize(size);
                    cartItems.get(i).setQuantity(quantity);
                    cartItems.get(i).setStatus(2);
                    cartItemService.save(cartItems.get(i));
                }
            }
        }
        return new ModelAndView("redirect:/checkout");
    }


}
