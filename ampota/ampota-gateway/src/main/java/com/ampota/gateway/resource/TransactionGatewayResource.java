package com.ampota.gateway.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ampota.shared.client.TransactionClient;
import com.ampota.shared.dto.transaction.TransactionInfo;

@RestController
@RequestMapping("/api/transaction")
public class TransactionGatewayResource {

    @Autowired
    private TransactionClient client;

    @PostMapping
    public ResponseEntity<TransactionInfo> save(@RequestBody TransactionInfo txn) {
        return client.save(txn);
    }

}
