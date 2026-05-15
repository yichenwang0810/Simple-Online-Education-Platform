package com.edu.service;

import com.edu.entity.CartItem;

import java.util.List;

public interface CartService {

    void addItem(CartItem item);

    List<CartItem> getItemsByUserId(Long userId);

    void removeItem(Long itemId);
}
