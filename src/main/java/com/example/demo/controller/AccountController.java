package com.example.demo.controller;

import com.example.demo.service.login.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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


}
