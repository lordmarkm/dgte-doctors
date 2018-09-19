package com.ampota.user.resource;

import java.security.Principal;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ampota.shared.dto.UserProfileInfo;
import com.ampota.user.service.UserProfileService;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/user-profile")
public class UserProfileResource {

    @Autowired
    private UserProfileService service;

    @GetMapping
    public ResponseEntity<UserProfileInfo> getUserProfile(Principal principal) {
        return service.findByUsernameInfo(principal.getName())
                .map(u -> ResponseEntity.status(OK).body(u))
                .orElse(ResponseEntity.status(NOT_FOUND).body(null));
    }

    @PostMapping("/register")
    public ResponseEntity<UserProfileInfo> register(Principal principal,
            @Valid @RequestBody UserProfileInfo userProfile) {
        String username = principal.getName();
        Optional<UserProfileInfo> existingUser = service.findByUsernameInfo(username);
        if (existingUser.isPresent()) {
            return ResponseEntity.status(CONFLICT).body(existingUser.get());
        } else {
            userProfile.setUsername(username);
            return ResponseEntity.status(ACCEPTED).body(service.saveInfo(userProfile));
        }
    }

}
