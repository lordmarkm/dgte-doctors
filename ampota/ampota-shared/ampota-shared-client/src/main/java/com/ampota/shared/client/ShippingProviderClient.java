package com.ampota.shared.client;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.ampota.shared.dto.ShippingProviderInfo;

@FeignClient("user")
public interface ShippingProviderClient {

    /**
     * @RequestBody on Pageable is necessary here for the marshaller to work
     */
    @GetMapping("/api/shipping-provider")
    ResponseEntity<Page<ShippingProviderInfo>> rqlSearch(@RequestParam String term, @RequestBody Pageable page);

    @PostMapping("/api/shipping-provider")
    ResponseEntity<ShippingProviderInfo> save(@Valid @RequestBody ShippingProviderInfo shippingProvider);

}
