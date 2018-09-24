package com.ampota.shared.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.ampota.shared.dto.card.CardInfo;

@FeignClient("card")
public interface CardClient {

    /**
     * @RequestBody on Pageable is necessary here for the marshaller to work
     */
    @GetMapping("/api/card")
    ResponseEntity<Page<CardInfo>> rqlSearch(@RequestParam String term, @RequestBody Pageable page);

}
