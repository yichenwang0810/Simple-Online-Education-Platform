package com.edu.controller;

import com.edu.entity.CartItem;
import com.edu.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "*")
public class CartController {

    @Autowired
    private CartService cartService;

    // Add course to cart
    @PostMapping("/add")
    public String addToCart(@RequestBody CartItem item) {
        cartService.addItem(item);
        return "Added to cart";
    }

    // Get items in cart for current user
    @GetMapping("/items")
    public List<CartItem> getCartItems(@RequestParam Long userId) {
        return cartService.getItemsByUserId(userId);
    }

    // Remove item from cart
    @DeleteMapping("/remove/{itemId}")
    public String removeFromCart(@PathVariable Long itemId) {
        cartService.removeItem(itemId);
        return "Removed from cart";
    }
}