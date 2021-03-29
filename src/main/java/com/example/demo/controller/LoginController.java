package com.example.demo.controller;

import com.example.demo.model.cart.Cart;
import com.example.demo.model.login.AppRole;
import com.example.demo.model.login.AppUser;
import com.example.demo.service.Cart.ICartService;
import com.example.demo.service.login.role.IAppRoleService;
import com.example.demo.service.login.user.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    ICartService cartService;

    @ModelAttribute("listRole")
    public List<AppRole> appRoleList() {
        return appRoleService.findAll();
    }

    @GetMapping
    public String login() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return "login/login";
        }
        return "redirect:/";
    }

    @GetMapping("/register")
    public ModelAndView showFormCreate() {
        return new ModelAndView("login/create", "user", new AppUser());
    }


    // tạo mới user hàng với giỏ
    @PostMapping("/register")
    public ModelAndView createAccount(@ModelAttribute AppUser appUser) {
        // khi gọi phương thức thì tạo ta 1 giỏ
        Cart cart = new Cart();
        // lưu cart vào trong database
        Cart insertCart = cartService.save(cart);
        // user lưu thông tin cart đã có trong database vào thuộc tính user
        appUser.setCart(insertCart);
        // lưu user vào database
        AppUser inSertAppUser = appUserService.save(appUser);
        // bổ sung thông tin user đã có trong databse vào thuộc tính cart
        insertCart.setAppUser(inSertAppUser);
        // cập nhật cart lên database
        cartService.save(insertCart);
        return new ModelAndView("redirect:/login");
    }

}
