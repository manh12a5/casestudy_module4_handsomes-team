package com.example.demo.service.product;

import com.example.demo.model.cart.Cart;
import com.example.demo.model.product.Product;
import com.example.demo.service.IService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService extends IService<Product> {

    Page<Product> findAll(Pageable pageable);

    Page<Product> findProductByName(String name, Pageable pageable);


}
