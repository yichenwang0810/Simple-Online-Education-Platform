package com.edu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("user_profile")
public class UserProfile {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long userId;
    private String bio;
    private String avatarUrl;
    private String website;
    private String location;

    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;
}