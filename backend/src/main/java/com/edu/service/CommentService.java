package com.edu.service;

import com.edu.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface CommentService extends IService<Comment> {

    /**
     * Get comments for a course with user names
     */
    List<Comment> getCommentsByCourseId(Long courseId);

    /**
     * Add a comment to a course
     */
    boolean addComment(Comment comment);

    /**
     * Get average rating for a course
     */
    Double getAverageRating(Long courseId);
}