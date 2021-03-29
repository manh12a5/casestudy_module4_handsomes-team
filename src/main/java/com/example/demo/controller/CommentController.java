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

//    @GetMapping("")
//    public ModelAndView showAll(){
//        ModelAndView modelAndView=new ModelAndView("comment/list");
//        modelAndView.addObject("comment",commentService.findAll());
//        return modelAndView;
//    }

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

//    @RequestMapping("/comments")
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


//    @PostMapping("/create")
//    public ResponseEntity<Comment> addComment(@RequestBody Comment comment){
//        String commentComment = comment.getComment();
//        Product product  = productService.findById(comment.getProduct().getId());
//        Date date=new Date();
//        Timestamp timestamp=new Timestamp(date.getTime());
//        Comment comment1 = new Comment();
//        comment1.setComment(commentComment);
//        comment1.setProduct(product);
//        comment1.setUser(currentUser());
//        comment1.setDate(timestamp);
//        commentService.save(comment1);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//    @PostMapping("/create")
//    public ModelAndView addComment(@ModelAttribute Comment comment){
//        ModelAndView modelAndView = new ModelAndView("comment/create");
//        String commentComment = comment.getComment();
//        Product product  = productService.findById(comment.getProduct().getId());
//        Date date=new Date();
//        Timestamp timestamp=new Timestamp(date.getTime());
//        System.out.println(timestamp);
//        Comment comment1 = new Comment();
//        comment1.setComment(commentComment);
//        comment1.setProduct(product);
//        comment1.setUser(currentUser());
//        comment1.setDate(timestamp);
//        commentService.save(comment1);
//        modelAndView.addObject("comment", comment1);
//        return modelAndView;
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
//
//
//
//
//    @GetMapping("/create")
//    private ModelAndView showCreate() {
//        ModelAndView modelAndView = new ModelAndView("comment/create");
//        modelAndView.addObject("comment", new Comment());
//        return modelAndView;
//    }

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
