package com.ampota.user.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ampota.shared.dto.UserProfileInfo;
import com.ampota.user.model.FacebookLink;
import com.ampota.user.model.UserProfile;
import com.google.api.client.repackaged.com.google.common.base.Strings;

import xyz.xpay.shared.jpa.service.XpayJpaServiceCustomImpl;

public class UserProfileServiceCustomImpl extends XpayJpaServiceCustomImpl<UserProfile, UserProfileInfo, UserProfileService>
    implements UserProfileServiceCustom {

    private static final Logger LOG = LoggerFactory.getLogger(UserProfileServiceCustomImpl.class);

    @Autowired
    private FacebookLinkService fbLinkService;

    @Override
    public Optional<UserProfileInfo> findByUsernameInfo(String username) {
        return findByUsernameInfo(username, null);
    }

    @Override
    public Optional<UserProfileInfo> findByUsernameInfo(String username, String fbLink) {

        UserProfileInfo upi = toDto(repo.findByUsername(username).orElse(null));

        //Set FB link to dto if it already exists
        //Create new facebook link if it doesn't
        FacebookLink link = fbLinkService.findByUsername(username).map(fbl -> {
                    if (null != upi) {
                        upi.setFbLink(fbl.getFbLink());
                    }
                    return fbl;
                })
                .orElseGet(() -> {
                    if (!Strings.isNullOrEmpty(fbLink)) {
                        FacebookLink fbl = new FacebookLink();
                        fbl.setUsername(username);
                        fbl.setFbLink(fbLink);
                        return fbLinkService.save(fbl);
                    } else {
                        LOG.info("Null or empty fbLink. Skipping.");
                        return null;
                    }
                });

        LOG.info("New facebook linked. fbl={}", link);
        return Optional.ofNullable(upi);
    }

}
