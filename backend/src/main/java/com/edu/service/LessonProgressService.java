package com.edu.service;

import com.edu.entity.LessonProgress;
import com.baomidou.mybatisplus.extension.service.IService;

public interface LessonProgressService extends IService<LessonProgress> {

    /**
     * Update lesson progress for a student
     */
    boolean updateProgress(Long studentId, Long lessonId, Integer progressPercentage, Integer lastWatchedTime);

    /**
     * Get lesson progress for a student
     */
    LessonProgress getProgress(Long studentId, Long lessonId);

    /**
     * Mark lesson as completed
     */
    boolean markCompleted(Long studentId, Long lessonId);
}