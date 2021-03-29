package com.example.demo.controller;

import com.example.demo.model.category.Category;
import com.example.demo.model.login.AppRole;
import com.example.demo.model.login.AppUser;
import com.example.demo.model.product.Product;
import com.example.demo.service.category.ICategoryService;
import com.example.demo.service.login.role.IAppRoleService;
import com.example.demo.service.login.user.IAppUserService;
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
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    IAppUserService appUserService;

    @Autowired
    IAppRoleService appRoleService;

    @Autowired
    ICategoryService categoryService;

    @Autowired
    IProductService productService;

    @ModelAttribute("listRole")
    public List<AppRole> appRoleList() {
        return appRoleService.findAll();
    }

    //// acount
    @GetMapping("")
    public ModelAndView showAll(@PageableDefault(size = 5) Pageable pageable) {
        return new ModelAndView("admin/account/list", "list", appUserService.findAll(pageable));
    }

    @GetMapping("/account/create")
    public ModelAndView showFormCreate() {
        return new ModelAndView("login/create", "user", new AppUser());
    }

    @PostMapping("/account/create")
    public ModelAndView createAccount(@ModelAttribute AppUser appUser) {
        appUserService.save(appUser);
        return new ModelAndView("redirect:/admin/");
    }

    @GetMapping("account/delete/{id}")
    public ModelAndView delete(@PathVariable Long id) {
        appUserService.remove(id);
        return new ModelAndView("redirect:/admin/");
    }

    @GetMapping("account/edit/{id}")
    public ModelAndView showFormEdit(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("admin/account/edit");
        AppUser user = appUserService.findById(id);
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @PostMapping("account/edit/{id}")
    public ModelAndView edit(@ModelAttribute AppUser appUser) {
        appUserService.save(appUser);
        return new ModelAndView("redirect:/admin/");
    }

    @PostMapping("account/search")
    public ModelAndView searchAccountByRole(@PageableDefault Pageable pageable, @RequestParam String username) {
        System.out.println(username);
        Page<AppUser> list = appUserService.findAllByUsername(username,pageable);
        return new ModelAndView("admin/account/list", "list", list);
    }

    ////category
    @GetMapping("/category")
    public ModelAndView showAllCategory() {
        return new ModelAndView("admin/category/list", "list", categoryService.findAll());
    }

    @GetMapping("/category/delete/{id}")
    public ModelAndView deleteCategory(@PathVariable Long id) {
        categoryService.remove(id);
        return new ModelAndView("redirect:/admin/category");
    }

    @GetMapping("/category/create")
    public ModelAndView showFormCreateCate() {
        return new ModelAndView("admin/category/create", "category", new Category());
    }

    @PostMapping("/category/create")
    public ModelAndView createCate(@ModelAttribute Category category) {
        categoryService.save(category);
        return new ModelAndView("redirect:/admin/category");
    }

    @GetMapping("/category/edit/{id}")
    public ModelAndView showFormEditCate(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("admin/category/edit");
        Category category = categoryService.findById(id);
        modelAndView.addObject("category", category);
        return modelAndView;
    }

    @PostMapping("/category/edit/{id}")
    public ModelAndView editCate(@ModelAttribute Category category) {
        categoryService.save(category);
        return new ModelAndView("redirect:/admin/category");
    }


    ///// Products
    @GetMapping("/product")
    public ModelAndView showAllProduct(@PageableDefault Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("admin/product/list");
        Page<Product> list = productService.findAll(pageable);
        modelAndView.addObject("list",list);
        return modelAndView;
    }


}
