package com.example.demo.controller;

import com.example.demo.service.category.ICategoryService;
import com.example.demo.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class DefautController {

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IProductService productService;



    @RequestMapping("")
    public ModelAndView home() {
        ModelAndView mav = new ModelAndView("/view/index");
        mav.addObject("categories",categoryService.findAll());
        return mav;
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
    }

}
