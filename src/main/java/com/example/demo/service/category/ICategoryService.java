package com.example.demo.service.category;

import com.example.demo.model.category.Category;
import com.example.demo.model.product.Product;
import com.example.demo.service.IService;

import java.util.List;

public interface ICategoryService extends IService<Category> {

    List<Product> findCategoriesByName(String name);

}
