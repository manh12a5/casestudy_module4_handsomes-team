package com.example.demo.repository;

import com.example.demo.model.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

    List<Product> findAllByNameContaining(String name);

    List<Product> findAllByCategoryId(Long id);

    //Sắp xếp
    Page<Product> findAllByOrderByPriceAsc(Pageable pageable);

    //Sắp xếp
    Page<Product> findAllByOrderByPriceDesc(Pageable pageable);

    //Top5 price max
    Page<Product> findTop5ByOrderByPriceDesc(Pageable pageable);

    //Top 5 Price Min
    Page<Product> findTop5ByOrderByPriceAsc(Pageable pageable);

}
