package com.edu.service;

import com.edu.entity.CourseStudent;

import java.util.List;
import java.util.Map;

public interface EnrollmentService {

    void enroll(Long studentId, Long courseId);

    List<CourseStudent> getMyCourses(Long studentId);

    /**
     * Get enrolled courses with progress information
     */
    List<Map<String, Object>> getMyCoursesWithProgress(Long studentId);
}