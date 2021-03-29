package com.example.demo.controller;

import com.example.demo.model.category.Category;
import com.example.demo.model.product.Product;
import com.example.demo.service.category.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryServiceImp categoryServiceImp;

    @Autowired
    Environment environment;

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
        ModelAndView mav = new ModelAndView("/category/create");
        MultipartFile multipartFile = category.getAvatar();
        String fileName = multipartFile.getOriginalFilename();
        String fileUpload = environment.getProperty("upload.path");
        String newFile = fileUpload + fileName;
        try {
            FileCopyUtils.copy(multipartFile.getBytes(), new File(newFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        category.setImage(fileName);
        categoryServiceImp.save(category);
        mav.addObject("category", category);
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
            return new ModelAndView("/error.404");
        }
    }

    @PostMapping("/edit-cate")
    public ModelAndView updateCate(@ModelAttribute("category") Category category) {
        ModelAndView modelAndView = new ModelAndView("/category/edit");
        MultipartFile multipartFile = category.getAvatar();
        String fileName = multipartFile.getOriginalFilename();
        String fileUpload = environment.getProperty("upload.path");
        String newFile = fileUpload + fileName;
        try {
            FileCopyUtils.copy(multipartFile.getBytes(), new File(newFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        category.setImage(fileName);
        categoryServiceImp.save(category);
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
            return new ModelAndView("/error.404");
        }
    }

    @PostMapping("/del-cate")
    public ModelAndView deleteCate(@ModelAttribute("category") Category category){
        categoryServiceImp.remove(category.getId());
        return new ModelAndView("redirect:/categories");
    }

}
