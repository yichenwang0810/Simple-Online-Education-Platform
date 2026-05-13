package com.edu.controller;

import com.edu.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/enrollment")
@CrossOrigin(origins = "*")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    /**
     * Enroll in a course
     */
    @PostMapping
    public Map<String, Object> enroll(@RequestBody Map<String, Object> request) {
        Long studentId = Long.valueOf(request.get("studentId").toString());
        Long courseId = Long.valueOf(request.get("courseId").toString());

        enrollmentService.enroll(studentId, courseId);

        Map<String, Object> response = new java.util.HashMap<>();
        response.put("success", true);
        return response;
    }

    /**
     * Get my enrolled courses with progress
     */
    @GetMapping("/my-courses/{studentId}")
    public List<Map<String, Object>> getMyCourses(@PathVariable Long studentId) {
        return enrollmentService.getMyCoursesWithProgress(studentId);
    }
}