package com.ampota.shared.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import xyz.quadx.shared.dto.OperationResult;

@FeignClient("user")
public interface FbLinkClient {

    @GetMapping("/api/fb-link")
    ResponseEntity<OperationResult<String>> getFbLink();

    @PutMapping("/api/fb-link")
    ResponseEntity<OperationResult<String>> putFbLink(@RequestParam String fbLink);

}
