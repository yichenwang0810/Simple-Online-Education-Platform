package com.edu.service;

import com.edu.entity.CourseChapter;
import com.edu.entity.CourseLesson;

import java.util.List;

public interface CourseContentService {

    List<CourseChapter> getChaptersByCourseId(Long courseId);

    List<CourseLesson> getLessonsByChapterId(Long chapterId);

    CourseChapter addChapter(CourseChapter chapter);

    CourseLesson addLesson(CourseLesson lesson);
}
