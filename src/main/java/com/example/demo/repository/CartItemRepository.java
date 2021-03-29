package com.example.demo.repository;

import com.example.demo.model.cart.CartItem;
import com.example.demo.model.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {
        List<CartItem> findAllByCartId(Long id);
        List<CartItem> findAllByCartIdAndStatus(Long id,int stt);
        List<CartItem> findAllByStatus(int stt);

        @Query(value = "select * from cart_item as c where( c.status = ?1 or c.status = ?2 ) and c.cart_id = ?3", nativeQuery = true)
        List<CartItem> findCartItemsByStatus(Integer stt1, Integer stt2, Long cartId);
}
