package com.edu.service;

import com.edu.entity.UserProfile;
import com.baomidou.mybatisplus.extension.service.IService;

public interface UserProfileService extends IService<UserProfile> {

    /**
     * Get user profile by user ID
     */
    UserProfile getByUserId(Long userId);

    /**
     * Update or create user profile
     */
    boolean saveOrUpdateProfile(UserProfile profile);
}