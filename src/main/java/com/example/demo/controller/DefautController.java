package com.example.demo.controller;

import com.example.demo.model.category.Category;
import com.example.demo.model.product.Product;
import com.example.demo.service.category.CategoryServiceImp;
import com.example.demo.service.category.ICategoryService;
import com.example.demo.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller

public class DefautController {

    @Autowired
    private ICategoryService categoryServiceImp;

    @Autowired
    private IProductService productService;

    @RequestMapping("")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("view/index");
        modelAndView.addObject("categories", categoryServiceImp.findAll());
        return modelAndView;
    }

    @RequestMapping("about")
    public ModelAndView about(){
        return new ModelAndView("view/about");
    }

    //SearchNameProduct
    @PostMapping("/search")
    public ModelAndView showSearchNameProduct(@RequestParam String name, @PageableDefault(size = 6) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("view/index");
        String nameProduct = "%" + name + "%";
        Page<Product> productList = productService.findProductByName(nameProduct, pageable);
        modelAndView.addObject("products", productList);
        return modelAndView;
    }

}
