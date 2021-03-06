package com.ampota.card.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ampota.card.service.transaction.TransactionService;
import com.ampota.shared.dto.transaction.TransactionInfo;

import xyz.xpay.shared.web.BaseResource;

@RestController
@RequestMapping("/api/transaction")
public class TransactionResource extends BaseResource<TransactionInfo, TransactionService> {

    @PostMapping
    public ResponseEntity<TransactionInfo> save(@RequestBody TransactionInfo txn) {
        return ResponseEntity.ok(service.saveInfo(txn));
    }

}
