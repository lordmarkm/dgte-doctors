package com.dgtedr.backend.shared.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dgtedr.backend.shared.dto.SpecialtyInfo;

@FeignClient("doctor")
@RequestMapping("/specialty")
public interface SpecialtyClient extends BaseClient<SpecialtyInfo> {

}
