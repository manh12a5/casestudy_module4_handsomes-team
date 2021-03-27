package com.example.demo.controller;

<<<<<<< HEAD
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DefautController {
    @RequestMapping("home")
    public ModelAndView index(){
        return new ModelAndView("shop/index");
=======
import com.example.demo.model.category.Category;
import com.example.demo.model.product.Product;
import com.example.demo.service.category.CategoryServiceImp;
import com.example.demo.service.category.ICategoryService;
import com.example.demo.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller

public class DefautController {

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IProductService productService;



    @RequestMapping("")
    public ModelAndView home() {
        return new ModelAndView("view/index");
>>>>>>> demo-of-khai
    }

    @RequestMapping("about")
    public ModelAndView about(){
<<<<<<< HEAD
        return new ModelAndView("shop/about");
    }
=======
        return new ModelAndView("view/about");
    }




>>>>>>> demo-of-khai
}
