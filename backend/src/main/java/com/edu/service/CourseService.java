package com.edu.service;

import com.edu.entity.Course;

import java.util.List;

public interface CourseService {

    List<Course> listCourses(String category);

    Course saveCourse(Course course);

    Course getById(Long id);
}