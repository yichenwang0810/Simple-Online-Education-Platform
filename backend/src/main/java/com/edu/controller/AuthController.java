package com.edu.controller;

import com.edu.entity.User;
import com.edu.service.UserService; // Assume this service exists
import com.edu.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody User loginRequest) {
        Map<String, Object> response = new HashMap<>();
        
        // 1. Find user by username
        User user = userService.getByUsername(loginRequest.getUsername());
        
        if (user == null) {
            response.put("success", false);
            response.put("message", "User not found");
            return response;
        }

        // 2. Check password
        if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            response.put("success", true);
            response.put("token", "mock-jwt-token-" + user.getId()); // In real app, generate JWT
            response.put("user", new UserVO(user));
        } else {
            response.put("success", false);
            response.put("message", "Incorrect password");
        }
        
        return response;
    }
}