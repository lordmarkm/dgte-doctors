package com.ampota.user.service;

import java.util.Optional;

import com.ampota.shared.dto.UserProfileInfo;
import com.ampota.user.model.UserProfile;

import xyz.xpay.shared.jpa.service.XpayJpaServiceCustomImpl;

public class UserProfileServiceCustomImpl extends XpayJpaServiceCustomImpl<UserProfile, UserProfileInfo, UserProfileService>
    implements UserProfileServiceCustom {

    @Override
    public Optional<UserProfileInfo> findByUsernameInfo(String username) {
        return Optional.ofNullable(toDto(repo.findByUsername(username).orElse(null)));
    }

}
