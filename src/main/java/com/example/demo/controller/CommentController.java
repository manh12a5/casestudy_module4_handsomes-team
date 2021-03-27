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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
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

//    @GetMapping("/create")
//    public ModelAndView formCreate(){
//        ModelAndView modelAndView = new ModelAndView("/comment/create");
//        modelAndView.addObject("comment",new Comment());
//        return modelAndView;
//    }
//
//    @PostMapping("/create")
//    public ModelAndView createCate(@ModelAttribute Comment comment){
//        ModelAndView modelAndView = new ModelAndView("redirect:/comments");
//        commentService.save(comment);
//        modelAndView.addObject("comment",new Comment());
//        return modelAndView;
//    }

//    @GetMapping("/create")
//    public ModelAndView create(@ModelAttribute Comment comment){
//        commentService.save(comment);
//        ModelAndView modelAndView=new ModelAndView("redirect:/comments");
//        modelAndView.addObject("comment",new Comment());
//        return modelAndView;
//    }

//    @GetMapping ("/delete/{id}")
//    public ModelAndView delete(@PathVariable Long id) {
//        ModelAndView modelAndView = new ModelAndView("redirect:/comments");
//        commentService.remove(id);
//        return modelAndView;
//    }

//    @RequestMapping("/comment")
//    public ModelAndView getDetails(@PathVariable Long id) {
//        ModelAndView modelAndView = new ModelAndView("comment/create");
//        Product product = productService.findById(id);
//        Iterable<Comment> comments = commentService.findAllByProduct(product);
//        Integer cmtSum = commentService.countAllByProduct(product);
//        modelAndView.addObject("product",product);
//        modelAndView.addObject("cmtSum",cmtSum);
//        modelAndView.addObject("comments",comments);
//        return modelAndView;
//    }


//    @PostMapping("/comment/create")
//    public ResponseEntity<Comment> addComment(@RequestBody Comment comment){
//        String commentComment = comment.getComment();
//        Product product  = productService.findById(comment.getProduct().getId());
//        Comment comment1 = new Comment();
//        comment1.setComment(commentComment);
//        comment1.setProduct(product);
//        comment1.setUser(currentUser());
//        commentService.save(comment1);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @GetMapping("/comment/{id}")
//    public ResponseEntity<Iterable<Comment>> loadList(@PathVariable Long id) {
//        Product product = productService.findById(id);
//        Iterable<Comment> comments = commentService.findAllByProduct(product);
//        return new ResponseEntity<>(comments,HttpStatus.OK);
//    }
//
//    @GetMapping("/comment/sum/{id}")
//    public ResponseEntity getSum(@PathVariable Long id) {
//        Product product = productService.findById(id);
//        Integer cmtSum = commentService.countAllByProduct(product);
//        return new ResponseEntity(cmtSum,HttpStatus.OK);
//    }




//    @GetMapping("/create")
//    private ModelAndView showCreate() {
//        ModelAndView modelAndView = new ModelAndView("comment/create");
//        modelAndView.addObject("comment", new Comment());
//        return modelAndView;
//    }
//
//    @PostMapping("/create")
//    private ModelAndView create(@ModelAttribute Comment comment) {
//        ModelAndView modelAndView = new ModelAndView("comment/create");
//        commentService.save(comment);
//        modelAndView.addObject("comment", comment);
//        return modelAndView;
//    }


}
