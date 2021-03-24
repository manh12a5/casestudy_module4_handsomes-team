package com.example.demo.controller;

import com.example.demo.model.category.Category;
import com.example.demo.service.category.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryServiceImp categoryServiceImp;

    @GetMapping("")
    public ModelAndView showCategories(){
        ModelAndView modelAndView = new ModelAndView("category/list");
        modelAndView.addObject("categories", categoryServiceImp.findAll());
        return modelAndView;
    }
    @GetMapping("/create-cate")
    public ModelAndView showFormCreateCate(){
        ModelAndView modelAndView = new ModelAndView("/category/create");
        modelAndView.addObject("category", new Category());
        return modelAndView;
    }

    @PostMapping("/create-cate")
    public ModelAndView createCate(@ModelAttribute Category category){
        categoryServiceImp.save(category);
        ModelAndView mav = new ModelAndView("/category/create");
        mav.addObject("category",new Category());
        mav.addObject("message", "New customer created successfully");
        return mav;
    }

    @GetMapping("/edit-cate/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        Category category = categoryServiceImp.findById(id);
        if(category != null) {
            ModelAndView modelAndView = new ModelAndView("/category/edit");
            modelAndView.addObject("category", category);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-cate")
    public ModelAndView updateCate(@ModelAttribute("category") Category category){
        categoryServiceImp.save(category);
        ModelAndView modelAndView = new ModelAndView("/category/edit");
        modelAndView.addObject("category", category);
        modelAndView.addObject("message", "Category updated successfully");
        return modelAndView;
    }

    @GetMapping("/del-cate/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        Category category = categoryServiceImp.findById(id);
        if(category != null) {
            ModelAndView modelAndView = new ModelAndView("/category/delete");
            modelAndView.addObject("category", category);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/del-cate")
    public ModelAndView deleteCate(@ModelAttribute("category") Category category){
        categoryServiceImp.remove(category.getId());
        ModelAndView mav = new ModelAndView("redirect:/categories");
        return mav;
    }

}
