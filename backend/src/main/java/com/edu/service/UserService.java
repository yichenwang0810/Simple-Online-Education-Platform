package com.edu.service;

import com.edu.entity.User;

public interface UserService {

    User getByUsername(String username);

    User register(User user);
}
