package com.edu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.edu.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

    @Select("SELECT c.*, u.nickname as userName FROM comment c LEFT JOIN user u ON c.user_id = u.id WHERE c.course_id = #{courseId} ORDER BY c.gmt_create DESC")
    List<Comment> selectCommentsWithUserName(Long courseId);
}