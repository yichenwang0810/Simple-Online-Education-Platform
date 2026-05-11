package com.edu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.edu.entity.Course;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CourseMapper extends BaseMapper<Course> {
    // BaseMapper provides standard CRUD operations (insert, delete, update, select)
    // Custom SQL can be added here if complex queries are needed
}