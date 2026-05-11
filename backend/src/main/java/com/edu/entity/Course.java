package com.edu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("course") // Maps to the 'course' table in DB
public class Course implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long teacherId;
    private Long subjectId;
    private String title;
    private BigDecimal price;
    private String cover; // URL to the cover image
    private String status;
    private Integer viewCount;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;
}