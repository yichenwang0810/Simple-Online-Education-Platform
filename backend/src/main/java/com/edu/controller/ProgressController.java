package com.edu.controller;

import com.edu.entity.LessonProgress;
import com.edu.service.LessonProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/progress")
@CrossOrigin(origins = "*")
public class ProgressController {

    @Autowired
    private LessonProgressService progressService;

    /**
     * Update lesson progress
     */
    @PostMapping("/lesson")
    public Map<String, Object> updateProgress(@RequestBody Map<String, Object> request) {
        Long studentId = Long.valueOf(request.get("studentId").toString());
        Long lessonId = Long.valueOf(request.get("lessonId").toString());
        Integer progressPercentage = Integer.valueOf(request.get("progressPercentage").toString());
        Integer lastWatchedTime = Integer.valueOf(request.get("lastWatchedTime").toString());

        boolean success = progressService.updateProgress(studentId, lessonId, progressPercentage, lastWatchedTime);

        Map<String, Object> response = new java.util.HashMap<>();
        response.put("success", success);
        return response;
    }

    /**
     * Get lesson progress
     */
    @GetMapping("/lesson/{studentId}/{lessonId}")
    public LessonProgress getProgress(@PathVariable Long studentId, @PathVariable Long lessonId) {
        return progressService.getProgress(studentId, lessonId);
    }

    /**
     * Mark lesson as completed
     */
    @PostMapping("/lesson/{studentId}/{lessonId}/complete")
    public Map<String, Object> markCompleted(@PathVariable Long studentId, @PathVariable Long lessonId) {
        boolean success = progressService.markCompleted(studentId, lessonId);

        Map<String, Object> response = new java.util.HashMap<>();
        response.put("success", success);
        return response;
    }
}