package com.dgtedr.backend.shared.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dgtedr.backend.shared.dto.SpecialtyInfo;

@FeignClient("doctor")
@RequestMapping("/specialty")
public interface SpecialtyClient {

    @GetMapping("/{id}")
    ResponseEntity<SpecialtyInfo> findOne(@PathVariable Long id);

}
