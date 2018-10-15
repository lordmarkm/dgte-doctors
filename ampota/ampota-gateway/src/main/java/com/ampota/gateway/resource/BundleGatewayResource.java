package com.ampota.gateway.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ampota.shared.client.BundleClient;
import com.ampota.shared.dto.card.collection.BundleInfo;

import io.swagger.annotations.ApiParam;
import xyz.quadx.xpay.shared.firebase.FirebaseUserDetails;

@RestController
@RequestMapping("/api/bundle")
public class BundleGatewayResource {

    @Autowired
    private BundleClient client;

    @GetMapping
    public ResponseEntity<Page<BundleInfo>> rqlSearch(
            @RequestParam(required = false) @ApiParam(value = "The search term, example: name==test;deleted==false") String term,
            Pageable page) {
        return client.rqlSearch(term, page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BundleInfo> findOne(@PathVariable long id) {
        return client.findOne(id);
    }

    @PostMapping
    public ResponseEntity<BundleInfo> save(@AuthenticationPrincipal FirebaseUserDetails principal, @Valid @RequestBody BundleInfo bundle) {
        bundle.setOwner(principal.getUsername());
        bundle.setOwnerName(principal.getDisplayName());
        bundle.setOwnerLink(principal.getFbLink());
        return client.save(bundle);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BundleInfo> delete(@PathVariable long id) {
        return client.delete(id);
    }

}
