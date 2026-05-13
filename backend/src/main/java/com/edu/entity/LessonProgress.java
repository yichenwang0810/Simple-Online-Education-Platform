package com.edu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("lesson_progress")
public class LessonProgress {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long studentId;
    private Long lessonId;
    private Integer progressPercentage; // 0-100
    private Boolean completed;
    private Integer lastWatchedTime; // seconds

    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;
}