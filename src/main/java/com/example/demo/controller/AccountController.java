package com.example.demo.controller;

import com.example.demo.model.login.AppUser;
import com.example.demo.service.login.IAppUserService;
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
    public ModelAndView showAll() {
        return new ModelAndView("account/list", "list", appUserService.findAll());
    }

    @GetMapping("/create")
    public ModelAndView showFormCreate(){
        return new ModelAndView("account/create","account",new AppUser());
    }
    @PostMapping("/create")
    public ModelAndView createAccount(@ModelAttribute AppUser appUser){
        appUserService.save(appUser);
        return new ModelAndView("redirect:/account");
    }
    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable Long id){
        appUserService.remove(id);
        return new ModelAndView("redirect:/account");
    }


}
