package com.example.demo.service.cart;

import com.example.demo.model.cart.CartItem;
import com.example.demo.model.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService implements ICartService {
    private List<CartItem>itemList;

    public CartService() {
        itemList=new ArrayList<CartItem>();
    }

    @Override
    public CartItem getItem(Product product) {
        for (CartItem item:itemList
             ) {
            if (item.getProduct().getId()== product.getId()){
                return item;
            }
        }
        return null;
    }

    @Override
    public List<CartItem> getItems() {
        return itemList;
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    @Override
    public void addItem(CartItem cartItem) {
        addItem(cartItem.getProduct(),cartItem.getQuantity());
    }

    private void addItem(Product product, int quantity) {
        CartItem cartItem=getItem(product);
        //add item khi tồn tại
        if (cartItem!=null){
            cartItem.setQuantity(cartItem.getQuantity()+quantity);
        }
    }

    @Override
    public void updateItem(Product product, int quantity) {
        CartItem item=getItem(product);
        if (item!=null){
            item.setQuantity(quantity);
        }
    }

    @Override
    public void remoteItem(Product product) {
        CartItem item=getItem(product);
        if (item!=null){
            itemList.remove(item);
        }
    }

    @Override
    public void remoteCart() {
        itemList.clear();
    }

    @Override
    public boolean isEmpty() {
        return itemList.isEmpty();
    }
}
