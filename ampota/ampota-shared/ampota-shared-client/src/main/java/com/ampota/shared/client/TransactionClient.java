package com.ampota.shared.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ampota.shared.dto.transaction.TransactionInfo;

@FeignClient("card")
public interface TransactionClient {

    @PostMapping("/api/transaction")
    ResponseEntity<TransactionInfo> save(@RequestBody TransactionInfo txn);

}
