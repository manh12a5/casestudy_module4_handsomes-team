package com.example.demo.repository;

import com.example.demo.model.category.Category;
import com.example.demo.model.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {

    //Tìm kiếm sản phẩm theo tên
    @Query(value = "select * from category where category.name like ?", nativeQuery = true)
    List<Product> findCategoriesByName(String name);

}
