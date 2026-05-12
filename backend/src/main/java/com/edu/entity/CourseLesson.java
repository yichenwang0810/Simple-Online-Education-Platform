package com.edu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("course_lesson")
public class CourseLesson {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private Long chapterId;
    private String title;
    private String videoUrl; // URL to the video file
    private Integer sort;
    private LocalDateTime gmtCreate;
}