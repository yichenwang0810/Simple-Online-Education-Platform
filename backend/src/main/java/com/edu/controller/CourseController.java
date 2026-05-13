package com.edu.controller;

import com.edu.entity.Course;
import com.edu.service.CourseService;
import com.edu.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/courses")
@CrossOrigin(origins = "*") // Allow frontend access
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private CommentService commentService;

    // Get all courses with filtering
    @GetMapping
    public List<Course> getAllCourses(@RequestParam(required = false) String category) {
        return courseService.listCourses(category);
    }

    // Get specific course details with rating
    @GetMapping("/{id}")
    public Map<String, Object> getCourseById(@PathVariable Long id) {
        Course course = courseService.getById(id);
        Double averageRating = commentService.getAverageRating(id);

        Map<String, Object> response = new HashMap<>();
        response.put("course", course);
        response.put("averageRating", averageRating);
        return response;
    }

    // Create a new course (Instructor only)
    @PostMapping
    public Course createCourse(@RequestBody Course course) {
        return courseService.saveCourse(course);
    }
}