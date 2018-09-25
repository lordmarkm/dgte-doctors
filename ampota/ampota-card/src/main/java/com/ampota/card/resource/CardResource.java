package com.ampota.card.resource;

import java.security.Principal;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ampota.card.service.CardService;
import com.ampota.shared.dto.card.CardInfo;

import xyz.xpay.shared.web.BaseResource;

@RestController
@RequestMapping("/api/card")
public class CardResource extends BaseResource<CardInfo, CardService> {

    @GetMapping("/unique-names")
    ResponseEntity<Set<String>> uniqueNames(Principal principal, @RequestParam String term) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findUniqueNameLike(term));
    }

}
