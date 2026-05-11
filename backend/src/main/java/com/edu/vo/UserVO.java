package com.edu.vo;

import lombok.Data;

@Data
public class UserVO {
    private Long id;
    private String username;
    private String nickname;
    private String role;
    
    // Constructor to map Entity to VO
    public UserVO(com.edu.entity.User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.nickname = user.getNickname();
        this.role = user.getRole();
    }
}