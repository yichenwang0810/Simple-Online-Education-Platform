package com.edu.service.impl;

import com.edu.entity.LessonProgress;
import com.edu.mapper.LessonProgressMapper;
import com.edu.service.LessonProgressService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LessonProgressServiceImpl extends ServiceImpl<LessonProgressMapper, LessonProgress>
        implements LessonProgressService {

    @Override
    public boolean updateProgress(Long studentId, Long lessonId, Integer progressPercentage, Integer lastWatchedTime) {
        QueryWrapper<LessonProgress> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("student_id", studentId).eq("lesson_id", lessonId);

        LessonProgress existing = this.getOne(queryWrapper);

        if (existing != null) {
            // Update existing progress
            UpdateWrapper<LessonProgress> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("student_id", studentId)
                        .eq("lesson_id", lessonId)
                        .set("progress_percentage", progressPercentage)
                        .set("last_watched_time", lastWatchedTime)
                        .set("completed", progressPercentage >= 100)
                        .set("gmt_modified", LocalDateTime.now());
            return this.update(updateWrapper);
        } else {
            // Create new progress record
            LessonProgress progress = new LessonProgress();
            progress.setStudentId(studentId);
            progress.setLessonId(lessonId);
            progress.setProgressPercentage(progressPercentage);
            progress.setLastWatchedTime(lastWatchedTime);
            progress.setCompleted(progressPercentage >= 100);
            progress.setGmtCreate(LocalDateTime.now());
            progress.setGmtModified(LocalDateTime.now());
            return this.save(progress);
        }
    }

    @Override
    public LessonProgress getProgress(Long studentId, Long lessonId) {
        QueryWrapper<LessonProgress> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("student_id", studentId).eq("lesson_id", lessonId);
        return this.getOne(queryWrapper);
    }

    @Override
    public boolean markCompleted(Long studentId, Long lessonId) {
        UpdateWrapper<LessonProgress> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("student_id", studentId)
                    .eq("lesson_id", lessonId)
                    .set("progress_percentage", 100)
                    .set("completed", true)
                    .set("gmt_modified", LocalDateTime.now());
        return this.update(updateWrapper);
    }
}