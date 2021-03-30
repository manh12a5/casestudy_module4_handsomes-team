package com.example.demo.controller;

import com.example.demo.model.cart.Cart;
import com.example.demo.model.cart.CartItem;
import com.example.demo.model.product.Product;
import com.example.demo.service.Cart.ICartItemService;
import com.example.demo.service.category.ICategoryService;
import com.example.demo.service.login.user.IAppUserService;
import com.example.demo.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
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

    @Autowired
    private IProductService productService;



    @RequestMapping("")
    public ModelAndView home() {
        ModelAndView mav = new ModelAndView("/view/index");
        Cart cart = appUserService.getCurrentUser().getCart();
        List<CartItem> cartItems = cartItemService.findCartItemsByStatus(1, 2, cart.getId());
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
        List<CartItem> cartItems = cartItemService.findCartItemsByStatus(1, 2, cart.getId());
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
        List<CartItem> cartItems = cartItemService.findCartItemsByStatus(1, 2, cart.getId());
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
    public ModelAndView placeOrder(@RequestParam String placeOrder ) {
        ModelAndView modelAndView;
        modelAndView = new ModelAndView("view/checkout");
        if(placeOrder ==null) {System.out.println("loi");}
        else {
            Long card_id=Long.parseLong(placeOrder);
            List<CartItem> cartItems = cartItemService.findAllByCartIdAndStatus(card_id, 2);

            for (int i = 0; i < cartItems.size(); i++) {
                cartItems.get(i).setStatus(3);
                cartItemService.save(cartItems.get(i));
            }

        }

        return modelAndView;
    }

    @RequestMapping("/contact")
    public ModelAndView contact() {
        ModelAndView mav = new ModelAndView("/view/contact-us");
        Cart cart = appUserService.getCurrentUser().getCart();
        List<CartItem> cartItems = cartItemService.findCartItemsByStatus(1, 2, cart.getId());
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
        List<CartItem> cartItems = cartItemService.findCartItemsByStatus(1, 2, cart.getId());
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
        List<CartItem> cartItems = cartItemService.findCartItemsByStatus(1, 2, cart.getId());
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
        List<CartItem> cartItems = cartItemService.findCartItemsByStatus(1, 2, cart.getId());
        double subTotal = 0;
        for (CartItem c : cartItems) {
            double total = c.getProduct().getPrice() * c.getQuantity();
            subTotal += total;
        }
        mav.addObject("cartItems", cartItems);
        mav.addObject("subTotal", subTotal);
        return mav;
    }

    @RequestMapping("/category/{id}")
    public ModelAndView searchProductByCategory(@PathVariable Long id) {
        List<Product> productList = productService.findAllByCategoryId(id);
        ModelAndView modelAndView = new ModelAndView("view/category", "products", productList);
        Cart cart = appUserService.getCurrentUser().getCart();
        List<CartItem> cartItems = cartItemService.findCartItemsByStatus(1,2,cart.getId());
        double subTotal = 0;
        for (CartItem c :
                cartItems) {
            double total = c.getProduct().getPrice() * c.getQuantity();
            subTotal += total;
        }
        modelAndView.addObject("cartItems", cartItems);
        modelAndView.addObject("subTotal", subTotal);
        modelAndView.addObject("numberOfProductsCate",productList.size());
        modelAndView.addObject("category", categoryService.findById(id));
        return modelAndView;
    }

    @GetMapping("/search")
    public ModelAndView showSearchNameProduct(@RequestParam String name) {
        ModelAndView modelAndView = new ModelAndView("view/search");
        List<Product> productList = productService.findAllByName(name);
        Cart cart = appUserService.getCurrentUser().getCart();
        List<CartItem> cartItems = cartItemService.findCartItemsByStatus(1,2,cart.getId());
        double subTotal = 0;
        for (CartItem c :
                cartItems) {
            double total = c.getProduct().getPrice() * c.getQuantity();
            subTotal += total;
        }
        modelAndView.addObject("cartItems", cartItems);
        modelAndView.addObject("subTotal", subTotal);
        modelAndView.addObject("productList", productList);
        modelAndView.addObject("numberOfProductsSearch",productList.size());
        modelAndView.addObject("categories",categoryService.findAll());
        return modelAndView;
    }

    @GetMapping("/comment")
    public ModelAndView comment(){
        return new ModelAndView("view/comment");
    }

}
