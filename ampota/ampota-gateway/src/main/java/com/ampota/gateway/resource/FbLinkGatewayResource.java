package com.ampota.gateway.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ampota.shared.client.FbLinkClient;

@RestController
@RequestMapping("/api/fb-link")
public class FbLinkGatewayResource {

    @Autowired
    private FbLinkClient client;

    @PutMapping
    public ResponseEntity<String> putFbLink(@RequestParam String fbLink) {
        return client.putFbLink(fbLink);
    }

}
