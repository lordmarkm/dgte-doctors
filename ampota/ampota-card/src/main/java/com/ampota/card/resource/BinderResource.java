package com.ampota.card.resource;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ampota.card.service.collection.BinderService;
import com.ampota.shared.dto.card.collection.BinderInfo;

import xyz.xpay.shared.web.BaseResource;

@RestController
@RequestMapping("/api/binder")
public class BinderResource extends BaseResource<BinderInfo, BinderService> {

    @PostMapping
    public ResponseEntity<BinderInfo> save(Principal principal, @RequestBody BinderInfo binder) {
        String username = principal.getName();
        binder.setOwner(username);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.saveInfo(binder));
    }

}
