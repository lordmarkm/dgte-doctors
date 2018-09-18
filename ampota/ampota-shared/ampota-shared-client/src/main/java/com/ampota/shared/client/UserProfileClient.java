package com.ampota.shared.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ampota.shared.dto.UserProfileInfo;

@FeignClient("user")
public interface UserProfileClient {

    @GetMapping("/api/user-profile")
    ResponseEntity<UserProfileInfo> getUserProfile();

    @PostMapping("/api/user-profile/register")
    ResponseEntity<UserProfileInfo> register(@RequestBody UserProfileInfo userProfile);

}
