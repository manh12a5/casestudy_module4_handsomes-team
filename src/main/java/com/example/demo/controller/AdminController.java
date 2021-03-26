package com.example.demo.controller;

import com.example.demo.model.login.AppRole;
import com.example.demo.model.login.AppUser;
import com.example.demo.service.login.role.IAppRoleService;
import com.example.demo.service.login.user.IAppUserService;
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

    @ModelAttribute("listRole")
    public List<AppRole> appRoleList() {
        return appRoleService.findAll();
    }

    @GetMapping("/account")
    public ModelAndView showAll(@PageableDefault(size = 5) Pageable pageable) {
        return new ModelAndView("account/list", "list", appUserService.findAll(pageable));
    }

    @GetMapping("/account/create")
    public ModelAndView showFormCreate() {
        return new ModelAndView("account/create", "account", new AppUser());
    }

    @PostMapping("/account/create")
    public ModelAndView createAccount(@ModelAttribute AppUser appUser) {
        appUserService.save(appUser);
        return new ModelAndView("redirect:/admin/account");
    }

    @GetMapping("account/delete/{id}")
    public ModelAndView delete(@PathVariable Long id) {
        appUserService.remove(id);
        return new ModelAndView("redirect:/admin/account");
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
        return new ModelAndView("redirect:/admin/account");
    }

    @GetMapping("/search")
    public ModelAndView searchAccountByRole(@ModelAttribute AppRole role) {
        List<AppUser> list = appUserService.findAllByAppRole(role);
        System.out.println(list.size());
        return new ModelAndView("/account/test", "list", list);
    }


}
