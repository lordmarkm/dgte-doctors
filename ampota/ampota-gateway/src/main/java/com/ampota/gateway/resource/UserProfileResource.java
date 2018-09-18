package com.ampota.gateway.resource;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ampota.shared.client.UserProfileClient;
import com.ampota.shared.dto.UserProfileInfo;

@RestController
@RequestMapping("/api/user-profile")
public class UserProfileResource {

    @Autowired
    private UserProfileClient client;

    @GetMapping
    public ResponseEntity<UserProfileInfo> getUserProfile(Principal principal) {
        return client.getUserProfile();
    }

    @PostMapping
    public ResponseEntity<UserProfileInfo> save(@RequestBody UserProfileInfo userProfile) {
        
    }

}
