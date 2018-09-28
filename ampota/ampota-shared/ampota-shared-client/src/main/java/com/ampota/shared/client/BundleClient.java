package com.ampota.shared.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.ampota.shared.dto.card.collection.BundleInfo;

@FeignClient("card")
public interface BundleClient {

    /**
     * @RequestBody on Pageable is necessary here for the marshaller to work
     */
    @GetMapping("/api/bundle")
    ResponseEntity<Page<BundleInfo>> rqlSearch(@RequestParam String term, @RequestBody Pageable page);

    @GetMapping("/api/bundle/{id}")
    ResponseEntity<BundleInfo> findOne(@PathVariable long id);

    @PostMapping("/api/bundle")
    ResponseEntity<BundleInfo> save(@RequestBody BundleInfo bundle);

    @DeleteMapping("/api/bundle/{id}")
    ResponseEntity<BundleInfo> delete(@PathVariable long id);

}
