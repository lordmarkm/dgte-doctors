package com.ampota.gateway.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import com.ampota.shared.client.FbLinkClient;

import xyz.quadx.xpay.shared.firebase.FirebaseUserDetails;

/**
 * Get a user's FB link and add it to the user details
 * TODO once independent of Xpay, move this logic to FirebaseAuthenticationProvider
 * @author mbmartinez on 15 Oct 2018
 *
 */
@Component
public class InsertFbLinkListener implements ApplicationListener<AuthenticationSuccessEvent> {

    private static final Logger LOG = LoggerFactory.getLogger(InsertFbLinkListener.class);

    @Autowired
    private FbLinkClient client;

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        FirebaseUserDetails userDetails = (FirebaseUserDetails) event.getAuthentication().getPrincipal();
        LOG.info("Trying to retrieve and set FB link for user. user={}", userDetails.getUsername());

        String fbLink = client.getFbLink().getBody().getData();
        userDetails.setFbLink(fbLink);
        LOG.info("Successfully set fb link. link={}", fbLink);
    }

}
