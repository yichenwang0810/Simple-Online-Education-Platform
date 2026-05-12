package com.edu.controller;

import com.edu.entity.CourseChapter;
import com.edu.entity.CourseLesson;
import com.edu.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/content")
@CrossOrigin(origins = "*")
public class CourseContentController {

    @Autowired
    private CourseContentService contentService;

    // Get all chapters for a course
    @GetMapping("/chapters/{courseId}")
    public List<CourseChapter> getChaptersByCourseId(@PathVariable Long courseId) {
        return contentService.getChaptersByCourseId(courseId);
    }

    // Get all lessons for a chapter
    @GetMapping("/lessons/chapter/{chapterId}")
    public List<CourseLesson> getLessonsByChapterId(@PathVariable Long chapterId) {
        return contentService.getLessonsByChapterId(chapterId);
    }

    // Add a new chapter (Teacher only)
    @PostMapping("/chapter")
    public CourseChapter addChapter(@RequestBody CourseChapter chapter) {
        return contentService.addChapter(chapter);
    }

    // Add a new lesson (Teacher only)
    @PostMapping("/lesson")
    public CourseLesson addLesson(@RequestBody CourseLesson lesson) {
        return contentService.addLesson(lesson);
    }
}