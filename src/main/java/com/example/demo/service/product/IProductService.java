package com.example.demo.service.product;

import com.example.demo.model.product.Product;
import com.example.demo.service.IService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService extends IService<Product> {

    Page<Product> findAll(Pageable pageable);

    List<Product> findAllByName(String name);

    List<Product> findAllByCategoryId(Long id);

    Page<Product> findTop5ByOrderByPriceDesc(Pageable pageable);

    Page<Product> findTop5ByOrderByPriceAsc(Pageable pageable);

    //Sắp xếp
    Page<Product> findAllByOrderByPriceAsc(Pageable pageable);

    //Sắp xếp
    Page<Product> findAllByOrderByPriceDesc(Pageable pageable);

}
