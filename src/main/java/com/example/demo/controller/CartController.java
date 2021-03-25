package com.example.demo.controller;

import com.example.demo.model.cart.Cart;
import com.example.demo.model.cart.CartItem;
import com.example.demo.model.login.AppUser;
import com.example.demo.model.product.Product;
import com.example.demo.service.cart.ICartService;
import com.example.demo.service.cartItem.ICartItemService;
import com.example.demo.service.product.IProductService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {
    @Autowired
    private ICartService cartService;

    @Autowired
    private IProductService productService;

    @Autowired
    private ICartItemService cartItemService;

    @ModelAttribute()
    public AppUser appUser(){
        return null;
    }
    @ModelAttribute("cart")
    public Cart cart(){
        Cart cart=cartService.findByAppUser(appUser());
        return cart;
    }
    @GetMapping("/cart/add/{id}")
    public ResponseEntity<Iterable<Product>> postCart(@PathVariable Long id) throws NotFoundException {
        Product product = productService.findById(id);
//        List<Product> allProductInCurrentCart = productService.findAllByCart(currentCart());
        Cart cart = null;
        if (cart() == null){
            cart = new Cart();
        }else {
            cart =                                                                                                                   cart();
        }
        boolean isContains = allProductInCurrentCart.contains(product);
        if (isContains){
            CartItem currentItems = cartItemService.getByCartIsAndProductIs(cart(), product);
            int currentQuantity = currentItems.getQuantity();
            currentQuantity++;
            currentItems.setQuantity(currentQuantity);
            cartItemService.save(currentItems);
        }else  {
            if (cart() == null){
                cart = new Cart();
                cart.setAppUser(cart());
                cartService.save(cart);
            }
            CartItem cartItem = new CartItem();
            cartItem.setQuantity(1);
            cartItem.setProduct(product);
            cartItem.setCart(cart);
            cartItemService.save(cartItem);
        }
        Iterable<Product> list= productService.findAllByCart(currentCart());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/cart/count")
    public ResponseEntity cout(@PathVariable Long id) throws NotFoundException {
        List<CartItem> cartItems = (List<CartItem>) cartItemService.findAllByCart(cart());
        return new ResponseEntity(cartItems,HttpStatus.OK);
    }

    @GetMapping("/total")
    public ResponseEntity total() {
        Double total  = 0.0;
        List<CartItem> cartItems = cartItemService.findByCart(cart());
        for (CartItem cartItem : cartItems){
            total += cartItem.getQuantity() * cartItem.getProduct().getPrice();
        }
        return new ResponseEntity(total,HttpStatus.OK);
    }

    @GetMapping("/quantity")
    public ResponseEntity quantity(){
        List<CartItem> cartItems = cartItemService.findByCart(cart());
        List<Integer> list = new ArrayList();
        for (CartItem cartItem : cartItems){
            list.add(cartItem.getQuantity());
        }
        return new ResponseEntity(list,HttpStatus.OK);
    }
}
