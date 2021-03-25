package com.example.demo.controller;

import com.example.demo.model.login.AppRole;
import com.example.demo.model.login.AppUser;
import com.example.demo.service.login.role.IAppRoleService;
import com.example.demo.service.login.user.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    IAppRoleService appRoleService;

    @Autowired
    IAppUserService appUserService;

    @ModelAttribute("listRole")
    public List<AppRole> appRoleList() {
        return appRoleService.findAll();
    }

    @GetMapping
    public String login() {
        return "account/login";
    }

    @GetMapping("/register")
    public ModelAndView showFormCreate() {
        return new ModelAndView("account/create", "user",new AppUser());
    }

    @PostMapping("/register")
    public ModelAndView createAccount(@ModelAttribute AppUser appUser){
       appUserService.save(appUser);
        return new ModelAndView("redirect:/login");
    }


}
