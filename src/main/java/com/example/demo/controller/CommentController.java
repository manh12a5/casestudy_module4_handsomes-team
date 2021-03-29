package com.example.demo.controller;

import com.example.demo.model.category.Category;
import com.example.demo.model.comment.Comment;
import com.example.demo.model.login.AppUser;
import com.example.demo.model.product.Product;
import com.example.demo.service.comment.ICommentService;
import com.example.demo.service.login.user.IAppUserService;
import com.example.demo.service.product.IProductService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.util.Date;

@Controller
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    IProductService productService;

    @Autowired
    ICommentService commentService;

    @Autowired
    IAppUserService appUserService;

    @ModelAttribute()
    public AppUser currentUser(){

        return appUserService.getCurrentUser();
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Iterable<Comment> allComment() {
        return commentService.findAll();
    }

    @GetMapping("")
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView("comment/list");

        modelAndView.addObject("allComment", allComment());
        return modelAndView;
    }

    @GetMapping(value = "/create")
    public ModelAndView formCreate() {
        ModelAndView mav = new ModelAndView("comment/create");
        mav.addObject("comment", new Comment());
        return mav;
    }

    @PostMapping(value = "/createNewC",
            produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Comment create(@RequestBody Comment comment){
        AppUser appUser=currentUser();
        Date date=new Date(System.currentTimeMillis());
        comment.setUser(appUser);
        comment.setDate(date);
        return commentService.save(comment);
    }

    @GetMapping (value = "/edit/{id}")
    public ModelAndView formEdit(@PathVariable Long id){
        Comment comment = commentService.findById(id);
        if(comment != null) {
            ModelAndView modelAndView = new ModelAndView("comment/edit");
            modelAndView.addObject("comment",comment);
            return modelAndView;
        }
        return null;
    }

    @PostMapping(value = "/edit/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Comment edit(@PathVariable Long id, @RequestBody Comment comment){
        comment.setId(id);
        Product product=productService.findById(comment.getProduct().getId());
        AppUser appUser=appUserService.findById(comment.getUser().getId());
        Date date=new Date(System.currentTimeMillis());
        Comment comment1=new Comment();
        comment1.setProduct(product);
        comment1.setUser(appUser);
        comment1.setDate(date);
        return commentService.save(comment);
    }

    @PostMapping(value = "/delete/{id}",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void delete(@PathVariable Long id) {
        commentService.remove(id);
    }

}
