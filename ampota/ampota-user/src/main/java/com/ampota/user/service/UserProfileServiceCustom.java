package com.ampota.user.service;

import java.util.Optional;

import com.ampota.shared.dto.UserProfileInfo;
import com.ampota.user.model.UserProfile;

import xyz.xpay.shared.jpa.service.XpayJpaServiceCustom;

public interface UserProfileServiceCustom extends XpayJpaServiceCustom<UserProfile, UserProfileInfo> {

    Optional<UserProfileInfo> findByUsernameInfo(String username);
    Optional<UserProfileInfo> findByUsernameInfo(String username, String fbLink);

}
