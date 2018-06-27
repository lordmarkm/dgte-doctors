package com.dgtedr.backend.shared.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.dgtedr.backend.shared.dto.SpecialtyInfo;

@FeignClient("doctor")
//@RequestMapping("/specialty") Don't do this or Spring will expose this interface as a controller
public interface SpecialtyClient {

    @GetMapping("/specialty/{id}")
    ResponseEntity<SpecialtyInfo> findOne(@PathVariable Long id);

}
