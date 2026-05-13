package com.edu.controller;

import com.edu.entity.Comment;
import com.edu.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/comments")
@CrossOrigin(origins = "*")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * Get comments for a course
     */
    @GetMapping("/course/{courseId}")
    public List<Comment> getComments(@PathVariable Long courseId) {
        return commentService.getCommentsByCourseId(courseId);
    }

    /**
     * Add a comment to a course
     */
    @PostMapping
    public Map<String, Object> addComment(@RequestBody Comment comment) {
        boolean success = commentService.addComment(comment);

        Map<String, Object> response = new java.util.HashMap<>();
        response.put("success", success);
        return response;
    }

    /**
     * Get average rating for a course
     */
    @GetMapping("/course/{courseId}/rating")
    public Map<String, Object> getAverageRating(@PathVariable Long courseId) {
        Double averageRating = commentService.getAverageRating(courseId);

        Map<String, Object> response = new java.util.HashMap<>();
        response.put("averageRating", averageRating);
        return response;
    }
}