package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DefautController {
    @RequestMapping("home")
    public ModelAndView index(){
        return new ModelAndView("shop/index");
    }

    @RequestMapping("about")
    public ModelAndView about(){
        return new ModelAndView("shop/about");
    }
}
