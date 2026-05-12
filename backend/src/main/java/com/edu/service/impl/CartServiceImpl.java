package com.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.edu.entity.CartItem;
import com.edu.mapper.CartItemMapper;
import com.edu.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartItemMapper cartItemMapper;

    @Override
    public void addItem(CartItem item) {
        // Optional: Check if item already exists in cart to update quantity instead
        QueryWrapper<CartItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", item.getUserId())
                    .eq("course_id", item.getCourseId());
        
        CartItem existingItem = cartItemMapper.selectOne(queryWrapper);
        
        if (existingItem != null) {
            // Item exists, maybe update quantity or just do nothing
            // For this demo, we assume one course per user
            throw new RuntimeException("Course already in cart");
        } else {
            // Add new item
            cartItemMapper.insert(item);
        }
    }

    @Override
    public List<CartItem> getItemsByUserId(Long userId) {
        QueryWrapper<CartItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        return cartItemMapper.selectList(queryWrapper);
    }

    @Override
    public void removeItem(Long itemId) {
        cartItemMapper.deleteById(itemId);
    }
}