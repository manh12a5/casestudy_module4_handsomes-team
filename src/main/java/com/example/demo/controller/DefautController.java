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
        return new ModelAndView("view/index");
    }

    @RequestMapping("about")
    public ModelAndView about(){
        return new ModelAndView("view/about");
    }

    @RequestMapping("detail")
    public ModelAndView test(){
        return new ModelAndView("shop-detail");
    }

}
