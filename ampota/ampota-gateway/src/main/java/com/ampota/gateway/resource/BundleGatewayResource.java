package com.ampota.gateway.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ampota.shared.client.BundleClient;
import com.ampota.shared.dto.card.collection.BundleInfo;

import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/bundle")
public class BundleGatewayResource {

    @Autowired
    private BundleClient client;

    @GetMapping
    public ResponseEntity<Page<BundleInfo>> rqlSearch(
            @RequestParam(required = false) @ApiParam(value = "The search term, example: name==test;deleted==false") String term,
            Pageable page) {
        return client.rqlSearch(term, page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BundleInfo> findOne(@PathVariable long id) {
        return client.findOne(id);
    }

    @PostMapping
    public ResponseEntity<BundleInfo> save(@Valid @RequestBody BundleInfo bundle) {
        return client.save(bundle);
    }

}
