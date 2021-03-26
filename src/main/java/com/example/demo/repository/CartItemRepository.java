package com.example.demo.repository;

import com.example.demo.model.cart.CartItem;
import com.example.demo.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {
}
