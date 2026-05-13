package com.edu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("course_student")
public class CourseStudent {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private Long courseId;
    private Long studentId;
    private String status;
    private LocalDateTime gmtCreate;
}