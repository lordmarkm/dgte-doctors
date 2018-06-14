package com.dgtedr.backend.doctor.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dgtedr.backend.doctor.service.SpecialtyService;
import com.dgtedr.backend.shared.client.SpecialtyClient;
import com.dgtedr.backend.shared.dto.SpecialtyInfo;
import com.mynt.core.web.BaseResource;
import static org.springframework.http.HttpStatus.*;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/specialty")
public class SpecialtyResource extends BaseResource<SpecialtyInfo, SpecialtyService>
    implements SpecialtyClient {

    private static final Logger LOG = LoggerFactory.getLogger(SpecialtyResource.class);

    @PostMapping
    public ResponseEntity<SpecialtyInfo> save(@RequestBody @Valid SpecialtyInfo specialty) {
        LOG.info("SpecialtyResource::save(...); specialty={}", specialty);
        return new ResponseEntity<>(service.saveInfo(specialty), OK);
    }

}
