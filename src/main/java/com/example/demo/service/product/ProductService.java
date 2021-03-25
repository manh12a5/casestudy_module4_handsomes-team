package com.example.demo.service.product;

import com.example.demo.model.cart.Cart;
import com.example.demo.model.cart.CartItem;
import com.example.demo.model.product.Product;
import com.example.demo.repository.IProductRepository;
import com.example.demo.service.cartItem.ICartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    IProductRepository productRepository;
    @Autowired
    private ICartItemService cartItemService;

    @Override
    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void remove(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Page<Product> findProductByName(String name, Pageable pageable) {
        return productRepository.findProductByName(name, pageable);
    }

    @Override
    public List<Product> findAllByCart(Cart cart) {
        Iterable<CartItem> cartItems = cartItemService.findAllByCart(cart);
        List<Product> productList = new ArrayList<>();
        for (CartItem item:cartItems
        ) {
            productList.add(item.getProduct());
        }
        return productList;
    }
}
