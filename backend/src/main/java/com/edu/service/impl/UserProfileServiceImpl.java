package com.edu.service.impl;

import com.edu.entity.UserProfile;
import com.edu.mapper.UserProfileMapper;
import com.edu.service.UserProfileService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserProfileServiceImpl extends ServiceImpl<UserProfileMapper, UserProfile>
        implements UserProfileService {

    @Override
    public UserProfile getByUserId(Long userId) {
        QueryWrapper<UserProfile> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        return this.getOne(queryWrapper);
    }

    @Override
    public boolean saveOrUpdateProfile(UserProfile profile) {
        if (profile.getId() != null) {
            profile.setGmtModified(LocalDateTime.now());
            return this.updateById(profile);
        } else {
            profile.setGmtCreate(LocalDateTime.now());
            profile.setGmtModified(LocalDateTime.now());
            return this.save(profile);
        }
    }
}