package com.ampota.user.service;

import java.util.Optional;

import com.ampota.user.model.UserProfile;

import xyz.xpay.shared.jpa.service.XpayJpaService;

public interface UserProfileService extends XpayJpaService<UserProfile>, UserProfileServiceCustom {

    Optional<UserProfile> findByUsername(String username);

}
