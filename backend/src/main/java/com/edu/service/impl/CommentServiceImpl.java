package com.edu.service.impl;

import com.edu.entity.Comment;
import com.edu.mapper.CommentMapper;
import com.edu.service.CommentService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
        implements CommentService {

    @Override
    public List<Comment> getCommentsByCourseId(Long courseId) {
        return baseMapper.selectCommentsWithUserName(courseId);
    }

    @Override
    public boolean addComment(Comment comment) {
        comment.setGmtCreate(LocalDateTime.now());
        return this.save(comment);
    }

    @Override
    public Double getAverageRating(Long courseId) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId).select("AVG(rating) as avgRating");
        // This is a simplified implementation. In a real scenario, you'd use a custom SQL query
        List<Comment> comments = this.list(queryWrapper);
        if (comments.isEmpty()) {
            return 0.0;
        }
        // For simplicity, calculate average manually
        double sum = 0;
        for (Comment comment : comments) {
            sum += comment.getRating();
        }
        return sum / comments.size();
    }
}