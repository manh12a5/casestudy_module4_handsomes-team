package com.example.demo.repository;

import com.example.demo.model.comment.Comment;
import com.example.demo.model.product.Product;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CommentRepository extends PagingAndSortingRepository<Comment,Long> {
    Iterable<Comment>findAllByProductOrderByIdDesc(Product product);
    Integer countAllByProduct(Product product);
}
