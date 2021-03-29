package com.example.demo.service.Cart;


import com.example.demo.model.cart.CartItem;
import com.example.demo.model.product.Product;
import com.example.demo.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemServiceImp implements ICartItemService {
    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public CartItem save(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    @Override
    public List<CartItem> findAll() {
        return cartItemRepository.findAll();
    }

    @Override
    public CartItem findById(Long id) {
        return cartItemRepository.getOne(id);
    }

    @Override
    public void remove(Long id) {
        cartItemRepository.deleteById(id);
    }

    @Override
    public void createCartItem(CartItem cartItem) {
        List<CartItem> cartItemList = this.findAll();
        boolean check = false;
        int index = 0;
        int quantity = 0;
        if (cartItemList.isEmpty()) {
            this.save(cartItem);
        } else {
            for (int i = 0; i < cartItemList.size(); i++) {
                if
                ((cartItemList.get(i).getProduct().getId() == cartItem.getProduct().getId()) && (cartItemList.get(i).getSize() == cartItem.getSize())) {
                    int quantity_before = cartItemList.get(i).getQuantity();
                    int quantity_after = cartItem.getQuantity();
                    int quantity_present = quantity_before + quantity_after;
                    index = i;
                    quantity = quantity_present;
                    check = true;
                    break;
                }
            }

            if (check == false) {
                this.save(cartItem);
            } else {
                CartItem cartItem1 = cartItemList.get(index);
                cartItem1.setQuantity(quantity);
                this.save(cartItem1);
            }
        }
    }

    @Override
    public List<CartItem> findAllByCartId(Long id) {
        return cartItemRepository.findAllByCartId(id);
    }

    @Override
    public List<CartItem> findAllByStatus(int stt) {
        return cartItemRepository.findAllByStatus(stt);
    }

    @Override
    public List<CartItem> findAllByCartIdAndStatus(Long id, int stt) {
        return cartItemRepository.findAllByCartIdAndStatus(id,stt);
    }
    @Override
    public List<CartItem> findCartItemsByStatus(Integer stt1, Integer stt2, Long cartId){
        return cartItemRepository.findCartItemsByStatus(stt1,stt2,cartId);
    }
}
