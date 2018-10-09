package com.ampota.gateway.resource;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ampota.shared.client.UserProfileClient;
import com.ampota.shared.dto.UserProfileInfo;

@RestController
@RequestMapping("/api/user-profile")
public class UserProfileGatewayResource {

    @Autowired
    private UserProfileClient client;

    @GetMapping
    public ResponseEntity<UserProfileInfo> getUserProfile(Principal principal, @RequestParam(required = false) String fbLink) {
        return client.getUserProfile(fbLink);
    }

    @PostMapping("/register")
    public ResponseEntity<UserProfileInfo> register(@Valid @RequestBody UserProfileInfo userProfile) {
        return client.register(userProfile);
    }

}
