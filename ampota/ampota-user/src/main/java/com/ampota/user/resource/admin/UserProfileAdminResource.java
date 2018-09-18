package com.ampota.user.resource.admin;

import static org.springframework.http.HttpStatus.OK;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ampota.shared.dto.UserProfileInfo;
import com.ampota.user.service.UserProfileService;

import xyz.xpay.shared.web.BaseResource;

@RestController
@RequestMapping("/api/admin/user-profile")
public class UserProfileAdminResource extends BaseResource<UserProfileInfo, UserProfileService> {

    @PostMapping
    public ResponseEntity<UserProfileInfo> save(@RequestBody UserProfileInfo user) {
        return ResponseEntity.status(OK).body(service.saveInfo(user));
    }

}
