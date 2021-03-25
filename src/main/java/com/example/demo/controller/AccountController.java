package com.example.demo.controller;

import com.example.demo.model.login.AppUser;
import com.example.demo.service.login.user.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    IAppUserService appUserService;

    @GetMapping()
    public ModelAndView showAll(@PageableDefault(size = 5) Pageable pageable) {
        return new ModelAndView("account/list", "list", appUserService.findAll(pageable));
    }

    @GetMapping("/create")
    public ModelAndView showFormCreate() {
        return new ModelAndView("account/create", "account", new AppUser());
    }

    @PostMapping("/create")
    public ModelAndView createAccount(@ModelAttribute AppUser appUser) {
        appUserService.save(appUser);
        return new ModelAndView("redirect:/account");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable Long id) {
        appUserService.remove(id);
        return new ModelAndView("redirect:/account");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showFormEdit(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("account/edit");
        AppUser user = appUserService.findById(id);
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView edit(@ModelAttribute AppUser appUser) {
        appUserService.save(appUser);
        return new ModelAndView("redirect:/account");
    }


}
