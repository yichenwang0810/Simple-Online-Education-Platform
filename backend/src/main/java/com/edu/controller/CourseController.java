package com.edu.controller;

import com.edu.entity.Course;
import com.edu.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@CrossOrigin(origins = "*") // Allow frontend access
public class CourseController {

    @Autowired
    private CourseService courseService;

    // Get all courses with filtering
    @GetMapping
    public List<Course> getAllCourses(@RequestParam(required = false) String category) {
        return courseService.listCourses(category);
    }

    // Get specific course details
    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable Long id) {
        return courseService.getById(id);
    }

    // Create a new course (Instructor only)
    @PostMapping
    public Course createCourse(@RequestBody Course course) {
        return courseService.saveCourse(course);
    }
}