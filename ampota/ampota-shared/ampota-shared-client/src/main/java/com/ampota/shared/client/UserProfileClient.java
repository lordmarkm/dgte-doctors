package com.ampota.shared.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.ampota.shared.dto.UserProfileInfo;

@FeignClient("user")
public interface UserProfileClient {

    @GetMapping("/api/user-profile")
    ResponseEntity<UserProfileInfo> getCurrentUser();

    @GetMapping(value = "/api/user-profile", params = "fbLink")
    ResponseEntity<UserProfileInfo> getUserProfile(@RequestParam String fbLink);

    @PostMapping("/api/user-profile/register")
    ResponseEntity<UserProfileInfo> register(@RequestBody UserProfileInfo userProfile);

    @GetMapping("/api/user-profile/find-by-username")
    ResponseEntity<UserProfileInfo> findByUsername(@RequestParam String username);

    @GetMapping("/api/user-profile/{id}")
    ResponseEntity<UserProfileInfo> findOneInfo(@PathVariable Long id);

}
