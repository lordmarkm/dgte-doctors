package com.ampota.user.resource;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ampota.user.model.FacebookLink;
import com.ampota.user.service.FacebookLinkService;

import xyz.quadx.shared.dto.OperationResult;

@RequestMapping("/api/fb-link")
@RestController
public class FbLinkResource {

    @Autowired
    private FacebookLinkService service;

    @PutMapping
    public ResponseEntity<OperationResult<String>> saveFbLink(Principal principal, @RequestParam String fbLink) {
        String username = principal.getName();
        return service.findByUsername(username)
            .map(fbLinkEntity -> {
                fbLinkEntity.setFbLink(fbLink);
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(OperationResult.of(service.save(fbLinkEntity).getFbLink()));
            })
            .orElseGet(() -> {
                FacebookLink newFbLink = new FacebookLink();
                newFbLink.setUsername(username);
                newFbLink.setFbLink(fbLink);
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(OperationResult.of(service.save(newFbLink).getFbLink()));
            });
    }

}
