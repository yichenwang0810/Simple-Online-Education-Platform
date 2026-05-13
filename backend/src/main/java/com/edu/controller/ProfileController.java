package com.edu.controller;

import com.edu.entity.UserProfile;
import com.edu.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
@CrossOrigin(origins = "*")
public class ProfileController {

    @Autowired
    private UserProfileService profileService;

    /**
     * Get user profile
     */
    @GetMapping("/{userId}")
    public UserProfile getProfile(@PathVariable Long userId) {
        return profileService.getByUserId(userId);
    }

    /**
     * Update user profile
     */
    @PostMapping
    public UserProfile updateProfile(@RequestBody UserProfile profile) {
        profileService.saveOrUpdateProfile(profile);
        return profile;
    }
}