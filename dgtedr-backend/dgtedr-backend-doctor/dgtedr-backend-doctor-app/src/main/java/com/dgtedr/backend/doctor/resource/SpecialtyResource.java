package com.dgtedr.backend.doctor.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dgtedr.backend.doctor.service.SpecialtyService;
import com.dgtedr.backend.shared.dto.SpecialtyInfo;
import com.mynt.core.web.BaseResource;

@RestController
@RequestMapping("/specialty")
public class SpecialtyResource extends BaseResource<SpecialtyInfo, SpecialtyService> {

}
