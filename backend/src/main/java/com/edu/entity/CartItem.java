package com.edu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("cart_item")
public class CartItem {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private Long userId;
    private Long courseId;
    private String courseTitle;
    private BigDecimal price;
    private Integer quantity;
    private LocalDateTime gmtCreate;
}