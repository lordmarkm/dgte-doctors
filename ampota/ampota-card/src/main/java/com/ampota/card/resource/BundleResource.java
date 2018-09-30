package com.ampota.card.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ampota.card.service.collection.BundleService;
import com.ampota.shared.dto.card.collection.BundleInfo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import xyz.quadx.xpay.shared.firebase.FirebaseUserDetails;
import xyz.xpay.shared.web.BaseResource;

@RestController
@RequestMapping("/api/bundle")
public class BundleResource extends BaseResource<BundleInfo, BundleService> {

    @PostMapping
    public ResponseEntity<BundleInfo> save(@AuthenticationPrincipal FirebaseUserDetails principal, @RequestBody BundleInfo bundle) {
        bundle.setOwner(principal.getUsername());
        bundle.setOwnerName(principal.getDisplayName());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.saveInfo(bundle));
    }

}
