package com.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.edu.entity.CourseStudent;
import com.edu.mapper.CourseStudentMapper;
import com.edu.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    @Autowired
    private CourseStudentMapper courseStudentMapper;

    @Override
    public void enroll(Long studentId, Long courseId) {
        // Check if already enrolled
        QueryWrapper<CourseStudent> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("student_id", studentId).eq("course_id", courseId);

        if (courseStudentMapper.selectCount(queryWrapper) == 0) {
            CourseStudent cs = new CourseStudent();
            cs.setStudentId(studentId);
            cs.setCourseId(courseId);
            cs.setStatus("ENROLLED");
            courseStudentMapper.insert(cs);
        }
    }

    @Override
    public List<CourseStudent> getMyCourses(Long studentId) {
        QueryWrapper<CourseStudent> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("student_id", studentId);
        return courseStudentMapper.selectList(queryWrapper);
    }

    @Override
    public List<Map<String, Object>> getMyCoursesWithProgress(Long studentId) {
        // This is a simplified implementation. In a real app, you'd use JOIN queries
        List<CourseStudent> enrollments = getMyCourses(studentId);
        List<Map<String, Object>> result = new ArrayList<>();

        for (CourseStudent enrollment : enrollments) {
            Map<String, Object> courseData = new HashMap<>();
            courseData.put("enrollment", enrollment);
            // In a real implementation, you'd fetch course details and calculate progress
            // For now, return mock progress data
            courseData.put("progress", 45); // Mock progress
            result.add(courseData);
        }

        return result;
    }
}