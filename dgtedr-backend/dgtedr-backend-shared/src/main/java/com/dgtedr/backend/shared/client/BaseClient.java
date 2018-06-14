package com.dgtedr.backend.shared.client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface BaseClient<D> {

    @GetMapping("/{id}")
    ResponseEntity<D> findOne(@PathVariable Long id);

}
