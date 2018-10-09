package com.ampota.user.service;

import java.util.Optional;

import com.ampota.user.model.FacebookLink;

import xyz.xpay.shared.jpa.service.XpayJpaService;

public interface FacebookLinkService extends XpayJpaService<FacebookLink> {

    Optional<FacebookLink> findByUsername(String username);

}

