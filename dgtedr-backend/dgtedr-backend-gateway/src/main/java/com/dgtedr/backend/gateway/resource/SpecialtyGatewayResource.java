package com.dgtedr.backend.gateway.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dgtedr.backend.shared.client.SpecialtyClient;
import com.dgtedr.backend.shared.dto.SpecialtyInfo;

@RestController
@RequestMapping("/api/specialty")
public class SpecialtyGatewayResource {

    private static final Logger LOG = LoggerFactory.getLogger(SpecialtyGatewayResource.class);

    @Autowired
    private SpecialtyClient specialtyClient;

    @GetMapping("/{id}")
    public ResponseEntity<SpecialtyInfo> findOne(@PathVariable Long id) {
        LOG.info("SpecialtyGatewayResource::findOne({})", id);
        return specialtyClient.findOne(id);
    }

}
