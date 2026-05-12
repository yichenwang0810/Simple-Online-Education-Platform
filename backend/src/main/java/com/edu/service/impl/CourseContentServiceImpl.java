package com.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.edu.entity.CourseChapter;
import com.edu.entity.CourseLesson;
import com.edu.mapper.CourseChapterMapper;
import com.edu.mapper.CourseLessonMapper;
import com.edu.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseContentServiceImpl implements CourseContentService {

    @Autowired
    private CourseChapterMapper chapterMapper;

    @Autowired
    private CourseLessonMapper lessonMapper;

    @Override
    public List<CourseChapter> getChaptersByCourseId(Long courseId) {
        QueryWrapper<CourseChapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId)
                    .orderByAsc("sort"); // Order by sort number
        return chapterMapper.selectList(queryWrapper);
    }

    @Override
    public List<CourseLesson> getLessonsByChapterId(Long chapterId) {
        QueryWrapper<CourseLesson> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("chapter_id", chapterId)
                    .orderByAsc("sort");
        return lessonMapper.selectList(queryWrapper);
    }

    @Override
    public CourseChapter addChapter(CourseChapter chapter) {
        chapterMapper.insert(chapter);
        return chapter;
    }

    @Override
    public CourseLesson addLesson(CourseLesson lesson) {
        lessonMapper.insert(lesson);
        return lesson;
    }
}