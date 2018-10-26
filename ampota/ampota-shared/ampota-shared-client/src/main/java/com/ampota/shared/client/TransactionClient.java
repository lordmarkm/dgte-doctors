package com.ampota.shared.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.ampota.shared.dto.transaction.TransactionInfo;

@FeignClient("card")
public interface TransactionClient {

    /**
     * @RequestBody on Pageable is necessary here for the marshaller to work
     */
    @GetMapping("/api/transaction")
    ResponseEntity<Page<TransactionInfo>> rqlSearch(@RequestParam String term, @RequestBody Pageable page);

    @PostMapping("/api/transaction")
    ResponseEntity<TransactionInfo> save(@RequestBody TransactionInfo txn);

    @GetMapping("/api/transaction/{id}")
    ResponseEntity<TransactionInfo> findOneInfo(@PathVariable Long id);

}
