package com.example.demo.service.comment;

import com.example.demo.model.comment.Comment;
import com.example.demo.model.product.Product;
import com.example.demo.service.IService;

public interface ICommentService extends IService<Comment> {
    Iterable<Comment>findAllByProduct(Product product);
    Integer countAllByProduct(Product product);
}
