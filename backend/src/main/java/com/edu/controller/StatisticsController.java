package com.edu.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/statistics")
@CrossOrigin(origins = "*")
public class StatisticsController {

    // Get course analytics (e.g., view count, student count)
    @GetMapping("/course/{courseId}")
    public Map<String, Object> getCourseStatistics(@PathVariable Long courseId) {
        Map<String, Object> stats = new HashMap<>();
        stats.put("courseId", courseId);
        stats.put("totalViews", 1250); // This would come from a service
        stats.put("totalStudents", 85);
        stats.put("averageRating", 4.7);
        return stats;
    }
}