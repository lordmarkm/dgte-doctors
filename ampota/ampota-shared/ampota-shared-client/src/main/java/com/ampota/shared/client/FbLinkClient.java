package com.ampota.shared.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("user")
public interface FbLinkClient {

    @PutMapping("/api/fb-link")
    ResponseEntity<String> putFbLink(@RequestParam String fbLink);

}
