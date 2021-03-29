package com.example.demo.controller;

import com.example.demo.model.product.Product;
import com.example.demo.service.category.ICategoryService;
import com.example.demo.service.login.user.IAppUserService;
import com.example.demo.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller

public class DefautController {

    @Autowired
    private ICategoryService categoryServiceImp;

    @Autowired
    private IAppUserService appUserService;

    @RequestMapping("")
<<<<<<< HEAD
    public ModelAndView home(@PageableDefault(size = 5) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("view/index");
        modelAndView.addObject("categories", categoryServiceImp.findAll());
        modelAndView.addObject("top5products", productService.findTop5ByOrderByPriceDesc(pageable));
        return modelAndView;
=======
    public ModelAndView home() {
        ModelAndView mav = new ModelAndView("/view/index");
        mav.addObject("categories",categoryService.findAll());
        return mav;
>>>>>>> khai
    }

    @RequestMapping("/about")
    public ModelAndView about(){
        return new ModelAndView("/view/about");
    }

    @RequestMapping("/checkout")
    public ModelAndView checkout(){
        return new ModelAndView("/view/checkout");
    }

    @RequestMapping("/contact")
    public ModelAndView contact(){
        return new ModelAndView("/view/contact-us");
    }

<<<<<<< HEAD
    //SearchNameProduct
    @PostMapping("/search")
    public ModelAndView showSearchNameProduct(@RequestParam String name, @PageableDefault(size = 6) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("view/index");
        String nameProduct = "%" + name + "%";
        Page<Product> productList = productService.findProductByName(nameProduct, pageable);
        modelAndView.addObject("products", productList);
        return modelAndView;
=======
    @RequestMapping("/my-account")
    public ModelAndView myAccount(){
        return new ModelAndView("/view/my-account");
    }

    @RequestMapping("/service")
    public ModelAndView service(){
        return new ModelAndView("/view/service");
    }

    @RequestMapping("/wishlist")
    public ModelAndView wishList(){
        return new ModelAndView("/view/wishlist");
>>>>>>> khai
    }

}
