package com.ampota.gateway.resource;

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

import com.ampota.shared.client.TransactionClient;
import com.ampota.shared.dto.transaction.TransactionInfo;

import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/transaction")
public class TransactionGatewayResource {

    @Autowired
    private TransactionClient client;

    @GetMapping
    public ResponseEntity<Page<TransactionInfo>> rqlSearch(
            @RequestParam(required = false) @ApiParam(value = "The search term, example: name==test;deleted==false") String term,
            Pageable page) {
        return client.rqlSearch(term, page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionInfo> findById(@PathVariable Long id) {
        return client.findOneInfo(id);
    }

    @PostMapping
    public ResponseEntity<TransactionInfo> save(@RequestBody TransactionInfo txn) {
        return client.save(txn);
    }

}
