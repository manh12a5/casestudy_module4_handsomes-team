package com.example.demo.controller;

import com.example.demo.model.comment.Comment;
import com.example.demo.model.login.AppUser;
import com.example.demo.model.product.Product;
import com.example.demo.service.comment.ICommentService;
import com.example.demo.service.login.user.IAppUserService;
import com.example.demo.service.product.IProductService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/comment")
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

    @GetMapping("")
    public ModelAndView showAll(){
        ModelAndView modelAndView=new ModelAndView("comment/list");
        modelAndView.addObject("comment",commentService.findAll());
        return modelAndView;
    }
    @PostMapping("/comment/create")
    public ResponseEntity<Comment> addComment(@RequestBody Comment comment) throws NotFoundException {
        String commentCommentt = comment.getComment();
        Product product  = productService.findById(comment.getProduct().getId());
        Comment comment1 = new Comment();
        comment1.setComment(commentCommentt);
        comment1.setProduct(product);
        comment1.setUser(currentUser());
        commentService.save(comment1);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/comment/{id}")
    public ResponseEntity<Iterable<Comment>> loadList(@PathVariable Long id) throws NotFoundException {
        Product product = productService.findById(id);
        Iterable<Comment> comments = commentService.findAllByProduct(product);
        return new ResponseEntity<>(comments,HttpStatus.OK);
    }

    @GetMapping("/comment/sum/{id}")
    public ResponseEntity getSum(@PathVariable Long id)throws NotFoundException {
        Product product = productService.findById(id);
        Integer cmtSum = commentService.countAllByProduct(product);
        return new ResponseEntity(cmtSum,HttpStatus.OK);
    }
}
