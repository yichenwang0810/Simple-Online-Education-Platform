package com.edu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("user")
public class User {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String username;
    private String password; // This should be hashed
    private String nickname;
    private String role; // STUDENT, TEACHER, ADMIN
    private LocalDateTime gmtCreate;
}