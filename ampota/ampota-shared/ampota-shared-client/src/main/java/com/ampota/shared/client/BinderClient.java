package com.ampota.shared.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.ampota.shared.dto.card.collection.BinderInfo;

@FeignClient("card")
public interface BinderClient {

    /**
     * @RequestBody on Pageable is necessary here for the marshaller to work
     */
    @GetMapping("/api/binder")
    ResponseEntity<Page<BinderInfo>> rqlSearch(@RequestParam String term, @RequestBody Pageable page);

    @PostMapping("/api/binder")
    ResponseEntity<BinderInfo> save(@RequestBody BinderInfo binder);

}
