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

import com.ampota.shared.dto.MeetupInfo;

@FeignClient("user")
public interface MeetupClient {

    /**
     * @RequestBody on Pageable is necessary here for the marshaller to work
     */
    @GetMapping("/api/meetup")
    ResponseEntity<Page<MeetupInfo>> rqlSearch(@RequestParam String term, @RequestBody Pageable page);

    @PostMapping("/api/meetup")
    ResponseEntity<MeetupInfo> save(@RequestBody MeetupInfo meetup);

    @GetMapping("/api/meetup/{id}")
    ResponseEntity<MeetupInfo> findOneInfo(@PathVariable Long id);

}
