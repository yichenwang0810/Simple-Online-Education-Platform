package com.edu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.edu.entity.CourseLesson;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CourseLessonMapper extends BaseMapper<CourseLesson> {
}