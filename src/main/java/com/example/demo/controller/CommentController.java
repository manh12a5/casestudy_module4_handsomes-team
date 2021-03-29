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
    @ModelAttribute()
    public Product currentProduct(){

        return productService.getCurrentProduct();
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
        Product product=currentProduct();
        Date date=new Date(System.currentTimeMillis());
        comment.setUser(appUser);
        comment.setProduct(product);
        System.out.println("id"+product);
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
    @ResponseBody
    @PostMapping(value = "/edit/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)

    public Comment edit(@PathVariable Long id, @RequestBody Comment comment){
        comment.setId(id);
        AppUser appUser=currentUser();
        Product product=currentProduct();
        Date date=new Date(System.currentTimeMillis());
        if (appUser.equals(appUser.getUsername())&&product.equals(product.getName())){
            comment.setUser(appUser);
            comment.setProduct(product);
            System.out.println(product);
            comment.setDate(date);
            return commentService.save(comment);
        }else {
            return null;
        }

    }

    @PostMapping(value = "/delete/{id}",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Comment> delete(@PathVariable Long id) {
            AppUser appUser=currentUser();
        String a = commentService.findById(id).getUser().getUsername();
        boolean b = appUser.getUsername().equals(a);
        if (b){
            commentService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }

    }

}
