package com.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.edu.entity.Course;
import com.edu.mapper.CourseMapper;
import com.edu.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Course> listCourses(String category) {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", "NORMAL"); // Only show published courses
        
        // Simple filtering logic
        if (category != null && !category.isEmpty()) {
            queryWrapper.eq("subject_id", category); 
        }
        
        queryWrapper.orderByDesc("gmt_create"); // Newest first
        return courseMapper.selectList(queryWrapper);
    }

    @Override
    public Course saveCourse(Course course) {
        // In a real app, you would validate data here
        course.setStatus("NORMAL"); // Auto-publish for demo
        course.setViewCount(0);
        courseMapper.insert(course);
        return course;
    }

    @Override
    public Course getById(Long id) {
        // Increment view count logic could go here
        return courseMapper.selectById(id);
    }
}