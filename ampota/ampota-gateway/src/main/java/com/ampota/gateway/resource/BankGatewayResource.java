package com.ampota.gateway.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ampota.shared.client.BankClient;
import com.ampota.shared.dto.BankInfo;

import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/bank")
public class BankGatewayResource {

    @Autowired
    private BankClient client;

    @GetMapping
    public ResponseEntity<Page<BankInfo>> rqlSearch(
            @RequestParam(required = false) @ApiParam(value = "The search term, example: name==test;deleted==false") String term,
            Pageable page) {
        return client.rqlSearch(term, page);
    }

    @PostMapping
    public ResponseEntity<BankInfo> save(@Valid @RequestBody BankInfo bank) {
        return client.save(bank);
    }

}
